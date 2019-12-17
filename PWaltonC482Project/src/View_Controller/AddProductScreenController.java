/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inhouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import Model.Product;
import static View_Controller.AddPartScreenController.count;
//import /*static*/ View_Controller.AddProductScreenController.product;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
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
public class AddProductScreenController implements Initializable {

    public static final AtomicInteger count = new AtomicInteger(0);


    @FXML
    private Button searchBtnAddProductScreen;
    @FXML
    private Button addBtnAddProductScreen;
    @FXML
    private Button deleteBtnAddProductScreen;
    @FXML
    private Button cancelBtnAddProductScreen;
    @FXML
    private Button saveBtnAddProductScreen;
    @FXML
    private TextField idTextFieldAddProductScreen;
    @FXML
    private TextField nameTextFieldAddProductScreen;
    @FXML
    private TextField invTextFieldAddProductScreen;
    @FXML
    private TextField priceTextFieldAddProductScreen;
    @FXML
    private TextField searchProductTextFieldAddProductScreen;
    @FXML
    private TableView<Part> tableViewAddProductSearchScreen;// upper table
    @FXML
    private TableView<Part> addSaveTableViewAddProductScreen; //lower table
    @FXML
    private TextField maxInvAddProduct;
    @FXML
    private TextField minInvAddProduct;
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
    private ObservableList<Part> lowerParts = FXCollections.observableArrayList();
    private ObservableList<Product> upperParts = FXCollections.observableArrayList();
    //public static void product.price => product.cost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //upper table 
        tableViewAddProductSearchScreen.setItems(Inventory.getAllParts());
        uPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        uPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        uInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        uPricePer.setCellValueFactory(new PropertyValueFactory<>("price"));
        //lower table
        addSaveTableViewAddProductScreen.setItems(lowerParts);//
        lPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        lPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        lInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        lPricePer.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    @FXML
    private void searchHandler(ActionEvent event) {



        String x = searchProductTextFieldAddProductScreen.getText();

        ObservableList < Part > list = Inventory.lookupPart(x);
        System.out.println(list.size());
        tableViewAddProductSearchScreen.setItems(list);

    }


    @FXML
    private void addHandler(ActionEvent event) {

        // get selected part
        Part part = tableViewAddProductSearchScreen.getSelectionModel().getSelectedItem();
        //if part = null
        if (part == null)
            return;
        lowerParts.add(part);

    }


    @FXML
    private void deleteHandler(ActionEvent event) {
        Part part = addSaveTableViewAddProductScreen.getSelectionModel().getSelectedItem();
        //if part = null
        if (part == null)
            return;
        lowerParts.remove(part);

    }


    @FXML
    private void cancelHandler(ActionEvent event) {
        Stage stage = (Stage) cancelBtnAddProductScreen.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void saveHandler(ActionEvent event) {
        try {
            String name = nameTextFieldAddProductScreen.getText();
            int inv = Integer.parseInt(invTextFieldAddProductScreen.getText());
            double price = Double.parseDouble(priceTextFieldAddProductScreen.getText());
            int max = Integer.parseInt(maxInvAddProduct.getText());
            int min = Integer.parseInt(minInvAddProduct.getText());
            if (!(Product.validateMax(max,min))) {

                Alert alert = new Alert((Alert.AlertType.ERROR));
                alert.setTitle(("Error Dialog"));
                alert.setContentText("Max must not be less than min!");
                alert.showAndWait();
                return;
            }

            int id = count.incrementAndGet();

            Product product = new Product(id, name, price, inv, min, max);
            product.getAllAssociatedParts().addAll(lowerParts);
            Inventory.addProduct(product);

            Stage stage = (Stage) cancelBtnAddProductScreen.getScene().getWindow();
            stage.close();
            // Exception #2
        } catch(Exception e){
                Alert alert = new Alert((Alert.AlertType.ERROR));
                alert.setTitle(("Error Dialog"));
                alert.setContentText("All fields require valid inputs.Thank you!");
                alert.showAndWait();

            }

        }
    }



        
    
    

