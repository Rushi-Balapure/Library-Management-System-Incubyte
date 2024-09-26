package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;

    @BeforeEach
    public void setUp() {
        library = new Library(); // Initialize library before each test
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

    // Manually running tests from main method
    public static void main(String[] args) {
        LibraryTest obj = new LibraryTest();
        int ctr = 0; // to count the number of tests passed

        // Manually call setUp() before each test to initialize the library
        try {
            obj.setUp(); // Initialize for each test
            obj.testAddBook();
            System.out.println("Unit Test for Adding Books Successful");
            ctr++;
        } catch (Exception e) {
            System.out.println("Unit Test for Adding Books Failed");
        }

        try {
            obj.setUp();
            obj.testAddDuplicateBook();
            System.out.println("Unit Test for Duplicate Books Successful");
            ctr++;
        } catch (Exception e) {
            System.out.println("Unit Test for Duplicate Books Failed");
        }

        try {
            obj.setUp();
            obj.testBorrowBookSuccess();
            System.out.println("Unit Test for Borrowing Books Successful");
            ctr++;
        } catch (Exception e) {
            System.out.println("Unit Test for Borrowing Books Failed");
        }

        try {
            obj.setUp();
            obj.testBorrowNonExistentBook();
            System.out.println("Unit Test for Non Existent Borrowing Books Successful");
            ctr++;
        } catch (Exception e) {
            System.out.println("Unit Test for Non Existent Borrowing Books Failed");
        }

        try {
            obj.setUp();
            obj.testBorrowUnavailableBook();
            System.out.println("Unit Test for Borrowing Unavailable Books Successful");
            ctr++;
        } catch (Exception e) {
            System.out.println("Unit Test for Borrowing Unavailable Books Failed");
        }

        try {
            obj.setUp();
            obj.testReturnBookSuccess();
            System.out.println("Unit Test for Returning Books Successful");
            ctr++;
        } catch (Exception e) {
            System.out.println("Unit Test for Returning Books Failed");
        }

        try {
            obj.setUp();
            obj.testReturnNonExistentBook();
            System.out.println("Unit Test for Returning Non-Existent Books Successful");
            ctr++;
        } catch (Exception e) {
            System.out.println("Unit Test for Returning Non-Existent Books Failed");
        }

        try {
            obj.setUp();
            obj.testReturnAlreadyAvailableBook();
            System.out.println("Unit Test for Returning Already Available Books Successful");
            ctr++;
        } catch (Exception e) {
            System.out.println("Unit Test for Returning Already Available Books Failed");
        }

        try {
            obj.setUp();
            obj.testViewAvailableBooks();
            System.out.println("Unit Test for Displaying Available Books Successful");
            ctr++;
        } catch (Exception e) {
            System.out.println("Unit Test for Displaying Available Books Failed");
        }

        System.out.println("Total Unit Tests Passed: " + ctr + "/9");
    }
}
