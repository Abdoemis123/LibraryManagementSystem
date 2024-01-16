package org.example.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty isbn;
    private final IntegerProperty id;

    // Constructors

    public Book(String title, String author, String isbn) {
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.isbn = new SimpleStringProperty(isbn);
        this.id = new SimpleIntegerProperty();
    }

    // Getters and setters for JavaFX properties

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    // Define JavaFX properties

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty authorProperty() {
        return author;
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public IntegerProperty idProperty() {
        return id;
    }

    // Other methods...

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id.get() +
                ", title='" + title.get() + '\'' +
                ", author='" + author.get() + '\'' +
                ", isbn='" + isbn.get() + '\'' +
                '}';
    }
}
