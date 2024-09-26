package org.example;

import java.util.HashMap;
import java.util.Map;

public class Library {
    private Map<String, Book> books;

    public Library() {
        books =  new HashMap<>();
    }

    // Add a new book to the library
    public void addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Book with this ISBN already exists.");
        }
        books.put(book.getIsbn(), book);
    }

    // Borrow a book by ISBN
    public void borrowBook(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is not available.");
        }
        book.setAvailable(false);
    }

    // Return a borrowed book
    public void returnBook(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }
        if (book.isAvailable()) {
            throw new IllegalStateException("Book is not borrowed.");
        }
        book.setAvailable(true);
    }

    // View all available books
    public void viewAvailableBooks() {
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }
}

