/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Avis;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import services.AvisService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterAvisFXMLController implements Initializable {

    @FXML
    private Button btngetback;
    @FXML
    private TextField txtdescavis;
    @FXML
    private Button btnajt;
    @FXML
    private RadioButton rd1;
    @FXML
    private RadioButton rd5;
    @FXML
    private RadioButton rd4;
    @FXML
    private RadioButton rd3;
    @FXML
    private RadioButton rd2;
    ToggleGroup etoile = new ToggleGroup();
    int choix =5;
    Avis a = new Avis();
    AvisService as = new AvisService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rd5.setSelected(true);
        rd1.setToggleGroup(etoile);
        rd2.setToggleGroup(etoile);
        rd3.setToggleGroup(etoile);
        rd4.setToggleGroup(etoile);
        rd5.setToggleGroup(etoile);
    }    


    @FXML
    private void loadMenu(ActionEvent event) {
        displayMenu();
    }
    
    private void displayMenu(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuReclamationAvis.fxml"));
            Parent root =loader.load();
            MenuReclamationAvisController mr = loader.getController();
            btngetback.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajoutavis(ActionEvent event) {
        String desc_avis = txtdescavis.getText();
        a.setDesc_avis(desc_avis);
        a.setEtoile(choix);
        a.setId_hbg(1);
        a.setId_user(21);
        as.ajouterAvis(a);
        displayMenu();
    }

    @FXML
    private void setEtoile1(ActionEvent event) {
        choix = 1;
    }

    @FXML
    private void setEtoile5(ActionEvent event) {
        choix = 5;
    }

    @FXML
    private void setEtoile4(ActionEvent event) {
        choix = 4;
    }

    @FXML
    private void setEtoile3(ActionEvent event) {
        choix = 3;
    }

    @FXML
    private void setEtoile2(ActionEvent event) {
        choix = 2;
    }
    
}
