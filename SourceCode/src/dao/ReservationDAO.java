package dao;

import factory.OpenConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReservationDAO {
    public String creteReservation(int uid, int bid){
        String sqlinsert = "INSERT INTO Reservations(bid,uid) VALUES(?,?)";
        String sqlcheckreserved = "SELECT * FROM Reservations WHERE BID = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            conn = OpenConnection.createConnectionToMySQL();
            preparedStatement = conn.prepareStatement(sqlcheckreserved);
            preparedStatement.setInt(1,bid);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst()) {
                return "The book is already reserved";
            }
            preparedStatement = conn.prepareStatement(sqlinsert);
            preparedStatement.setInt(1,bid);
            preparedStatement.setInt(2,uid);
            preparedStatement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "Book reserved";
    }

    public String cancelReservation(int uid, int bid){
        String sql = "DELETE FROM Reservations where uid = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = OpenConnection.createConnectionToMySQL();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,uid);
            preparedStatement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "Reservations for this user cancelled";
    }

    public boolean checkIfIsReserved(int bid){
        String sql = "SELECT * FROM Reservations where bid = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            conn = OpenConnection.createConnectionToMySQL();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, bid);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst()){
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
