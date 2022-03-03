package dao;

import factory.OpenConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class FineDAO {

    //create fine
    public String createFine(int uid, int counter){
        String sql = "INSERT INTO Fines(UID, Value) VALUES (?,?)";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = OpenConnection.createConnectionToMySQL();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,uid);
            preparedStatement.setInt(2,counter);
            preparedStatement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "Fine created";
    }


    //pay fine
    public String payfine(int uid){
        String sql = "DELETE FROM Fines WHERE UID = ?";
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
        return "The fine has been payed";
    }



}
