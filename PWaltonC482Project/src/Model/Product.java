/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

//import static Model.Product.product;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author paulwalton
 */
public class Product {
    private ObservableList<Part>associatedParts;
    private int id;

    private int min;
    private int max;



    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private SimpleIntegerProperty stock = new SimpleIntegerProperty();



    
    public Product (int id, String name, double price,int stock, int min , int max){
        this.associatedParts = FXCollections.observableArrayList();
        this.id = id;
        this.name.set(name);

        this.price.set(price);
        this.stock.set(stock);
        this.min = min;
        this.max = max;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    public StringProperty nameProperty(){
        return name;
    }

    public double getPrice() {
        return price.get();
    }
 // this was converting to simple string object so we can see updates in table modifying values
    public void setPrice(double price) {
        this.price.set(price);
    }
    public DoubleProperty priceProperty(){
        return price;
    }

    public int getStock() {
        return stock.get();
    }
    public IntegerProperty stockProperty(){
        return stock;
    }


    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }



    public void addAssociatedPart(Part part){
        this.associatedParts.add(part);
       
    }
    public void deleteAssociatedPart(Part associatedPart){
       
    }
    // Exception #1
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
    // work on both parts and products - global
    public static boolean validateMax(int max, int min){
        if (max >= min)
            return true;
        else
            return false;

    }



}
    



