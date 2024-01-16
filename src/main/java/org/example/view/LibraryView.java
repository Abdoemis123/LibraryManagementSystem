package org.example.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.Book;
import org.example.model.LibraryModel;

import java.util.function.Consumer;

public class LibraryView  {

    private TableView<Book> tableView;
    private TextField titleField;
    private TextField authorField;
    private TextField isbnField;
    private Button addButton;

    public LibraryView() {
        // Initialize UI elements
        tableView = new TableView<>();
        titleField = new TextField();
        authorField = new TextField();
        isbnField = new TextField();
        addButton = new Button("Add Book");

        // Set up table columns
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());

        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());

        tableView.getColumns().addAll(titleColumn, authorColumn, isbnColumn);

        // Set up layout
        VBox layout = new VBox(10);
        layout.getStyleClass().add("library-view"); // Apply CSS class
        layout.getChildren().addAll(tableView, titleField, authorField, isbnField, addButton);

        // Set up the scene
        Scene scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/styles/library-style.css").toExternalForm());

        // Set up the stage
        Stage stage = new Stage();
        stage.setTitle("Library Management System");
        stage.setScene(scene);
        stage.show();
    }

    // Add a listener for the "Add Book" button
    public void setOnAddBookListener(Consumer<Book> listener) {
        addButton.setOnAction(event -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();

            if (!title.isEmpty() && !author.isEmpty() && !isbn.isEmpty()) {
                Book newBook = new Book(title, author, isbn);
                listener.accept(newBook);
            }
        });
    }

    // Update the book list in the TableView
    public void updateBookList(ObservableList<Book> books) {
        tableView.setItems(books);
    }

    // Create the Scene for the view
    public Scene createScene() {
        return new Scene(new VBox(), 600, 400);
    }

    // Method to create and show the stage
    public void createAndShowStage() {
        Stage stage = new Stage();
        stage.setTitle("Library Management System");
        stage.setScene(createScene());
        stage.show();
    }
}

