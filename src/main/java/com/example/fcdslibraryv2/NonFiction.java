package com.example.fcdslibraryv2;

public class NonFiction extends Book{
    private String genre = "Non-Fiction";
    public NonFiction(String isbn, String title, String author, String publisher, String release_year) {
        super(isbn, title, author, publisher, release_year);
    }
    @Override
    public String getGenre() {
        return genre;
    }
    @Override
    public void setGenre(String genre) {
        this.genre = genre;
    }
}