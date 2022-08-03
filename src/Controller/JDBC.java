package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    Connection connection = null;
    public JDBC() {

    }
    public Connection connection() {
        try{
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Library?user=postgres&password=berkin00bb");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
