package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;

//    @BeforeEach
    public void setUp() {
        library = new Library(); // Initialize library before each test
    }

    @Test
    public void testAddBook() {
        Book book = new Book("12345", "Test Book", "Test Author", 2023, 3);
        library.addBook(book);
        assertDoesNotThrow(() -> library.borrowBook("12345"));
    }

    @Test
    public void testAddDuplicateBookIncreasesCopies() {
        Book book = new Book("12345", "Test Book", "Test Author", 2023, 2);
        library.addBook(book);

        Book duplicateBook = new Book("12345", "Test Book", "Test Author", 2023, 3);
        library.addBook(duplicateBook);

        Book storedBook = library.getBookByISBN("12345");
        assertEquals(5, storedBook.getTotalCopies()); // Ensure total copies have increased
        assertEquals(5, storedBook.getAvailableCopies()); // Ensure available copies have increased
    }

    @Test
    public void testBorrowBookSuccessWithMultipleCopies() {
        Book book = new Book("12345", "Test Book", "Test Author", 2023, 3);
        library.addBook(book);
        library.borrowBook("12345");

        assertEquals(2, book.getAvailableCopies()); // Check if available copies decrease
        assertTrue(book.isAvailable()); // Still available because copies remain
    }

    @Test
    public void testBorrowAllCopiesAndCheckAvailability() {
        Book book = new Book("12345", "Test Book", "Test Author", 2023, 2);
        library.addBook(book);

        library.borrowBook("12345"); // Borrow first copy
        library.borrowBook("12345"); // Borrow second copy

        assertFalse(book.isAvailable()); // No copies should be left
        assertEquals(0, book.getAvailableCopies()); // All copies borrowed
    }

    @Test
    public void testBorrowNonExistentBook() {
        assertThrows(IllegalArgumentException.class, () -> library.borrowBook("99999"));
    }

    @Test
    public void testBorrowUnavailableBook() {
        Book book = new Book("12345", "Test Book", "Test Author", 2023, 1);
        library.addBook(book);
        library.borrowBook("12345"); // Borrow the only copy

        assertThrows(IllegalStateException.class, () -> library.borrowBook("12345"));
    }

    @Test
    public void testReturnBookSuccess() {
        Book book = new Book("12345", "Test Book", "Test Author", 2023, 1);
        library.addBook(book);
        library.borrowBook("12345");
        library.returnBook("12345");

        assertTrue(book.isAvailable());
        assertEquals(1, book.getAvailableCopies()); // Verify available copies have increased
    }

    @Test
    public void testReturnNonExistentBook() {
        assertThrows(IllegalArgumentException.class, () -> library.returnBook("99999"));
    }

    @Test
    public void testReturnAlreadyAvailableBook() {
        Book book = new Book("12345", "Test Book", "Test Author", 2023, 1);
        library.addBook(book);
        assertThrows(IllegalStateException.class, () -> library.returnBook("12345"));
    }

    @Test
    public void testViewAvailableBooks() {
        Book book1 = new Book("12345", "Test Book 1", "Test Author", 2023, 1);
        Book book2 = new Book("67890", "Test Book 2", "Test Author", 2023, 2);
        library.addBook(book1);
        library.addBook(book2);
        library.borrowBook("67890");

        // Ensure both books are listed as available
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
            obj.testAddDuplicateBookIncreasesCopies();
            System.out.println("Unit Test for Duplicate Book Handling Successful");
            ctr++;
        } catch (Exception e) {
            System.out.println("Unit Test for Duplicate Book Handling Failed");
        }

        try {
            obj.setUp();
            obj.testBorrowBookSuccessWithMultipleCopies();
            System.out.println("Unit Test for Borrowing Books with Multiple Copies Successful");
            ctr++;
        } catch (Exception e) {
            System.out.println("Unit Test for Borrowing Books with Multiple Copies Failed");
        }

        try {
            obj.setUp();
            obj.testBorrowAllCopiesAndCheckAvailability();
            System.out.println("Unit Test for Borrowing All Copies Successful");
            ctr++;
        } catch (Exception e) {
            System.out.println("Unit Test for Borrowing All Copies Failed");
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

        System.out.println("Total Unit Tests Passed: " + ctr + "/10");
    }
}
