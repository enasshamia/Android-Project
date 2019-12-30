package com.example.noteapp.model;


public class Book {
    private String bookTitle;
    private String bookColor;
    private String bookId;

    public Book() {
        this.bookTitle = "";
        this.bookColor = "0";
        this.bookId = "";
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookColor() {
        return bookColor;
    }

    public void setBookColor(String bookColor) {
        this.bookColor = bookColor;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
