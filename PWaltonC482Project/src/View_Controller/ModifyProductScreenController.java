/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import static View_Controller.AddProductScreenController.count;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author paulwalton
 */

public class ModifyProductScreenController implements Initializable {
    @FXML
    private TableColumn<?, ?> uPartId;
    @FXML
    private TableColumn<?, ?> uPartName;
    @FXML
    private TableColumn<?, ?> uInv;
    @FXML
    private TableColumn<?, ?> uPricePer;
    @FXML
    private TableColumn<?, ?> lPartId;
    @FXML
    private TableColumn<?, ?> lPartName;
    @FXML
    private TableColumn<?, ?> lInv;
    @FXML
    private TableColumn<?, ?> lPricePer;
    @FXML
    private TextField idTextFieldModifyProductScreen;
    @FXML
    private TextField nameTextFieldModifyProductScreen;
    @FXML
    private TextField invTextFieldModifyProductScreen;
    @FXML
    private TextField priceTextFieldModifyProductScreen;
    @FXML
    private TextField minNumTextFieldModifyProductScreen;
    @FXML
    private TextField maxTextFieldModifyProductScreen;
    @FXML
    private Button searchBtnModifyPartScreen;
    @FXML
    private TextField searchTextFieldModifyProductScreen;
    @FXML
    private Button addBtnModifyProductScreen;
    @FXML
    private Button deleteBtnModifyProductScreen;
    @FXML
    private Button cancelBtnModifyProductScreen;
    @FXML
    private Button saveBtnModifyProductScreen;
    private static Product productModify = null;
    public static void setProductModify(Product prod){
        productModify = prod;
    }
     // *Initializes the controller class.
    @FXML
    private TableView<Part> productScreenUpperTblView; /// upper table
    @FXML
    private TableView<Part> productScreenLowerTblView; // lower table
    private ObservableList<Part> lowerParts= FXCollections.observableArrayList();

    @Override

    public void initialize(URL url, ResourceBundle rb)
    {

        //upper table
        productScreenUpperTblView.setItems(Inventory.getAllParts());
        uPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        uPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        uInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        uPricePer.setCellValueFactory(new PropertyValueFactory<>("price"));
        //lower table
        productScreenLowerTblView.setItems(lowerParts);//
        lPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        lPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        lInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        lPricePer.setCellValueFactory(new PropertyValueFactory<>("price"));

        // setting product values
        nameTextFieldModifyProductScreen.setText(productModify.getName());
        idTextFieldModifyProductScreen.setText(Integer.toString(productModify.getId()));
        invTextFieldModifyProductScreen.setText(Integer.toString(productModify.getStock()));
        priceTextFieldModifyProductScreen.setText(Double.toString(productModify.getPrice()));
        maxTextFieldModifyProductScreen.setText(Integer.toString(productModify.getMax()));
        minNumTextFieldModifyProductScreen.setText(Integer.toString(productModify.getMin()));


        lowerParts.addAll(productModify.getAllAssociatedParts());


        productScreenUpperTblView.setItems(Inventory.getAllParts());


    }





    @FXML
    private void searchHandler(ActionEvent event) {
        //String x = "super";  see main parts
        String x = searchTextFieldModifyProductScreen.getText();

        // get string from search text field
        ObservableList < Part > list = Inventory.lookupPart(x);
        System.out.println(list.size());
        productScreenUpperTblView.setItems(list);
    }


    @FXML
    private void addHandler(ActionEvent event) {
        // get selected part
        Part part = productScreenUpperTblView.getSelectionModel().getSelectedItem();
        //if part = null
        if(part == null)
            return;
        lowerParts.add(part);

    }


    @FXML
    private void deleteHandler(ActionEvent event) {
        Part part = productScreenLowerTblView.getSelectionModel().getSelectedItem();

        if(part == null)
            return;
        lowerParts.remove(part);

    }


    @FXML
    private void cancelHandler(ActionEvent event) {
        Stage stage =(Stage) cancelBtnModifyProductScreen.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void saveHandler(ActionEvent event) {
        try {

            String name = nameTextFieldModifyProductScreen.getText();
            if( name.equals("")){
                String X = "Paul";
                String Y =  "Paul 0";
                String Z = "";
                String W = null;
                throw new Exception(" ouch ");


            }
            int inv = Integer.parseInt(invTextFieldModifyProductScreen.getText());
            double price = Double.parseDouble(priceTextFieldModifyProductScreen.getText());
            int max = Integer.parseInt(maxTextFieldModifyProductScreen.getText());
            int min = Integer.parseInt(minNumTextFieldModifyProductScreen.getText());
            if (!(Product.validateMax(max,min))) {

                Alert alert = new Alert((Alert.AlertType.ERROR));
                alert.setTitle(("Error Dialog"));
                alert.setContentText("Max must not be less than min!");
                alert.showAndWait();
                return;
            }
            // modify basic values with setters
            productModify.setName(name);

            productModify.setStock(inv);
            productModify.setPrice(price);
            productModify.setMax(max);
            ;
            productModify.setMin(min);

            // parts stored in lower list
            // remove parts from product
            //
            productModify.getAllAssociatedParts().clear();
            // add lower parts parts
            productModify.getAllAssociatedParts().addAll(lowerParts);

            Stage stage = (Stage) cancelBtnModifyProductScreen.getScene().getWindow();
            stage.close();
            // Exception #2
        } catch (Exception e) {
            Alert alert = new Alert((Alert.AlertType.ERROR));
            alert.setTitle(("Error Dialog"));
            alert.setContentText("All fields require valid inputs. Thank you!");
            alert.showAndWait();


        }

    }
}