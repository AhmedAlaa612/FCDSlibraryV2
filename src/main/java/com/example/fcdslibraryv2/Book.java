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
    public abstract void setGenre(String genre);
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

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

}