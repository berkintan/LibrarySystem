package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {
    private String nameOftheBook;
    private String author;
    private String publisher;
    private String numberOfPages;
    private boolean available = true;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM uuuu");
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
        String date = dtf.format(localDate);
        String substring = date.substring(3,5);
        String month = getMonth(Integer.parseInt(substring));
        String substring1 = date.substring(0,2);
        String substring2 = date.substring(6,10);
        return substring1 + " " + month + " " + substring2;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public String getMonth(int month) {
        return switch (month) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "lol";
        };
    }

}