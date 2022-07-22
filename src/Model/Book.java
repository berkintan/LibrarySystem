package Model;

import java.util.ArrayList;

public class Book {
    private String nameOftheBook;
    private String author;
    private String publisher;
    private int numberOfPages;
//    private ArrayList<Book> booksList = new ArrayList<>();

    public Book(String nameofTheBook, String author, int numberOfPages, String publisher) {
        this.publisher = publisher;
        this.nameOftheBook = nameofTheBook;
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public void updateBook(String nameOftheBook, String author, int numberOfPages) {
        this.nameOftheBook = nameOftheBook;
        this.author = author;
        this.numberOfPages = numberOfPages;

        System.out.println("The info has been changed successfully.");
    }

    public String toString() {
        return "Info: " + this.nameOftheBook + " " + this.author + " " + this.author + " " + this.numberOfPages;
    }
}
