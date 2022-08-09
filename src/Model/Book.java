package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {
    private String nameOftheBook;
    private String author;
    private String publisher;
    private String numberOfPages;
    private boolean available = true;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuu");
    LocalDate localDate = LocalDate.now();


    public Book(String nameofTheBook, String author, String numberOfPages, String publisher) {
        this.publisher = publisher;
        this.nameOftheBook = nameofTheBook;
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public String getNameOftheBook() {
        return nameOftheBook;
    }

    public String getLocalDate() {
        return dtf.format(localDate);
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setNameOftheBook(String nameOftheBook) {
        this.nameOftheBook = nameOftheBook;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

}