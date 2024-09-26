package org.lms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Library class.
 */
public class LibraryTest {

    private Library library;
    private Book book1;
    private Book book2;

    @BeforeEach
    public void setUp() {
        library = new Library();
        book1 = new Book("123-202", "Java", "Freeman", 2001);
        book2 = new Book("123-203", "C", "Sahil", 2011);
        library.addBook(book1);
        library.addBook(book2);
    }


    //Test-1a For adding a unique book which is not already present.
    @Test
    public void testAddBook() {
        Book newBook = new Book("123-201", "C++", "Eric Freeman", 2010); // Assuming quantity is also passed

        // Add the book to the library
        library.addBook(newBook);

        // Verify that the library contains the books which is newly added
        boolean bookExists = library.containsBook("123-201");

        // Assert that the book is in the library
        assertTrue(bookExists, "Library should contain the book with ISBN 123-201 after adding.");
    }


    //Test-1b For adding a same book which is already present. in the library
    @Test
    public void testAddDuplicateBook() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.addBook(book1); // Adding the same book again
        });
        assertEquals("Book with ISBN 123-202 already exists.", exception.getMessage());
    }



    /**
     * Test-2a For Borrowing already present book in the library
     */
    @Test
    public void testBorrowBook() {
        Book borrowedBook = library.borrowBook("123-202");
        assertTrue(borrowedBook.isBorrowed());
    }


    /**
     * Test-2b For Borrowing book which is not present in the library
     * It checks for if the book is present in the library or not if not then @param borrowBook will return an error of IllegalArgumentException
     */
    @Test
    public void testBorrowNonExistentBook() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("123-756");
        });
        assertEquals("Book not found in the library.", exception.getMessage());
    }


    /**
     * Test-2c For Borrowing book which is already borrowed
     */
    @Test
    public void testBorrowAlreadyBorrowedBook() {
        library.borrowBook("123-202");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("123-202");
        });
        assertEquals("Book is already borrowed.", exception.getMessage());
    }

}

