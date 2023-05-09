package com.example.fcdslibraryv2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class AddBookController {
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
        try {
            if (isFiction)
                Library.getInstance().addBook(isbn, title, author, publisher, year, "Fiction");
            else
                Library.getInstance().addBook(isbn, title, author, publisher, year, "Non-Fiction");
        }
        catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Duplicated Book");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            return;
        }
        isbnField.clear();
        titleField.clear();
        authorField.clear();
        yearField.clear();
        publisherField.clear();
        fictionBtn.setSelected(false);
        nonfictionBtn.setSelected(false);
        Stage stage = (Stage)submitBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void cancelBtn() {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
}
