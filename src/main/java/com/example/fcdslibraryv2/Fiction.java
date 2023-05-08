package com.example.fcdslibraryv2;

public class Fiction extends Book{
	private String genre = "Fiction";
    public Fiction(String isbn, String title, String author, String publisher, String release_year) {
        super(isbn, title, author, publisher, release_year);
    }
    @Override
    public String getGenre() {
        return genre;
    }
}