package org.example;

import org.example.data.ConnectionDB;

import java.sql.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        try {
            Connection con = ConnectionDB.Connect(); // get connection
            Auth.Auth(con); // authorisation
        } catch(SQLException e) { // Logging the exceptions
            System.out.println("connection error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}