package org.example.handler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserConsole extends HandleDB {
    Scanner sc = new Scanner(System.in);
    public void polling(Connection con) {
        while(true) {
            try {
                System.out.println("select a clothes by:\n1 - select all\n2 - select by brand\n3 - select by model\n4 - select by type\n5 - select by color\n6 - select by size\n7 - select by price");
                String chooce = sc.nextLine(); // do not use nextInt() as it cause a problem with console
                switch(chooce) {
                    case "1":
                        System.out.println(Select(con));
                        break;
                    case "2":
                        SeeAvailable(con,"brand");
                        break;
                    case "3":
                        SeeAvailable(con,"model");
                        break;
                    case "4":
                        SeeAvailable(con,"type");
                        break;
                    case "5":
                        SeeAvailable(con,"color");
                        break;
                    case "6":
                        SeeAvailable(con,"Size");
                        break;
                    case "7":
                        try {
                            System.out.println(SelectByDiapason(con));
                        } catch(SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Invalid option selected");
                        break;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private static void SeeAvailable(Connection con, String column) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Which " + column + " do you want to choose");
            String query = "SELECT DISTINCT " + column + " FROM clothes";
            Statement stmt = con.createStatement(); // creating a SQL statement
            ResultSet rs = stmt.executeQuery(query); // executing it
            ArrayList<String> unique = new ArrayList<>(); // needed for storing unique values
            while(rs.next()) {
                String brand = rs.getString(column).trim();
                unique.add(brand);
            }
            String message = "Unique " + column + ":";
            for(int i =0;i<unique.size();i++) {
                message = message + " " + unique.get(i);
            }
            System.out.println(message);
            String chooce = sc.nextLine();
            HandleDB handler = new HandleDB();
            System.out.println(handler.Select(con,column,chooce));
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private static ArrayList<String> SelectByDiapason(Connection con) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select a price diapason");
        String MaxQuery = "SELECT FROM clothes ORDER BY price DESC LIMIT 1;";
        String MinQuery = "SELECT FROM clothes ORDER BY price ASC LIMIT 1;";
        Statement stmt = con.createStatement();
        ResultSet maxrs = stmt.executeQuery(MaxQuery);
        ResultSet minrs = stmt.executeQuery(MinQuery);
        maxrs.next();
        minrs.next();
        int max = maxrs.getInt("price");
        int min = minrs.getInt("price");
        System.out.println(String.format("The most Expensive - %s, The Cheapest - %s",max,min));
        System.out.println("Max - \n");
        int MAX = Integer.parseInt(sc.nextLine());
        System.out.println("Min - \n");
        int MIN = Integer.parseInt(sc.nextLine());
        return new HandleDB().Select(con,MIN,MAX);
    }
}
