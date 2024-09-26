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

    //Add Book Feature Tests
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



    //Borrow Book Feature Tests
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


    //Return Book Feature Tests
    //Test-3a Test case for returning a borrowed book
    @Test
    public void testReturnBook() {
        //For Book 1
        //Borrow book1 with ISBN "123-202"
        library.borrowBook("123-202");

        //Return book1 back to the library
        library.returnBook("123-202");

        //Assert that book1 is no longer borrowed after return
        assertFalse(book1.isBorrowed());

        //For Book 2
        //Borrow book1 with ISBN "123-202"
        library.borrowBook("123-203");

        //Return book1 back to the library
        library.returnBook("123-203");

        //Assert that book2 is no longer borrowed after return
        assertFalse(book2.isBorrowed());
    }

    //Test-3b : Test case for attempting to return a book that doesn't exist in the library
    @Test
    public void testReturnNonExistentBook() {
        //Try to return a book with an ISBN that does not exist in the library
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.returnBook("978-455");// ISBN "978-455" does not exist
        });

        //Verify that the exception message is as expected
        assertEquals("Book not found in the library.", exception.getMessage());
    }

    // Test-3c : Test case for returning a book that has not been borrowed yet
    @Test
    public void testReturnNonBorrowedBook() {
        //Try to return book1 without borrowing it first
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.returnBook("123-202"); // Book not borrowed yet
        });

        //Verify that the exception message is as expected
        assertEquals("Book wasn't borrowed.", exception.getMessage());
    }

}

