package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/db_event?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; 

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database is Connected..");
            return con;

        } catch (Exception e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            return null;
        }
    }
}