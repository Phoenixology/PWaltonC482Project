/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author paulwalton
 */
public class AddPartScreenController implements Initializable {
public static final AtomicInteger count = new AtomicInteger(0);
  



    @FXML
    private RadioButton outsourcedRadioBtnAddPartScreen;
    @FXML
    private Label maxNumUnitsAddPartScreen;
    @FXML
    private Label co;
    @FXML
    private Label minNumUnitsAddPartScreen;
    @FXML
    private TextField idTextFieldAddPartScreen;
    @FXML
    private TextField nameTextFieldAddPartScreen;
    @FXML
    private TextField invTextFieldAddPartScreen;
    @FXML
    private TextField maxFieldAddPartScreen;
    @FXML
    private TextField minTextFieldAddPartScreen;
    @FXML
    private TextField companyNameIfOutsourcedAddPartScreen;
    @FXML
    private TextField priceCostTextFieldAddPartScreen;
    @FXML
    private Button saveBtnAddPartScreen;
    @FXML
    private Button cancelBtnAddPartScreen;
    @FXML
    private RadioButton inHouseRadioBtnAddPartScreen;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    boolean entered = true;

    }

    @FXML
    public void displayCompanyName(ActionEvent event) {
        co.setText("Company Name");
                
    }


    @FXML
    private void displayMachineId(ActionEvent event) {
       co.setText("Machine Id");
    }


    @FXML
    private void saveHandler(ActionEvent event) {

        String name = nameTextFieldAddPartScreen.getText();
        int inv = Integer.parseInt(invTextFieldAddPartScreen.getText());
        double price =Double.parseDouble(priceCostTextFieldAddPartScreen.getText());
        int max = Integer.parseInt(maxFieldAddPartScreen.getText());
        int min = Integer.parseInt(minTextFieldAddPartScreen.getText());
        boolean isOutsourced;
        if (!(Product.validateMax(max,min))) {

            Alert alert = new Alert((Alert.AlertType.ERROR));
            alert.setTitle(("Error Dialog"));
            alert.setContentText("Max must not be less than min!");
            alert.showAndWait();
            return;
        }
    
        int id = count.incrementAndGet();
        
        

        if(outsourcedRadioBtnAddPartScreen.isSelected())
            isOutsourced = true;
        else 
            isOutsourced = false;
        
        if(isOutsourced) {
            String companyName = companyNameIfOutsourcedAddPartScreen.getText();
             
            Outsourced part = new Outsourced(id,name, price, inv,min, max, companyName);
            Inventory.addPart(part);
        }
        else if (!isOutsourced){
            int machId = Integer.parseInt(companyNameIfOutsourcedAddPartScreen.getText());
            Inhouse part = new Inhouse(id, name, price, inv, min, max, machId);
            Inventory.addPart(part);
        
         
        }
        
        Stage stage =(Stage) cancelBtnAddPartScreen.getScene().getWindow();
        stage.close();
        
    }


    @FXML
    private void cancelHandler(ActionEvent event) {
         Stage stage =(Stage) cancelBtnAddPartScreen.getScene().getWindow();
         stage.close();
    }

 
}
