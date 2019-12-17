package View_Controller;

import Model.Inhouse;
import Model.Inventory;

import Model.Outsourced;
import Model.Part;
import Model.Product;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.max;
import static java.lang.reflect.Array.get;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
public class MainScreen implements Initializable {

    
    @FXML
    private Button addBtnPartsMainScreen;
    @FXML
    private Button deleteBtnPartsMainScreen;
    @FXML
    private TextField searchTextFieldMainScreen;
    @FXML
    private Button searchBtnPartsMainScreen;

    @FXML
    private Button addBtnProductsMainScreen;
    @FXML
    private Button modifyBtnProductsMainScreen;
    @FXML
    private Button deleteBtnProductsMainScreen;
    @FXML
    private TextField searchTextFieldProductsMainScreen;
    @FXML
    private Button searchProductsBtnMainScreen;
   
    @FXML
    private Button exitBtnMainScreen;
    @FXML
    private TableView<Part> partTableViewMainScreen;
    @FXML
    private TableColumn<?, ?> partIdTableViewMainScreen;
    @FXML
    private TableColumn<?, ?> partNameTableViewMainScreen;
    @FXML
    private TableColumn<?, ?> inventoryLevelTableViewMainScreen;
    @FXML
    private TableColumn<?, ?> pricePerUnitPartTableViewMainScreen;
    @FXML
    private TableView<Product> productTableViewMainScreen;
    @FXML
    private TableColumn<?, ?> productIdTableViewMainScreen;
    @FXML
    private TableColumn<?, ?> productNameTableViewMainScreen;
    @FXML
    private TableColumn<?, ?> inventoryLevelTableViewProductsMainScreen;
    @FXML
    private TableColumn<?, ?> pricePerUnitTableProductsMainScreen;



    
   
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
  

    
    partTableViewMainScreen.setItems(Inventory.getAllParts());
    productTableViewMainScreen.setItems(Inventory.getAllProducts());
    
    partIdTableViewMainScreen.setCellValueFactory(new PropertyValueFactory<>("id"));
    partNameTableViewMainScreen.setCellValueFactory(new PropertyValueFactory<>("name"));
    inventoryLevelTableViewMainScreen.setCellValueFactory(new PropertyValueFactory<>("stock"));
    pricePerUnitPartTableViewMainScreen.setCellValueFactory(new PropertyValueFactory<>("price"));
    
    productIdTableViewMainScreen.setCellValueFactory(new PropertyValueFactory<>("id"));
    productNameTableViewMainScreen.setCellValueFactory(new PropertyValueFactory<>("name"));
    inventoryLevelTableViewProductsMainScreen.setCellValueFactory(new PropertyValueFactory<>("stock"));
    pricePerUnitTableProductsMainScreen.setCellValueFactory(new PropertyValueFactory<>("price"));

    Part FS = new Inhouse( AddPartScreenController.count.incrementAndGet(),"front shock", 450.00,  2, 1, 100, 450 );
    
    Inventory.addPart(FS);
    Inventory.addPart(new Inhouse( AddPartScreenController.count.incrementAndGet(), "supercharger", 7450.00, 3, 1, 10, 25 ));
    Inventory.addPart(new Inhouse( AddPartScreenController.count.incrementAndGet(), "turbocharger", 6450.00, 1, 3, 22, 55  ));
    
    Part SSM = new Inhouse( AddPartScreenController.count.incrementAndGet(), "ss muffler", 350.00, 2, 1, 16, 200 );
    Inventory.addPart(SSM);
    
    
    Inventory.addPart(new Outsourced( AddPartScreenController.count.incrementAndGet(),"shock", 450.00,  2, 1, 100, "Koni" ));
    Inventory.addPart(new Outsourced( AddPartScreenController.count.incrementAndGet(), "supercharger", 7450.00, 3, 1, 25, "KraftWerks" ));
    Inventory.addPart(new Outsourced( AddPartScreenController.count.incrementAndGet(), "supercharger", 6450.00, 1, 3, 22, "Comptech"  ));
    Inventory.addPart(new Outsourced( AddPartScreenController.count.incrementAndGet(), "muffler", 350.00, 2, 1, 200, "Skunk2" ));

    Product winch = new Product( AddProductScreenController.count.incrementAndGet(), "winch", 375.00, 7, 2, 9);
    winch.addAssociatedPart(FS);
    Inventory.addProduct(winch);
    
    Product  hangers = new Product( AddProductScreenController.count.incrementAndGet(), " hangers", 29.00, 9, 3, 24);
    hangers.addAssociatedPart(SSM);
    Inventory.addProduct(hangers);

    
    }
    @FXML
    private void addPart(ActionEvent event) throws IOException {
        partTableViewMainScreen.setItems(Inventory.getAllParts());

        Parent parent = FXMLLoader.load(getClass().getResource("/View_Controller/AddPartScreen.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private boolean flag = true;
    private ObservableList <Part>flagParts = FXCollections.observableArrayList();
    @FXML
    private void modifyHandler(ActionEvent event) throws IOException {
        //get selected item from parts
         Part selectedPart = partTableViewMainScreen.getSelectionModel().getSelectedItem();
        //if item is null return
        if(selectedPart == null){
            return;
        }
        partTableViewMainScreen.setItems(Inventory.getAllParts());
        ModifyPartScreenController.setPartModify(selectedPart);
        //bring up modify screen
        Parent parent = FXMLLoader.load(getClass().getResource("/View_Controller/ModifyPartScreen.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void deleteHandler(ActionEvent event) {
        Part   dynamicPartRemover = partTableViewMainScreen.getSelectionModel().getSelectedItem();
        if (   dynamicPartRemover == null)
            return;

        (Inventory.getAllParts()).remove(dynamicPartRemover);

        partTableViewMainScreen.setItems(Inventory.getAllParts());

    }


    @FXML
    private void exitHandler(ActionEvent event) {
        Stage stage =(Stage) exitBtnMainScreen.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addProductHandler(ActionEvent event) throws IOException {
        productTableViewMainScreen.setItems(Inventory.getAllProducts());
        Parent parent = FXMLLoader.load(getClass().getResource("/View_Controller/AddProductScreen.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    
    
    }

    @FXML
    private void modifyProductHandler(ActionEvent event) throws IOException {
        
        //get selected item from parts
        Product selectedProduct = productTableViewMainScreen.getSelectionModel().getSelectedItem();
        //if item is null return
        if(selectedProduct == null){
            return;
        }
        productTableViewMainScreen.setItems(Inventory.getAllProducts());
        //pass selected items
        ModifyProductScreenController.setProductModify(selectedProduct);

        Parent parent = FXMLLoader.load(getClass().getResource("/View_Controller/ModifyProductScreen.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void deleteProductHandler(ActionEvent event) {

        Product   dynamicProductRemover = productTableViewMainScreen.getSelectionModel().getSelectedItem();
        if (   dynamicProductRemover == null)
            return;

        (Inventory.getAllProducts()).remove(dynamicProductRemover);
        productTableViewMainScreen.setItems(Inventory.getAllProducts());
    }

    @FXML
    private void searchHandlerParts(ActionEvent event) {

        String x = searchTextFieldMainScreen.getText();
        // get string from search text field
        ObservableList < Part > list = Inventory.lookupPart(x);
        System.out.println(list.size());
        partTableViewMainScreen.setItems(list);

        // set items parts table  look at initialize

    }

    @FXML
    private void searchHandlerProducts(ActionEvent event) {
        //String x = "super";
        String x = searchTextFieldProductsMainScreen.getText();
        // get string from search text field
        ObservableList < Product > list = Inventory.lookupProduct(x);
        System.out.println(list.size());
        productTableViewMainScreen.setItems(list);

    }
}

    
