package dao;

import components.TableIssuesModel;
import factory.OpenConnection;
import models.BookModel;
import models.IssueModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueDAO {

    //issue book
    public String issueBook(String uid, String bid){
        String sql_checkisborrowed = "SELECT * FROM Issues WHERE BID = ?"; //check if the book is already borrowed
        String sql_checkifbookexist = "SELECT * FROM Books where BID = ?"; //check if the book exist
        String sql_checkuser = "SELECT * FROM Users where UID = ?"; //check if the user exist
        String sql_checkuserlimit = "SELECT * FROM Issues where UID = ?"; //check if the user can borrow a book
        String sql_getuid = "SELECT * FROM Users where UID = ?"; //check
        String sql_getfines = "SELECT * FROM Fines where UID = ?";
        String sql_borrow = "INSERT INTO Issues(BID, UID, IssuedDate, ReturnDate) VALUES(?,?,?,?)"; //issue book
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            conn = OpenConnection.createConnectionToMySQL();

            //check if the book exists
            preparedStatement = conn.prepareStatement(sql_checkifbookexist);
            preparedStatement.setString(1,bid);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                return "The book does not exist";
            }

            //check if user exists
            preparedStatement = conn.prepareStatement(sql_checkuser);
            preparedStatement.setString(1,uid);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                return "The user does not exist";
            }

            //checking if the book is already borrowed
            preparedStatement = conn.prepareStatement(sql_checkisborrowed);
            preparedStatement.setString(1,bid);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return "The book is already borrowed";
            }


            //check if the users can borrow this book
            int rowcounter = 0;
            boolean isteacher = false;

            //checking if the user is a teacher or a student
            preparedStatement = conn.prepareStatement(sql_getuid);
            preparedStatement.setString(1,uid);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            isteacher = resultSet.getBoolean("Teacher");


            //checking how many books the users has already borrowed
            preparedStatement = conn.prepareStatement(sql_checkuserlimit);
            preparedStatement.setString(1,uid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                rowcounter++;
            }

            //check if the user has any fines
            boolean hasfine = false;
            preparedStatement = conn.prepareStatement(sql_getfines);
            preparedStatement.setString(1,uid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()){
                hasfine = true;
            }

            if(isteacher && rowcounter > 5 || isteacher && hasfine && rowcounter != 0){
                return "The user already have borrowed the maximum amount of books";
            } else if(!isteacher && rowcounter > 2 || !isteacher && hasfine && rowcounter != 0) {
                return "The user already have borrowed the maximum amount of books";
            }

            if(new ReservationDAO().checkIfIsReserved(Integer.parseInt(bid))){
                return "The book is reserved";
            }

            //setting up the dates
            Date returndate = null;
            if(isteacher){
                returndate = Date.valueOf(LocalDate.now().plusDays(15));
            } else {
                returndate = Date.valueOf(LocalDate.now().plusDays(7));
            }
            Date today = Date.valueOf(LocalDate.now());

            //actually borrowing the book
            preparedStatement = conn.prepareStatement(sql_borrow);
            preparedStatement.setString(1,bid);
            preparedStatement.setString(2,uid);
            preparedStatement.setDate(3,today);
            preparedStatement.setDate(4,returndate);
            preparedStatement.execute();

        } catch (Exception e){
            e.printStackTrace();
        }
        return "The book is borrowed";
    }


    //return a book
    public String returnBook(int isid){
        String sql_return = "DELETE FROM Issues WHERE ISID = ?";
        String sql_checkdate = "SELECT * FROM Issues WHERE ISID = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = OpenConnection.createConnectionToMySQL();
            preparedStatement = conn.prepareStatement(sql_checkdate);
            preparedStatement.setInt(1,isid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                return "This borrow does not exist";
            }

            Date returndate = resultSet.getDate("ReturnDate");
            int uid = resultSet.getInt("UID");
            LocalDate today = LocalDate.now().plusDays(7);
            String parsedate = String.valueOf(returndate);
            LocalDate returndateparsed = LocalDate.parse(parsedate);

            //if the book is being returned after the expiration date
            int daysbetween = 0;
            if (today.isAfter(returndateparsed)){
                while (today.isAfter(returndateparsed)){
                    daysbetween++;
                    today = today.minusDays(1);
                }
                new FineDAO().createFine(uid, daysbetween);
            }

            preparedStatement = conn.prepareStatement(sql_return);
            preparedStatement.setInt(1,isid);
            preparedStatement.execute();

        } catch (Exception e){
            e.printStackTrace();
        }
        return "The book has been returned";
    }

    //get all books
    public List<IssueModel> getAllIssues(){
        String sql = "SELECT * FROM Issues";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<IssueModel> issues = new ArrayList<IssueModel>();

        try{
            conn = OpenConnection.createConnectionToMySQL();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                IssueModel issue = new IssueModel();
                issue.setISID(resultSet.getInt("ISID"));
                issue.setBID(resultSet.getInt("BID"));
                issue.setUID(resultSet.getInt("UID"));
                issue.setIssueDate(resultSet.getDate("IssuedDate"));
                issue.setReturnDate(resultSet.getDate("ReturnDate"));
                issues.add(issue);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return issues;
    }

    //renew issue
    public String renewissue(int isid){
        String sqlcheckissue = "SELECT * FROM Issues WHERE ISID = ?";
        String sqlupdatebook = "UPDATE Issues SET ReturnDate = ? WHERE ISID = ?";
        String sqlgetuser = "SELECT * FROM Users WHERE UID = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            conn = OpenConnection.createConnectionToMySQL();
            preparedStatement = conn.prepareStatement(sqlcheckissue);
            preparedStatement.setInt(1,isid);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int uid = resultSet.getInt("UID");
            preparedStatement = conn.prepareStatement(sqlgetuser);
            preparedStatement.setInt(1,uid);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            boolean isteacher = resultSet.getBoolean("Teacher");
            Date returndate = null;
            if(isteacher){
                returndate = Date.valueOf(LocalDate.now().plusDays(15));
            } else {
                returndate = Date.valueOf(LocalDate.now().plusDays(7));
            }

            preparedStatement = conn.prepareStatement(sqlupdatebook);
            preparedStatement.setDate(1,returndate);
            preparedStatement.setInt(2,isid);
            preparedStatement.execute();

        } catch (Exception e){
            e.printStackTrace();
        }
        return "Reservation renewed";
    }

}
