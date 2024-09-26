package org.example;

import java.util.Scanner;

public class LibrarySystem {
    private static Library library = new Library(); // Library instance

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    borrowBook(scanner);
                    break;
                case 3:
                    returnBook(scanner);
                    break;
                case 4:
                    viewAvailableBooks();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting Library Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // Display the menu
    private static void displayMenu() {
        System.out.println("\nLibrary Management System:");
        System.out.println("1. Add Book");
        System.out.println("2. Borrow Book");
        System.out.println("3. Return Book");
        System.out.println("4. View Available Books");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    // Add a book to the library
    private static void addBook(Scanner scanner) {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Publication Year: ");
        int year = scanner.nextInt();

        try {
            Book book = new Book(isbn, title, author, year);
            library.addBook(book);
            System.out.println("Book added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Borrow a book from the library
    private static void borrowBook(Scanner scanner) {
        System.out.print("Enter ISBN of the book to borrow: ");
        String isbn = scanner.nextLine();

        try {
            library.borrowBook(isbn);
            System.out.println("Book borrowed successfully.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    // Return a book to the library
    private static void returnBook(Scanner scanner) {
        System.out.print("Enter ISBN of the book to return: ");
        String isbn = scanner.nextLine();

        try {
            library.returnBook(isbn);
            System.out.println("Book returned successfully.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    // View all available books in the library
    private static void viewAvailableBooks() {
        System.out.println("\nAvailable Books:");
        library.viewAvailableBooks();
    }
}