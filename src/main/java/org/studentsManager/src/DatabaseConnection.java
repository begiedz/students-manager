package org.studentsManager.src;

import org.studentsManager.src.exceptions.DatabaseConnectionException;

import java.sql.*;

// Klasa obsługująca połączenie z bazą danych MySQL
public class DatabaseConnection {
    // Adres URL do bazy danych
    private static final String url = "jdbc:mysql://localhost:3306/students-manager";

    // Nazwa użytkownika do połączenia z bazą danych
    private static final String username = "root";

    // Hasło do połączenia z bazą danych
    private static final String password = "";

    // Statyczny blok, który ładuje sterownik MySQL podczas pierwszego załadowania klasy
    // Jeśli nie zastosowalibyśmy bloku statycznego, musielibyśmy za każdym razem ręcznie ładować sterownik w każdej metodzie, co byłoby mniej wydajne i bardziej złożone.
    static {
        try {
            // Rejestracja sterownika JDBC dla MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.err.println("MySQL Driver not found: " + e);
        }
    }

    // Tworzy i zwraca połączenie z bazą danych wraz z wyjątkami
    public static Connection getConnection() throws DatabaseConnectionException{
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            if (connection == null || connection.isClosed()){
                throw new DatabaseConnectionException("Failed to establish connection to the database.");
            }
            return connection;
        } catch (SQLException e){
            throw new DatabaseConnectionException(e.getMessage());
        }
    }
}
