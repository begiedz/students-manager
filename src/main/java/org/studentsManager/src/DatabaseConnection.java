package org.studentsManager.src;

import java.sql.*;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/students-manager";
    private static final String username = "root";
    private static final String password = "";

    static {
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e){
            System.err.println("MySQL Driver not found: "+e);
        }
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,username,password);
    }
}
