package com.library.model;

import java.time.LocalDate;

public class Loan {

    private BookCopy copy;
    private Member member;
    private LocalDate dueDate;

    public Loan(BookCopy copy, Member member) {
        this.copy = copy;
        this.member = member;
        this.dueDate = LocalDate.now().plusDays(14);
    }

    public BookCopy getCopy() {
        return copy;
    }

    public Member getMember() {
        return member;
    }
}
