package com.example.fcdslibraryv2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("FCDS Library");
        showMainview();
    }
    public static void showMainview() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("MainView.fxml"));
        BorderPane root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void showAdd() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        BorderPane addNewBook =loader.load(Main.class.getResource("AddNewBook.fxml"));
        Stage AddBook = new Stage();
        AddBook.setTitle("Add new book");
        AddBook.initModality(Modality.WINDOW_MODAL);
        AddBook.initOwner(primaryStage);
        Scene scene = new Scene(addNewBook);
        AddBook.setScene(scene);
        AddBook.showAndWait();
    }
    public static void showEdit(Book book) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("EditBook.fxml"));
        Parent root = loader.load();
        EditBookController controller = loader.getController();
        controller.setBookData(book);
        Stage editBookStage = new Stage();
        editBookStage.setTitle("Edit Book");
        editBookStage.initModality(Modality.WINDOW_MODAL);
        editBookStage.initOwner(primaryStage); // Make sure to have primaryStage defined or replace it with the appropriate Stage object
        Scene scene = new Scene(root);
        editBookStage.setScene(scene);
        editBookStage.showAndWait();
    }

}
