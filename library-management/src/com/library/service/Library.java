package com.library.service;

import com.library.model.*;

import java.util.*;

public class Library {

    private Map<String, Member> members = new HashMap<>();
    private Map<String, BookCopy> copies = new HashMap<>();
    private List<Loan> activeLoans = new ArrayList<>();

    public void addBook(Book book, int count) {
        for (int i = 1; i <= count; i++) {
            String copyId = book.getIsbn() + "-C" + i;
            copies.put(copyId, new BookCopy(copyId, book));
        }
    }

    public boolean memberExists(String memberId) {
        return members.containsKey(memberId);
    }

    public Member getMember(String memberId) {
        return members.get(memberId);
    }

    public void registerMember(Member member) {
        members.put(member.getMemberId(), member);
    }

    // ISSUE BOOK
    public void issueBook(String copyId, String memberId) {

        BookCopy copy = copies.get(copyId);
        if (copy == null)
            throw new IllegalArgumentException("Invalid copy ID");

        if (!copy.isAvailable())
            throw new IllegalStateException("Book already issued");

        Member member = members.get(memberId);
        if (member == null)
            throw new IllegalArgumentException("Invalid member ID");

        Loan loan = new Loan(copy, member);
        activeLoans.add(loan);
        copy.markBorrowed();
    }


    // RETURN BOOK
    public void returnBook(String copyId, String memberId) {

        BookCopy copy = copies.get(copyId);
        if (copy == null)
            throw new IllegalArgumentException("Invalid copy ID");

        Member member = members.get(memberId);
        if (member == null)
            throw new IllegalArgumentException("Invalid member ID");

        Loan matchingLoan = null;

        for (Loan loan : activeLoans) {
            if (loan.getCopy().getCopyId().equals(copyId)
                    && loan.getMember().getMemberId().equals(memberId)) {
                matchingLoan = loan;
                break;
            }
        }

        if (matchingLoan == null) {
            throw new IllegalStateException(
                    "Book is not issued to member " + member.getName()
            );
        }

        copy.markReturned();
        activeLoans.remove(matchingLoan);
    }

    //Available Copies
    public Map<String, List<String>> getAvailableCopiesByBook() {

        Map<String, List<String>> result = new HashMap<>();

        for (BookCopy copy : copies.values()) {
            if (copy.isAvailable()) {
            String title = copy.getBook().getTitle();
            result.putIfAbsent(title, new ArrayList<>());
            result.get(title).add(copy.getCopyId());
        }
    }

        return result;
}
}

