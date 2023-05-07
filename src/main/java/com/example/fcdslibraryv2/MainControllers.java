package com.example.fcdslibraryv2;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainControllers implements Initializable {
    public static MainControllers instance;
    public static MainControllers getIsntance(){
        if (instance == null)
            instance = new MainControllers();
        return instance;
    }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        Library.getInstance().addBook("123","The Great Gatsby", "F. Scott Fitzgerald", "1925", "Scribner","Fiction");
//        Library.getInstance().addBook("456","To Kill a Mockingbird", "Harper Lee", "1960", "J. B. Lippincott & Co.","Fiction");
        ObservableList<Book> Books_updated = FXCollections.observableList(Library.getInstance().getlist());
        library.setItems(Books_updated);

        // Initialize the columns
        // Initialize the columns
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        release_year.setCellValueFactory(new PropertyValueFactory<>("release_year"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));

        deleteBtn.disableProperty().bind(
                Bindings.isEmpty(library.getSelectionModel().getSelectedItems())
        );

    }
    @FXML
    public void handleDeleteButtonAction(ActionEvent event) {
        // Get the selected Book object from the TableView
        Book selectedBook = library.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            Library.getInstance().deleteBook(selectedBook);
            library.getItems().remove(selectedBook);
            library.refresh();
        }
    }
    @FXML
    public void refreshScene() throws IOException {
        library.refresh();
    }

}
