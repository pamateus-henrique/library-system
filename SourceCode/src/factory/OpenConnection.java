package factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class OpenConnection {
    public static Connection createConnectionToMySQL() throws Exception{

        //ENV VARS
        final String USERNAME = "youruser";
        final String PASSWORD = "yourpassword";
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/library";

        //opens the DB connection and returns and instance
        return DriverManager.getConnection(DATABASE_URL, USERNAME,PASSWORD);
    }
}
