package Model;

import java.util.ArrayList;

public class Publisher {
    private String nameofThePublisher;
    private String address;
    private String phoneNumber;
    private ArrayList<Book> books = new ArrayList<>();

    public Publisher(String nameofThePublisher) {
        this.nameofThePublisher = nameofThePublisher;
    }

    public Publisher(String nameofThePublisher, String address, String phoneNumber) {
        this.nameofThePublisher = nameofThePublisher;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void addBook(Book book) {
        books.add(book);
    }
    public ArrayList<Book> getBooks() {
        return this.books;
    }

}
