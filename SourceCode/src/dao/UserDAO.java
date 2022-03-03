package dao;
    import factory.OpenConnection;
    import models.UserModel;
    import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    //Insert a user into the database
    public void CreateUser(UserModel user){
        String sql = "INSERT INTO Users(Username, Password, Name, Admin, Teacher) VALUES(?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = OpenConnection.createConnectionToMySQL();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3,user.getName());
            preparedStatement.setBoolean(4,user.getAdmin());
            preparedStatement.setBoolean(5,user.getTeacher());
            preparedStatement.execute();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    //Deletes a user in the database
    public String DeleteUser(int UID){
        String sql = "DELETE FROM Users WHERE UID = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            conn = OpenConnection.createConnectionToMySQL();
            preparedStatement = conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            preparedStatement.execute();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,UID);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return "Cannot delete user that have issues or fines";
        } finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return "user deleted";
    }
    //list all users in the database
    public List<UserModel> ListUsers(){
        //create a User list
        List<UserModel> users = new ArrayList<UserModel>();

        String sql = "SELECT * FROM Users";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = OpenConnection.createConnectionToMySQL();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UserModel user = new UserModel();
                user.setUID(resultSet.getInt("UID"));
                user.setUsername(resultSet.getString("Username"));
                user.setPassword(resultSet.getString("Password"));
                user.setName(resultSet.getString("Name"));
                user.setAdmin(resultSet.getBoolean("Admin"));
                user.setTeacher(resultSet.getBoolean("Teacher"));
                users.add(user);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return users;
    }

    //get user by Name
    public List<UserModel> findUserByName(String name, String username){
        String sqlname = "SELECT * FROM Users WHERE Name LIKE ?";
        String sqlusername = "SELECT * FROM Users WHERE Username LIKE ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<UserModel> users = new ArrayList<UserModel>();
        try{
            conn = OpenConnection.createConnectionToMySQL();
            if(username == null){
                preparedStatement = conn.prepareStatement(sqlname);
                preparedStatement.setString(1,'%'+name+'%');
            } else if(name == null) {
                preparedStatement = conn.prepareStatement(sqlusername);
                preparedStatement.setString(1,'%'+username+'%');
            }

            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                UserModel user = new UserModel();
                user.setUID(resultSet.getInt("UID"));
                user.setUsername(resultSet.getString("Username"));
                user.setPassword(resultSet.getString("Password"));
                user.setName(resultSet.getString("Name"));
                user.setAdmin(resultSet.getBoolean("Admin"));
                user.setTeacher(resultSet.getBoolean("Teacher"));
                users.add(user);
            }
            } else {
                return users;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public UserModel getUserLogin(String username, String password){
        String sql = "SELECT * FROM Users where Username = ? AND Password = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserModel user = null;

        try{
            conn = OpenConnection.createConnectionToMySQL();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new UserModel();
                user.setUID(resultSet.getInt("UID"));
                user.setUsername(resultSet.getString("Username"));
                user.setPassword(resultSet.getString("Password"));
                user.setName(resultSet.getString("Name"));
                user.setAdmin(resultSet.getBoolean("Admin"));
                user.setTeacher(resultSet.getBoolean("Teacher"));
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return user;
    }
}
