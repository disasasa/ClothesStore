package org.example.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class AdminConsole extends HandleDB{
    Scanner sc = new Scanner(System.in);
    public void polling(Connection con) {
        while(true) {
            try {
                System.out.println("select actions:\n1 - select all clothes\n2 - create new clothes\n3 - update clothes\n4 - delete clothes\n");
                String chooce = sc.nextLine(); // do not use nextInt() as it cause a problem with IOStream
                switch(chooce) {
                    case "1":
                        System.out.println(Select(con));
                        break;
                    case "2":
                        System.out.println("enter a brand");
                        String brand = sc.nextLine();
                        System.out.println("enter a model");
                        String model = sc.nextLine();
                        System.out.println("enter a type");
                        String type = sc.nextLine();
                        System.out.println("enter a color");
                        String color = sc.nextLine();
                        System.out.println("enter a size");
                        String size = sc.nextLine();
                        System.out.println("enter a price");
                        int price = Integer.parseInt(sc.nextLine());
                        Create(con,brand,model,type,color,size,price);
                        break;
                    case "3":
                        System.out.println("which column to choose - brand, model, type, color, size, price");
                        String column = sc.nextLine();
                        System.out.println("enter a value");
                        String value = sc.nextLine();
                        System.out.println("update all clothes (y/n)");
                        String choose = sc.nextLine();
                        if(choose.equals("y")) {
                            Update(con, column, value);
                        } else {
                            System.out.println("enter a column by which you want to pick clothes");
                            String targetColumn = sc.nextLine();
                            System.out.println("enter a value by which you want to pick clothes");
                            String targetValue = sc.nextLine();
                            Update(con,column,value,targetColumn,targetValue);
                        }
                        break;
                    case "4":
                        System.out.println("enter an ID");
                        int id = Integer.parseInt(sc.nextLine());
                        Delete(con,id);
                        break;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
