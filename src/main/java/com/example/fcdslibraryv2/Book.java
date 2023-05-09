package com.example.fcdslibraryv2;

public abstract class Book {
    private String isbn, title, author, publisher, release_year;
    public Book(String isbn, String title, String author, String publisher, String release_year) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.release_year = release_year;
    }
    //abstraction
    public abstract String getGenre();
    // setters and getters
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getRelease_year() {
        return release_year;
    }
}