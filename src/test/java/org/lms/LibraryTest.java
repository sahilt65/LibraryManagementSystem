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
        Book newBook = new Book("123-202", "Java", "Freeman", 2001); // Assuming quantity is also passed
        library.addBook(newBook);
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
    @Test
    public void testAddDuplicateBook() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.addBook(book1); // Adding the same book again
        });
        assertEquals("Book with ISBN 123-202 already exists.", exception.getMessage());
    }

}

