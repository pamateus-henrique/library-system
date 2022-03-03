package models;

public class BookModel {
    //book attributes
    private int BID;
    private String title;
    private String author;
    private int edition;
    private String publisher;
    private int year;
    private String ISBN;

    //constructor
    public BookModel(String title, String author, int edition, String publisher, int year, String ISBN) {
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.publisher = publisher;
        this.year = year;
        this.ISBN = ISBN;
    }

    //overloaded constructor
    public BookModel(){

    }

    //getters and setters
    public int getBID() {
        return BID;
    }

    public void setBID(int BID) {
        this.BID = BID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
