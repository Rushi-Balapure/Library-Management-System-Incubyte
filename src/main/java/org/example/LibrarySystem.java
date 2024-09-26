package org.example;
import java.util.Scanner;

public class LibrarySystem {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    viewAvailableBooks();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 5);
    }

    private static void showMenu() {
        System.out.println("\n--- Library Management System ---");
        System.out.println("1. Add Book");
        System.out.println("2. Borrow Book");
        System.out.println("3. Return Book");
        System.out.println("4. View Available Books");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addBook() {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter Publication Year: ");
        int year = scanner.nextInt();

        System.out.print("Enter Number of Copies: ");
        int copies = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Book book = new Book(isbn, title, author, year, copies);
        library.addBook(book);
        System.out.println("Book added successfully.");
    }

    private static void borrowBook() {
        System.out.print("Enter ISBN of the book to borrow: ");
        String isbn = scanner.nextLine();

        try {
            library.borrowBook(isbn);
            System.out.println("Book borrowed successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void returnBook() {
        System.out.print("Enter ISBN of the book to return: ");
        String isbn = scanner.nextLine();

        try {
            library.returnBook(isbn);
            System.out.println("Book returned successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewAvailableBooks() {
        System.out.println("\nAvailable Books in the Library:");
        library.viewAvailableBooks();
    }
}
