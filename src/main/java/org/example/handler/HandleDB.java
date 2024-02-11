package org.example.handler;

import org.example.AdminMethods;
import org.example.UserMethods;
import org.example.model.Clothes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HandleDB implements UserMethods, AdminMethods {
    @Override
    public ArrayList<String> Select(Connection con) throws SQLException {
        ArrayList<String> clothesArr = new ArrayList<>();
        String sql = "SELECT id, brand, model, type, color, size, price FROM clothes ORDER BY id;";
        Statement stmt = con.createStatement(); // creating a SQL statement
        ResultSet rs = stmt.executeQuery(sql); // executing it

        while (rs.next()) { // handling the response
            int id = rs.getInt("id");
            String brand = rs.getString("brand");
            String model = rs.getString("model");
            String type = rs.getString("type");
            String color = rs.getString("color");
            String size = rs.getString("size");
            int price = rs.getInt("price");

            Clothes clothes = new Clothes(id, brand, model, type, color, size, price); // creating new clothes object
            clothesArr.add(clothes.toString());
        }
        if(!clothesArr.isEmpty()) { // check if the clothes is not empty
            return clothesArr; // return all clothes
        } else {
            // No rows were returned
            System.out.println("No rows found.");
            return null;
        }
    }

    @Override
    public ArrayList<String> Select(Connection con, String column, String data) throws SQLException {
        ArrayList<String> clothesArr = new ArrayList<>();
        String sql = "SELECT id, brand, model, type, color, size, price FROM clothes WHERE " + column + " = '" + data +"' ORDER BY id;";
        Statement stmt = con.createStatement(); // creating a SQL statement
        ResultSet rs = stmt.executeQuery(sql); // executing it

        while (rs.next()) { // handling the response
            int id = rs.getInt("id");
            String brand = rs.getString("brand");
            String model = rs.getString("model");
            String type = rs.getString("type");
            String color = rs.getString("color");
            String size = rs.getString("size");
            int price = rs.getInt("price");

            Clothes clothes = new Clothes(id, brand, model, type, color, size, price); // creating new clothes object
            clothesArr.add(clothes.toString());
        }
        if(!clothesArr.isEmpty()) { // check if the clothes is not empty
            return clothesArr; // return all clothes
        } else {
            // No rows were returned
            System.out.println("No rows found.");
            return null;
        }
    }

    @Override
    public void Create(Connection con, String brand, String model, String type, String color, String size, int price) throws SQLException {
        String sql = "INSERT INTO clothes (brand,model,type,color,size,price) VALUES ('" + brand +"', '" + model + "', '" + type + "', '" + color + "', '" + size + "', " + String.valueOf(price) + " );";
        Statement stmt = con.createStatement(); // creating a SQL statement
        int rs = stmt.executeUpdate(sql); // executing it, without a response
    }

    @Override
    public void Update(Connection con, String column, String value) throws SQLException {
        String query = null;
        if(column.equals("price")) { // deciding which SQL statement to use
            query = "UPDATE clothes SET price = " + value + " ;";
        } else {
            query = "UPDATE clothes SET " + column + " = '" + value + "' ;";
        }
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(query); // executing
    }

    @Override
    public void Update(Connection con, String column, String value, String targetColumn, String targetValue) throws SQLException {
        String sql = null;
        if(column.equals("price")) { // deciding which SQL statement to use
            if(targetColumn.equals("price")) {
                sql = "UPDATE clothes SET " + column + " = " + value + " WHERE price = " + String.valueOf(targetValue) + ";";
            } else {
                sql = "UPDATE clothes SET " + column + " = " + value + " WHERE " + targetColumn + " = '" + String.valueOf(targetValue) + "';";
            }
        } else {
            if(targetColumn.equals("price")) {
                sql = "UPDATE clothes SET " + column + " = '" + value + "' WHERE price = " + String.valueOf(targetValue) + ";";
            } else {
                sql = "UPDATE clothes SET " + column + " = '" + value + "' WHERE " + targetColumn + " = '" + String.valueOf(targetValue) + "';";
            }
        }
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(sql); // executing
    }

    @Override
    public void Delete(Connection con, int id) throws SQLException {
        String sql = "DELETE FROM clothes WHERE id = " + String.valueOf(id) + " ;";
        Statement stmt = con.createStatement(); // creating a SQL statement
        int rs = stmt.executeUpdate(sql); // executing it
    }
}
