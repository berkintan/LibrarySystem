package Model;

import java.util.ArrayList;

public class Book {
    private String nameOftheBook;
    private String author;
    private String publisher;
    private int numberOfPages;

    public Book(String nameofTheBook, String author, int numberOfPages, String publisher) {
        this.publisher = publisher;
        this.nameOftheBook = nameofTheBook;
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public String getNameOftheBook() {
        return nameOftheBook;
    }

    public void setNameOftheBook(String nameOftheBook) {
        this.nameOftheBook = nameOftheBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

}
