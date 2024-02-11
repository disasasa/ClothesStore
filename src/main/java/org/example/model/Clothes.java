package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor // use of Lombok library
@NoArgsConstructor
@Getter
@Setter
public class Clothes {
    private int id;
    private String brand;
    private String model;
    private String type;
    private String color;
    private String size;
    private int price;

    @Override
    public String toString() {
        return "\nID: " + id +
                "\nBrand: " + brand +
                "\nModel: " + model +
                "\nType: " + type +
                "\nColor: " + color +
                "\nSize: " + size +
                "\nPrice: " + price + "\n";
    }
}
