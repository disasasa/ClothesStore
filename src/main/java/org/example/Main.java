package org.example;

import org.example.handler.AdminConsole;
import org.example.handler.UserConsole;

import java.sql.*;
import java.util.Scanner;


public class Main extends AdminConsole {
    public static void main(String[] args)  {
        String connectionString = "jdbc:postgresql://localhost:5432/postgres";
        Scanner sc = new Scanner(System.in);
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(connectionString, "postgres", "0000"); // creating the connection
            while(true) { // authentication
                System.out.println("Register: \n1 - authorise\n2 - register");
                String choice = sc.nextLine();
                switch(choice) {
                    case "1":
                        System.out.println("enter your name");
                        String name = sc.nextLine();
                        System.out.println("enter your password");
                        String password = sc.nextLine();
                        if (verifyUser(con, name, password)) {
                            System.out.println("Authentication successful");
                            if(name.equals("admin")) { // auth as admin
                                AdminConsole handler = new AdminConsole();
                                handler.polling(con);

                            } else { // auth as user
                                UserConsole handler = new UserConsole();
                                handler.polling(con);
                            }
                        }
                        break;
                    case "2":
                        System.out.println("enter your name");
                        String Name = sc.nextLine();
                        System.out.println("create new password");
                        String Password = sc.nextLine();

                        if(checkUser(con, Name, Password)) {//checks if user exists
                            System.out.println("New user was created");
                        } else {
                            System.out.println("User is already created");
                        }
                }
            }
        } catch(SQLException e) { // Logging the exceptions
            System.out.println("connection error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    //Authorization
    private static boolean verifyUser(Connection con, String name, String password) throws SQLException {
        String query = "SELECT password FROM users WHERE name = '" + name + "' ;";
        Statement stmt = con.createStatement(); // creating a SQL statement
        ResultSet rs = stmt.executeQuery(query); // executing it
        if (rs.next()) {
            String rsPassword = rs.getString("password").trim(); // erasing unnecessary spaces
            if(password.equals(rsPassword)) {
                return true;
            } else {
                System.out.println("the password is incorrect");
                return false;
            }
        } else {
            System.out.println("User not found");
            return false;
        }
    }

    private static boolean checkUser(Connection con, String name, String password) throws SQLException {
        String query = "SELECT name FROM users WHERE name = '" + name + "' ;";
        Statement stmt = con.createStatement(); // creating a SQL statement
        ResultSet rs = stmt.executeQuery(query); // executing it
        if (rs.next()) {
            return false;
        } else {
            String Query = "INSERT INTO users (name, password) VALUES ('" + name + "', '" + password + "') ;";
            Statement Stmt = con.createStatement(); // creating a SQL statement
            int Rs = stmt.executeUpdate(Query); // executing it
            return true;
        }
    }
}
