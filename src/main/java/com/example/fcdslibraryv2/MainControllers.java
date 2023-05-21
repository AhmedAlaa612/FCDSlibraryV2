package com.example.fcdslibraryv2;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainControllers implements Initializable {
    @FXML
    private void addBtn() throws IOException {
        Main.showAdd();
    }
    // tableview
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

    @FXML
    private TableColumn<Book, Void> buttons;
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
    // load data to the tableview
    public void load_data(){
        library.setItems(Library.getInstance().getBookList());
        search();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load data
        load_data_from_csv();
        load_data();
        // Initialize the columns
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        release_year.setCellValueFactory(new PropertyValueFactory<>("release_year"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        // delete and edit buttons
        buttons.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("\u2716");
            private final Button editButton = new Button("\u270E");
            private final HBox buttonsContainer = new HBox(deleteButton, editButton);

            {
                deleteButton.setOnAction(event -> {
                    Book book = getTableRow().getItem();
                    if (book != null) {
                        Library.getInstance().deleteBook(book);
                    }
                });
                editButton.setOnAction(event -> {
                    Book book = getTableRow().getItem();
                    if (book != null)
                    {
                        try {
                            Main.showEdit(book);
                            load_data();
                            library.refresh();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                // buttons style
                buttonsContainer.setAlignment(Pos.CENTER);
                buttonsContainer.setSpacing(10);
                deleteButton.setStyle("-fx-background-color: transparent; -fx-background-radius: 0;-fx-font-size: 18px; -fx-min-width: 40px;");
                editButton.setStyle("-fx-background-color: transparent; -fx-background-radius: 0;-fx-font-size: 18px; -fx-min-width: 40px;");
            }
            //update the tableview
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonsContainer);
                }
            }
        });
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
    }
    void load_data_from_csv(){
        String line;
        String csvDelimiter = ",";
        try (InputStream inputStream = Main.class.getResourceAsStream("books.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            // Skip the header row
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] bookData = line.split(csvDelimiter);
                String isbn = bookData[0];
                String title = bookData[1];
                String author = bookData[2];
                String publisher = bookData[3];
                String releaseYear = bookData[4];
                String genre = bookData[5];
                Library.getInstance().addBook(isbn, title, author, publisher, releaseYear, genre);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
