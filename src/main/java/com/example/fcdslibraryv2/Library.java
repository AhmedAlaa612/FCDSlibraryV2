package com.example.fcdslibraryv2;
import java.util.ArrayList;

public class Library {
    private static Library instance;
    private ArrayList<Book> library;

    private Library() {
        library = new ArrayList<>();
    }
    public static Library getInstance(){
        if (instance == null)
            instance = new Library();
        return instance;
    }

    public void addBook(String isbn, String title, String author, String publisher, String release_year, String genre) {
        Book book;
        if (genre.equals("Fiction"))
            book = new Fiction(isbn, title, author, publisher, release_year);
        else
            book = new NonFiction(isbn, title, author, publisher, release_year);
        //TODO: check if book with the same isbn already there
        if (searchBooks(book.getIsbn()) == null) {
            library.add(book);
            System.out.println("New book added successfully!");
            return;
        }
        System.out.println("Book Already in library!");
    }
    public void addBook(Book book){
        if (searchBooks(book.getIsbn()) == null) {
            library.add(book);
            System.out.println("New book added successfully!");
            return;
        }
        System.out.println("Book Already in library!");
    }
    public void deleteBook(String isbn) {
        //TODO: search for a book with isbn
        //TODO: if found remove from library, else display not found
        for (int i = 0; i < library.size(); i++){
            if (library.get(i).getIsbn().equals(isbn)){
                library.remove(i);
                System.out.println("Book removed successfully!");
                return;
            }
        }
        System.out.println("Book not found!");
    }
    public void deleteBook(Book book) {
        library.remove(book);
    }

    public Book searchBooks(String isbn) {
        for (Book book : library) {
            if (book.getIsbn().equals(isbn))
                return book;
        }
        return null;
    }
    //temporary
    public void showBooks() {
        System.out.println("number of books in library: "+ library.size());
        for (Book book: library){
            System.out.println(book.getTitle());
        }
    }
    
    
    public ArrayList<Book> getlist(){
    	return library;
    }
    
    
}