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

}

