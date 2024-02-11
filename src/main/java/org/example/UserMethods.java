package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserMethods {
    public ArrayList<String> Select(Connection con) throws SQLException; // SELECT ALL
    public ArrayList<String> Select(Connection con, String column, String data) throws SQLException; // SELECT by data in specific column
}
