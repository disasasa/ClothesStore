package org.example.handler.User;

import org.example.model.Clothes;

import java.sql.*;
import java.util.ArrayList;

public class HandleUserDB implements UserMethods {
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
    public ArrayList<String> Select(Connection con, int min, int max) throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        String query = "SELECT * FROM clothes WHERE price BETWEEN ? AND ?";
        PreparedStatement STMT = con.prepareStatement(query);
        STMT.setInt(1,min);
        STMT.setInt(2,max);
        ResultSet resultSet = STMT.executeQuery();
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String brand = resultSet.getString("brand");
            String model = resultSet.getString("model");
            String type = resultSet.getString("type");
            String color = resultSet.getString("color");
            String size = resultSet.getString("size");
            int price = resultSet.getInt("price");

            Clothes clothes = new Clothes(id,brand,model,type,color,size,price);
            result.add(clothes.toString());
        }
        return result;
    }
}
