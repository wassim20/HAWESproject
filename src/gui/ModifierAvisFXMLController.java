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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.controlsfx.control.Rating;
import services.AvisService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifierAvisFXMLController implements Initializable {

    @FXML
    private TextField txtdescavis;
 
    @FXML
    private Button btnmodifavis;
    @FXML
    private Button btngetback;
    ToggleGroup etoile = new ToggleGroup();
    AvisService as = new AvisService();
    Avis avis = new Avis();
    Avis a = new Avis();
    int choix = 0;
    @FXML
    private Rating rating;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void modifierAvis(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Avis modifiée avec succès "); //Succes
        //alert.setContentText("Réclamation Modifié avec succès");
        alert.showAndWait();
        String desc_avis = txtdescavis.getText();
        a.setDesc_avis(desc_avis);
        a.setDateAjoutavis(avis.getDateAjoutavis());
        //a.setDesc_avis(desc_avis);
       // rating.setRating(a.getEtoile());        
 a.setEtoile((int) rating.getRating());
        a.setId_avis(avis.getId_avis());
        a.setId_hbg(avis.getId_hbg());
        a.setId_user(avis.getId_user());
        as.modifierAvis(a, avis.getId_avis());
        displayMenu();
    }

    @FXML
    private void loadMenu(ActionEvent event) {
        displayMenu();
    }
    
    private void displayMenu(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherAvisFXML.fxml"));
            Parent root =loader.load();
           AfficherAvisFXMLController mr = loader.getController();
            btngetback.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void prepare(Avis a) {
  txtdescavis.setText(String.valueOf(a.getDesc_avis()));
 // a.setEtoile((int) rating.getRating());
 rating.setRating(a.getEtoile());  
        avis = a;
    }
    

    
}
