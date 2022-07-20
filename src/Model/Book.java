package Model;

public class Book {
    private String nameOftheBook;
    private String author;
    private Publisher publisher;
    private int numberOfPages;

    public Book(String nameofTheBook, String author, int numberOfPages, Publisher publisher) {
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
}
