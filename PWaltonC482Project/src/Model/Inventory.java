/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author paulwalton
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }
    //     return data type (void) method ( addProduct) name input parameters (type Product variable name newProduct)
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        // create new temp list
        ObservableList<Part> tempList = FXCollections.observableArrayList();
        //   search ( walk ) allParts list
        for (Part p : allParts) {
            if (p.getName().contains(partName)) {
                tempList.add(p);
            } // end if

        } //end for
        return (tempList);
        // if partName contains string
        // add to  temp list
        // when done return temp list

    }


    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> tempList = FXCollections.observableArrayList();

        for (Product prod : allProducts) {
            if (prod.getName().contains(productName)) {
                tempList.add(prod);
            } //end if

        } // end for
        return (tempList);// list parts that match into list and return
    }



    public static void deleteProduct(Product selectedProduct) {
        allProducts.remove(selectedProduct);
        return;
    }



    public static Part lookupPart(int partId) {
        return null;
    }



    public static Product lookupProduct( int productId) {
        return null;

    }



    public static void updatePart(int index, Part selectedPart){
            return;
        }



    public static void updateProduct(int  index, Product selectedProduct){
            return;
        }



    public static void deletePart(Part selectedPart){
      return;
    }


}
   

    
    
    
    
    


