/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherAvisAdminFXMLController implements Initializable {

    @FXML
    private ListView<?> txtlistavis;
    @FXML
    private Button btnsearch;
    @FXML
    private TextField txtsearch;
    @FXML
    private Button btnsuppa;
    @FXML
    private Button btngetback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void chercherAvis(ActionEvent event) {
    }

    @FXML
    private void supprimerAvis(ActionEvent event) {
    }

    @FXML
    private void loadMenu(ActionEvent event) {
    }
    
}
