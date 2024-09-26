package org.lms;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private int year;
    private boolean isBorrowed;

    public Book(String isbn, String title, String author, int year) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isBorrowed = false;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    //Marks the book as borrowed.
    public void borrowBook() {
        if (!isBorrowed) {
            isBorrowed = true;
        }
    }

    //Marks the book as returned.
    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
        }
    }
}

