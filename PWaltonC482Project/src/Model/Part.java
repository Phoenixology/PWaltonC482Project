/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

/**
 *
 * @author paulwalton
 */
public abstract class Part {
    private int id;
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private SimpleIntegerProperty stock = new SimpleIntegerProperty();

    
   private int min;
   private int max;

   
   
   public Part (int id, String name, double price, int stock, int min, int max){
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
// secret handshake add  in products
    public void setName(String name) {
        this.name.set(name);
    }
    public StringProperty nameProperty(){
       return name;
    }


    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }
    public IntegerProperty stockProperty(){
       return stock;
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

    // Exception 1

       
}
   
   
   
   


   
