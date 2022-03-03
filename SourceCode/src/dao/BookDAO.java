package dao;

import factory.OpenConnection;
import models.BookModel;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    //create a book in the database
    public void createBook(BookModel book){
        String sql = "INSERT INTO Books(Title, Author, Edition, Publisher, Year, ISBN) VALUES(?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = OpenConnection.createConnectionToMySQL();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,book.getTitle());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setInt(3,book.getEdition());
            preparedStatement.setString(4,book.getPublisher());
            preparedStatement.setInt(5,book.getYear());
            preparedStatement.setBigDecimal(6,new BigDecimal(book.getISBN()));
            preparedStatement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    // Delete a book in the database
    public void deleteBook(int BID){
        String sql = "DELETE FROM Books WHERE BID = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            conn = OpenConnection.createConnectionToMySQL();
            preparedStatement = conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            preparedStatement.execute();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,BID);
            preparedStatement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //update a book in the database
    public void updateBook(BookModel book){
        String sql = "UPDATE Books SET Title = ?, Author = ?, Edition = ?, Publisher = ?, Year = ?, ISBN = ? WHERE BID = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            conn = OpenConnection.createConnectionToMySQL();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,book.getTitle());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setInt(3,book.getEdition());
            preparedStatement.setString(4,book.getPublisher());
            preparedStatement.setInt(5,book.getYear());
            preparedStatement.setString(6,book.getISBN());
            preparedStatement.setInt(7,book.getBID());
            preparedStatement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Find a book using ISBN
    public BookModel findBookByISBN(String ISBN){
        String sql = "SELECT * FROM Books WHERE ISBN = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BookModel book = new BookModel();
        try{
            conn = OpenConnection.createConnectionToMySQL();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,ISBN);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                return book;
            } else {
                book.setBID(resultSet.getInt("BID"));
                book.setTitle(resultSet.getString("Title"));
                book.setAuthor(resultSet.getString("Author"));
                book.setEdition(resultSet.getInt("Edition"));
                book.setPublisher(resultSet.getString("Publisher"));
                book.setYear(resultSet.getInt("Year"));
                book.setISBN(resultSet.getString("ISBN"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }
    //get all books
    public List<BookModel> getAllBooks(){
        String sql = "SELECT * FROM Books";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<BookModel> books = new ArrayList<BookModel>();

        try{
            conn = OpenConnection.createConnectionToMySQL();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                    BookModel book = new BookModel();
                    book.setBID(resultSet.getInt("BID"));
                    book.setTitle(resultSet.getString("Title"));
                    book.setAuthor(resultSet.getString("Author"));
                    book.setEdition(resultSet.getInt("Edition"));
                    book.setPublisher(resultSet.getString("Publisher"));
                    book.setYear(resultSet.getInt("Year"));
                    book.setISBN(resultSet.getString("ISBN"));
                    books.add(book);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return books;
    }

    //Find books by title
    public List<BookModel> findBook(String title, String publisher, String isbn){
        String sqltitle = "SELECT * FROM Books WHERE Title LIKE ?";
        String sqlpublisher = "SELECT * FROM Books WHERE Publisher LIKE ?";
        String sqlisbn = "SELECT * FROM Books WHERE ISBN LIKE ?";

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<BookModel> books = new ArrayList<BookModel>();

        try{
            conn = OpenConnection.createConnectionToMySQL();
            if(title != null){
                preparedStatement = conn.prepareStatement(sqltitle);
                preparedStatement.setString(1,'%'+title+'%');
            } else if(isbn != null){
                preparedStatement = conn.prepareStatement(sqlisbn);
                preparedStatement.setString(1,'%'+isbn+'%');
            } else if (publisher != null){
                preparedStatement = conn.prepareStatement(sqlpublisher);
                preparedStatement.setString(1,'%'+publisher+'%');
            }

            resultSet = preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst()){
                while(resultSet.next()){
                    BookModel book = new BookModel();
                    book.setBID(resultSet.getInt("BID"));
                    book.setTitle(resultSet.getString("Title"));
                    book.setAuthor(resultSet.getString("Author"));
                    book.setEdition(resultSet.getInt("Edition"));
                    book.setPublisher(resultSet.getString("Publisher"));
                    book.setYear(resultSet.getInt("Year"));
                    book.setISBN(resultSet.getString("ISBN"));
                    books.add(book);
                }
            } else {
                return books;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return books;
    }
}
