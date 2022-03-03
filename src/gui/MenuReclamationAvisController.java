/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class MenuReclamationAvisController implements Initializable {

    @FXML
    private Button btnaffa;
    @FXML
    private Button btnajouta;
    @FXML
    private Button btnaffr;
    @FXML
    private Button btnajoutr;
    @FXML
    private Button btnmdr;
    @FXML
    private Button btnsuppr;
    @FXML
    private Button btnmda;
    @FXML
    private Button btnsuppa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherAvis(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherAvisFXML.fxml"));
            Parent root =loader.load();
            AfficherAvisFXMLController tr = loader.getController();
            btnaffa.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    @FXML
    private void ajouterAvis(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterAvisFXML.fxml"));
            Parent root =loader.load();
            AjouterAvisFXMLController tr = loader.getController();
            btnajouta.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @FXML
    private void afficherReclamation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamationFXML.fxml"));
            Parent root =loader.load();
            AfficherReclamationFXMLController tr = loader.getController();
            btnaffr.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @FXML
    private void ajouterReclamation(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReclamationFXML.fxml"));
            Parent root =loader.load();
            AjouterReclamationFXMLController tr = loader.getController();
            btnaffa.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @FXML
    private void modifierReclamation(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamationModifFXML.fxml"));
            Parent root =loader.load();
            AfficherReclamationModifFXMLController tr = loader.getController();
            btnmdr.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @FXML
    private void supprimerReclamation(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SupprimerReclamationFXML.fxml"));
            Parent root =loader.load();
            SupprimerReclamationFXMLController tr = loader.getController();
            btnaffa.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
        
    }

    @FXML
    private void modifierAvis(ActionEvent event) {
        
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherAvisModifFXML.fxml"));
            Parent root =loader.load();
            AfficherAvisModifFXMLController tr = loader.getController();
            btnaffa.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @FXML
    private void supprimerAvis(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SupprimerAvisFXML.fxml"));
            Parent root =loader.load();
            SupprimerAvisFXMLController tr = loader.getController();
            btnsuppa.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    
}
