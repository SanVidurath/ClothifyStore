package controller.db;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class DBConnection {
    private static DBConnection instance;
    public Connection connection;

    private DBConnection() throws SQLException {
        String mysql = "jdbc:mysql://localhost:3306/clothifystore";
        String user = "root";
        String password = "1234";
        connection = DriverManager.getConnection(mysql,user,password);
    }

    public static DBConnection getInstance() throws SQLException {
        if(instance==null){
            instance=new DBConnection();
        }
        return instance;
    }
}
