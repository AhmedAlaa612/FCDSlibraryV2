package com.example.fcdslibraryv2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public static Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("FCDS Library");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("logo.png"))));
        showMainView();
    }
    public static void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("MainView.fxml"));
        BorderPane root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void showAdd() throws IOException {
        BorderPane addNewBook = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("AddNewBook.fxml")));
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
        editBookStage.initOwner(primaryStage);
        Scene scene = new Scene(root);
        editBookStage.setScene(scene);
        editBookStage.showAndWait();
    }

}
