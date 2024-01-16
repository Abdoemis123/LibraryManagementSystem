package org.example.database;

import org.example.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    private static final String JDBC_URL = "jdbc:mysql://your_database_url";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    // Establish a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    // Retrieve the list of books from the database
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM books";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Book book = new Book(resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getString("isbn"));
                    book.setId(resultSet.getInt("id"));
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in a real-world scenario
        }

        return books;
    }

    // Add a new book to the database
    public void addBook(Book book) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO books (title, author, isbn) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.setString(3, book.getIsbn());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        book.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in a real-world scenario
        }
    }
}
