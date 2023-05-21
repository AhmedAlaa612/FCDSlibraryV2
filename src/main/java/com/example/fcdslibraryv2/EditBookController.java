package com.example.fcdslibraryv2;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EditBookController{

    @FXML
    private Button cancelButton;
    @FXML
    private Button submitBtn;
    @FXML
    private TextField isbnField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField publisherField;
    @FXML
    private RadioButton fictionBtn;
    @FXML
    private RadioButton nonfictionBtn;

    private Book book;
    @FXML
    public void setBookData(Book book){
        this.book = book;
        isbnField.setText(book.getIsbn());
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        yearField.setText(book.getRelease_year());
        publisherField.setText(book.getPublisher());
        if (book.getGenre().equals("Fiction"))
            fictionBtn.setSelected(true);
        else
            nonfictionBtn.setSelected(true);
    }
    @FXML
    public void submitBtn(){
        String isbn = isbnField.getText();
        String title = titleField.getText();
        String author = authorField.getText();
        String year = yearField.getText();
        String publisher = publisherField.getText();
        boolean isFiction = fictionBtn.isSelected();
        // Check if all fields are filled
        if (isbn.isEmpty() || title.isEmpty() || author.isEmpty() || year.isEmpty() || publisher.isEmpty()
                || (!isFiction && !nonfictionBtn.isSelected())) {
            // Show a message if any field is empty
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Information");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields.");
            alert.showAndWait();
            return;
        }
        if (Library.getInstance().searchBooks(isbn) > 0 && !book.getIsbn().equals(isbn)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Duplicated Book");
            alert.setHeaderText(null);
            alert.setContentText("A Book with the same ISBN already exists!");
            alert.showAndWait();
            return;
        }
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setRelease_year(year);
        book.setPublisher(publisher);
        if (fictionBtn.isSelected())
            book.setGenre("Fiction");
        else
            book.setGenre("Non-Fiction");
        Stage stage = (Stage)submitBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void cancelBtn() {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
}
