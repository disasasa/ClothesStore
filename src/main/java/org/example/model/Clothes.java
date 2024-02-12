package org.example.model;
// Lombok annotations to reduce boilerplate code in Java classes.
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor // use of Lombok library
@NoArgsConstructor
@Getter
@Setter
public class Clothes {
    // Definition of fields representing properties of a clothing item
    private int id;
    private String brand;
    private String model;
    private String type;
    private String color;
    private String size;
    private int price;

    @Override
    public String toString() {
        // Overrides the default toString method to provide a custom string representation of the Clothes object.
        return "\nID: " + id +
                "\nBrand: " + brand +
                "\nModel: " + model +
                "\nType: " + type +
                "\nColor: " + color +
                "\nSize: " + size +
                "\nPrice: " + price + "\n";
    }
}
