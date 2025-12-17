package com.library.model;

public class BookCopy {

    private String copyId;
    private Book book;
    private boolean available = true;

    public BookCopy(String copyId, Book book) {
        this.copyId = copyId;
        this.book = book;
    }

    public String getCopyId() {
        return copyId;
    }

    public Book getBook() {
        return book;
    }

    public boolean isAvailable() {
        return available;
    }

    public void markBorrowed() {
        available = false;
    }

    public void markReturned() {
        available = true;
    }
}
