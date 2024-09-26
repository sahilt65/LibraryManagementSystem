package org.lms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the library system that manages books.
 */
public class Library {
    private Map<String, Book> books;

    /**
     * Constructs a Library object.
     */
    public Library() {
        books = new HashMap<>();
    }

    public boolean containsBook(String isbn){
        return books.containsKey(isbn);
    }

    /**
     * Adds a book to the library.
     *
     * @param book the book to add
     * @throws IllegalArgumentException if the book already exists in the library
     */
    public void addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists.");
        }
        books.put(book.getIsbn(), book);
    }


    /**
     * Borrows a book from the library.
     *
     * @param isbn the ISBN of the book to borrow
     * @return the borrowed book
     * @throws IllegalArgumentException if the book is not found or already borrowed
     */
    public Book borrowBook(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Book not found in the library.");
        }
        if (book.isBorrowed()) {
            throw new IllegalArgumentException("Book is already borrowed.");
        }
        book.borrowBook();
        return book;
    }


    /**
     * Returns a book to the library.
     *
     * @param isbn the ISBN of the book to return
     * @throws IllegalArgumentException if the book is not found or already borrowed
     */
    public void returnBook(String isbn) {
        // Retrieve the book from the library's collection using the given ISBN.
        // The 'books' is a HashMap where ISBNs are keys, and Book objects are values.
        Book book = books.get(isbn);

        // Check if the book with the given ISBN exists in the library.
        // If the book is not found, throw an exception indicating that the book does not exist.
        if (book == null) {
            throw new IllegalArgumentException("Book not found in the library.");
        }

        // Check if the book has been borrowed or not.
        // If the book is not currently borrowed, throw an exception indicating that it wasn't borrowed.
        if (!book.isBorrowed()) {
            throw new IllegalArgumentException("Book wasn't borrowed.");
        }

        // Mark the book as returned by calling the 'returnBook()' method on the book object.
        // This method updates the internal state of the book to reflect that it is no longer borrowed.
        book.returnBook();
    }

}

