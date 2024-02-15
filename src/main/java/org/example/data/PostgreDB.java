package org.example.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static Connection Connect() throws SQLException, ClassNotFoundException {
        String connectionString = "jdbc:postgresql://localhost:5432/simpledb";
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(connectionString, "postgres", "0000"); // creating the connection
        return con;
    }
}
