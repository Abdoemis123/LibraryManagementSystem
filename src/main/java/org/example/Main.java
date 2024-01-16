package org.example;

import javafx.application.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.example.controller.LibraryController;
import org.example.model.LibraryModel;
import org.example.view.LibraryView;

import javax.sql.DataSource;

public class Main extends Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        launch(args);


    }


    @Override
    public void start(javafx.stage.Stage primaryStage) {
        // Your JavaFX initialization code here

        // Set up DataSource with Docker MySQL details
        DataSource dataSource = setupDataSource();

        // Initialize model, view, and controller
        LibraryModel libraryModel = new LibraryModel(dataSource);
        LibraryView libraryView = new LibraryView();
        LibraryController libraryController = new LibraryController(libraryModel, libraryView);

        // Set up the stage with the LibraryView
        libraryView.createAndShowStage();
    }



    // Set up DataSource with Docker MySQL details
    private static DataSource setupDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/library"); // Replace 'your_database_name' with your actual database name
        dataSource.setUsername("abdo");
        dataSource.setPassword("abdo");
        return dataSource;
    }
    }
