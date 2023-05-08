package com.example.fcdslibraryv2;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainControllers implements Initializable {
    @FXML
    private void addBtn() throws IOException {
        Main.showAdd();
    }
    @FXML
    private  Button refreshBtn;
    // tableview
    @FXML
    private Button deleteBtn;
    @FXML
    private TextField srch;
    @FXML
    private TableView<Book> library;
    @FXML
    private TableColumn<Book, String> isbn;

    @FXML
    private TableColumn<Book, String> title;

    @FXML
    private TableColumn<Book, String> author;

    @FXML
    private TableColumn<Book, String> publisher;

    @FXML
    private TableColumn<Book, String> release_year;

    @FXML
    private TableColumn<Book, String> genre;

    public void search(){
        //filtered list
        FilteredList<Book> booksrch = new FilteredList<>(Library.getInstance().getBookList(),b ->true);
        //linking  list to search box
        srch.textProperty().addListener((observable, oldValue, newValue) -> {
            booksrch.setPredicate(book -> {
                // If filter text is empty, display all books.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                //getting the string in lowercase
                String lowerCaseFilter = newValue.toLowerCase();

                if (book.getIsbn().startsWith(lowerCaseFilter)) {
                    return true; // Filter by ISBN.
                } else if (book.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter by book title.
                }
                if (book.getAuthor().toLowerCase().contains(lowerCaseFilter)) {
                    return true;//Filter by book author
                }
                return false; // Does not match.
            });
        });
        //sorting the filtered list
        SortedList<Book> sortedData = new SortedList<>(booksrch);
        //using comparator to sort the books
        sortedData.comparatorProperty().bind(library.comparatorProperty());
        //setting the books in the table
        library.setItems(sortedData);
    }
    public void load_data(){
        library.setItems(Library.getInstance().getBookList());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load data
        load_data();
        // Initialize the columns
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        release_year.setCellValueFactory(new PropertyValueFactory<>("release_year"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        // Add a listener to the book list
        Library.getInstance().getBookList().addListener((ListChangeListener.Change<? extends Book> change) -> {
            // In the listener, add the new object to the tableview.
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved()) {
                    load_data();
                }
            }
        });
        //search
        search();
        deleteBtn.disableProperty().bind(
                Bindings.isEmpty(library.getSelectionModel().getSelectedItems())
        );
    }
    @FXML
    public void handleDeleteButtonAction() {
        // Get the selected Book object from the TableView
        Book selectedBook = library.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            Library.getInstance().deleteBook(selectedBook);
        }
    }
    @FXML
    public void refreshScene() throws IOException {
        library.refresh();
    }
}
