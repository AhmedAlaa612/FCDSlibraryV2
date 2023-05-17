package com.example.fcdslibraryv2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class Library {
    private static Library instance;
    private Library() {}
    public static Library getInstance(){
        if (instance == null)
            instance = new Library();
        return instance;
    }
    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    public void addBook(String isbn, String title, String author, String publisher, String release_year, String genre) throws Exception {
        Book book;
        if (genre.equals("Fiction"))
            book = new Fiction(isbn, title, author, publisher, release_year);
        else
            book = new NonFiction(isbn, title, author, publisher, release_year);
        if (searchBooks(book.getIsbn()) == 0) {
            bookList.add(book);
            System.out.println("New book added successfully!");
            return;
        }
        throw new Exception("Book Already in library!");
//        System.out.println("Book Already in library!");
    }
    public void addBook(Book book){
        if (searchBooks(book.getIsbn()) == 0) {
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

    public int searchBooks(String isbn) {
        int booknum = 0;
        for (Book book : bookList) {
            if (book.getIsbn().equals(isbn))
                booknum++;
        }
        return booknum;
    }
    public ObservableList<Book> getBookList() {
        return bookList;
    }
}