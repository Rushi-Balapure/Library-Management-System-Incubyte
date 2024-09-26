package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;

    @BeforeEach
    public void setUp() {
        library = new Library();
    }

    @Test
    public void testAddBook() {
        Book book = new Book("12345", "Test Book", "Test Author", 2023);
        library.addBook(book);
        assertDoesNotThrow(() -> library.borrowBook("12345"));
    }

    @Test
    public void testAddDuplicateBook() {
        Book book = new Book("12345", "Test Book", "Test Author", 2023);
        library.addBook(book);
        assertThrows(IllegalArgumentException.class, () -> library.addBook(book));
    }

    @Test
    public void testBorrowBookSuccess() {
        Book book = new Book("12345", "Test Book", "Test Author", 2023);
        library.addBook(book);
        library.borrowBook("12345");
        assertFalse(book.isAvailable());
    }

    @Test
    public void testBorrowNonExistentBook() {
        assertThrows(IllegalArgumentException.class, () -> library.borrowBook("99999"));
    }

    @Test
    public void testBorrowUnavailableBook() {
        Book book = new Book("12345", "Test Book", "Test Author", 2023);
        library.addBook(book);
        library.borrowBook("12345");
        assertThrows(IllegalStateException.class, () -> library.borrowBook("12345"));
    }

    @Test
    public void testReturnBookSuccess() {
        Book book = new Book("12345", "Test Book", "Test Author", 2023);
        library.addBook(book);
        library.borrowBook("12345");
        library.returnBook("12345");
        assertTrue(book.isAvailable());
    }

    @Test
    public void testReturnNonExistentBook() {
        assertThrows(IllegalArgumentException.class, () -> library.returnBook("99999"));
    }

    @Test
    public void testReturnAlreadyAvailableBook() {
        Book book = new Book("12345", "Test Book", "Test Author", 2023);
        library.addBook(book);
        assertThrows(IllegalStateException.class, () -> library.returnBook("12345"));
    }

    @Test
    public void testViewAvailableBooks() {
        Book book1 = new Book("12345", "Test Book 1", "Test Author", 2023);
        Book book2 = new Book("67890", "Test Book 2", "Test Author", 2023);
        library.addBook(book1);
        library.addBook(book2);
        library.borrowBook("67890");

        // Only book1 should be available
        library.viewAvailableBooks();
    }
}
