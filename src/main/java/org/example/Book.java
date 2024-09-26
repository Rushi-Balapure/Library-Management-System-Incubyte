package org.example;
public class Book {
    private String isbn;
    private String title;
    private String author;
    private int publicationYear;

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    private int totalCopies;
    private int availableCopies;

    public Book(String isbn, String title, String author, int publicationYear, int totalCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies; // All copies are available initially
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public boolean isAvailable() {
        return availableCopies > 0;
    }

    public void borrowBook() {
        if (availableCopies > 0) {
            availableCopies--;
        } else {
            throw new IllegalStateException("No available copies left.");
        }
    }

    public void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        } else {
            throw new IllegalStateException("All copies are already returned.");
        }
    }

    @Override
    public String toString() {
        return String.format("ISBN: %s, Title: %s, Author: %s, Year: %d, Available: %d/%d",
                isbn, title, author, publicationYear, availableCopies, totalCopies);
    }
}
