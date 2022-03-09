/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Avis;

import Entités.Avis;
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
import org.controlsfx.control.Rating;
import Service.AvisService;
import javafx.scene.layout.AnchorPane;

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

    int choix = 5;
    Avis a = new Avis();
    AvisService as = new AvisService();
    @FXML
    private Rating rating;
    @FXML
    private AnchorPane mainPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void loadMenu(ActionEvent event) {
        displayMenu();
    }

    private void displayMenu() {
        try {
        AnchorPane pane;
        pane = FXMLLoader.load(getClass().getResource("../Avis/AfficherAvisFXML.fxml"));

        mainPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.out.println("error in redirect");
        }
    }

    @FXML
    private void ajoutavis(ActionEvent event) {

        String desc_avis = txtdescavis.getText();
        a.setDesc_avis(desc_avis);
        a.setEtoile((int) rating.getRating());
        a.setId_hbg(1);
        a.setId_user(21);
        as.ajouterAvis(a);
        displayMenu();
    }

}