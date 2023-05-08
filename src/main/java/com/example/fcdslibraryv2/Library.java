package com.example.fcdslibraryv2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Library {
    private static Library instance;
    private Library() {}
    public static Library getInstance(){
        if (instance == null)
            instance = new Library();
        return instance;
    }
    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    public void addBook(String isbn, String title, String author, String publisher, String release_year, String genre) {
        Book book;
        if (genre.equals("Fiction"))
            book = new Fiction(isbn, title, author, publisher, release_year);
        else
            book = new NonFiction(isbn, title, author, publisher, release_year);
        if (searchBooks(book.getIsbn()) == null) {
            bookList.add(book);
            System.out.println("New book added successfully!");
            return;
        }
        System.out.println("Book Already in library!");
    }
    public void addBook(Book book){
        if (searchBooks(book.getIsbn()) == null) {
            bookList.add(book);
            System.out.println("New book added successfully!");
            return;
        }
        System.out.println("Book Already in library!");
    }
    public void deleteBook(Book book) {
        bookList.remove(book);
        System.out.println("Book removed successfully!");
    }

    public Book searchBooks(String isbn) {
        for (Book book : bookList) {
            if (book.getIsbn().equals(isbn))
                return book;
        }
        return null;
    }
    public ObservableList<Book> getBookList() {
        return bookList;
    }
}