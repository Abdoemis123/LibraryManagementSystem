package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.model.Book;
import org.example.model.LibraryModel;
import org.example.view.LibraryView;

public class LibraryController {

    private LibraryModel libraryModel;
    private LibraryView libraryView;

    public LibraryController(LibraryModel libraryModel, LibraryView libraryView) {
        this.libraryModel = libraryModel;
        this.libraryView = libraryView;

        // Initialize the view with the current list of books
        ObservableList<Book> observableBooks = FXCollections.observableList(libraryModel.getBooks());
        libraryView.updateBookList(observableBooks);

        // Set up event handlers
        libraryView.setOnAddBookListener(this::handleAddBook);
    }

    private void handleAddBook(Book newBook) {
        // Add the book to the model
        libraryModel.addBook(newBook);
        // Update the view with the new list of books
        ObservableList<Book> observableBooks = FXCollections.observableList(libraryModel.getBooks());
        libraryView.updateBookList(observableBooks);
    }

}
