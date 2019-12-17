/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.*;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author paulwalton
 */
public class ModifyPartScreenController implements Initializable {

    
    

    Stage stage;
    Parent scene;
    @FXML
    private RadioButton inHousePartBtnModifyPartScreen;
    @FXML
    private RadioButton outsourcedPartRadioBtnModifyPartScreen;
    @FXML
    private TextField idTextFieldModifyPartScreen;
    @FXML
    private TextField nameTextFieldModifyPartScreen;
    @FXML
    private TextField invTextFieldModifyPartScreen;
    @FXML
    private TextField priceCostTextFieldModifyPartScreen;
    @FXML
    private TextField maxNumUnitsModifyPartScreen;
    @FXML
    private TextField companyNameIfOutsourcedModifyPartScreen;
    @FXML
    private TextField minNumUnitsModifyPartsScreen;
    @FXML
    private Button saveBtnModifyPartScreen;
    @FXML
    private Button cancelBtnModifyPartScreen;
    private static Part partModify = null;
    public static void setPartModify(Part p){
        partModify = p;
    }
    @FXML
    private ToggleGroup radioBtnsModPrt;
    @FXML
    private Label co;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameTextFieldModifyPartScreen.setText(partModify.getName());
        idTextFieldModifyPartScreen.setText(Integer.toString(partModify.getId()));
        invTextFieldModifyPartScreen.setText(Integer.toString(partModify.getStock()));
        priceCostTextFieldModifyPartScreen.setText(Double.toString(partModify.getPrice()));
        maxNumUnitsModifyPartScreen.setText(Integer.toString(partModify.getMax()));
        minNumUnitsModifyPartsScreen.setText(Integer.toString(partModify.getMin()));
     
        if(partModify instanceof Inhouse){
            co.setText("Machine Id");
            inHousePartBtnModifyPartScreen.setSelected(true);
            Inhouse homemade = (Inhouse) partModify;
            companyNameIfOutsourcedModifyPartScreen.setText(Integer.toString(homemade.getMachineId()));
        }
        else if(partModify instanceof Outsourced){
            co.setText("Company Name");
            outsourcedPartRadioBtnModifyPartScreen.setSelected(true);
            Outsourced imported = (Outsourced) partModify;
            companyNameIfOutsourcedModifyPartScreen.setText(imported.getCompanyName());
        }
    }    

    @FXML
    private void displayMachineId(ActionEvent event) {
        co.setText("Machine Id");

    }

    @FXML
    private void displayCompanyName(ActionEvent event) {
        co.setText("Company Name");
    }


    @FXML
    private void saveHandler(ActionEvent event) {
        int id = Integer.parseInt(idTextFieldModifyPartScreen.getText());
        String name = nameTextFieldModifyPartScreen.getText();
        int inv = Integer.parseInt(invTextFieldModifyPartScreen.getText());
        double price =Double.parseDouble(priceCostTextFieldModifyPartScreen.getText());
        int max = Integer.parseInt(maxNumUnitsModifyPartScreen.getText());
        int min = Integer.parseInt(minNumUnitsModifyPartsScreen.getText());

        if (!(Product.validateMax(max,min))) {

            Alert alert = new Alert((Alert.AlertType.ERROR));
            alert.setTitle(("Error Dialog"));
            alert.setContentText("Max must not be less than min!");
            alert.showAndWait();
            return;
        }
        
        boolean isOutsourced = outsourcedPartRadioBtnModifyPartScreen.isSelected();
        if(isOutsourced){
           if(partModify instanceof Outsourced){ // modify part modify
              partModify.setName(name);
              partModify.setStock(inv);
              partModify.setPrice(price);
              partModify.setMax(max);
              partModify.setMin(min);
              Outsourced imported = (Outsourced) partModify;
              imported.setCompanyName(companyNameIfOutsourcedModifyPartScreen.getText());
           }
           else {

               // create outSourced part, remove old part modify
               Inventory.getAllParts().remove(partModify);
               // public Outsourced (int id, String name, double price, int stock, int min, int max, String companyName)
               Outsourced imported = new Outsourced( id, name, price, inv, min, max, companyNameIfOutsourcedModifyPartScreen.getText() );
               Inventory.addPart(imported);

           }
        }
        else { // is inhouse 
            if(partModify instanceof Inhouse){ // modify part modify
               partModify.setName(name);
               partModify.setStock(inv);
               partModify.setPrice(price);
               partModify.setMax(max);
               partModify.setMin(min);
               Inhouse homemade = (Inhouse) partModify;
               homemade.setMachineId(Integer.parseInt(companyNameIfOutsourcedModifyPartScreen.getText()));
            }
            else   // create inHouse part, remove old part modify
            {

                int MachineId =Integer.parseInt(companyNameIfOutsourcedModifyPartScreen.getText());
                Inventory.getAllParts().remove(partModify);

                Inhouse homemade = new Inhouse (id, name, price, inv, min, max, MachineId);
                Inventory.addPart(homemade);
            }
            
        }
        Stage stage =(Stage) cancelBtnModifyPartScreen.getScene().getWindow();
        stage.close();
       
    }


    @FXML
    private void cancelHandler(ActionEvent event) {
        Stage stage =(Stage) cancelBtnModifyPartScreen.getScene().getWindow();
        stage.close();
    }
    
}
