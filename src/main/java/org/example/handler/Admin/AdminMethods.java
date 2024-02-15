package org.example.handler.Admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminMethods {
    public ArrayList<String> Select(Connection con) throws SQLException; // SELECT ALL
    public void Create(Connection con, String brand, String model, String type, String color, String size, int price) throws SQLException; // CREATE new clothes
    public void Update(Connection con, String column, String value) throws SQLException; // Update all clothes
    public void Update(Connection con, String column, String value, String targetColumn, String targetValue) throws SQLException; // Update specific clothes
    public void Delete(Connection con, int id) throws SQLException; // Delete by id
}
