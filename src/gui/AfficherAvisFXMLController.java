/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Avis;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;
import services.AvisService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherAvisFXMLController implements Initializable {

    @FXML
    private ListView<Avis> txtlistavis;
    @FXML
    private Button btngetback;
    AvisService as = new AvisService();
    Avis a = new Avis();
    @FXML
    private Button btnsearch;
    @FXML
    private TextField txtsearch;
    @FXML
    private Button btnsuppa;
    @FXML
    private Button btnmda;
    @FXML
    private Button btnajouta;
    @FXML
    private AnchorPane anchor2;
    @FXML
    private Rating rating;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        ObservableList<Avis> items =FXCollections.observableArrayList();
        List<Avis> listAvis = as.afficherAvis();
        //int etoile=RaitingId.getRating();         
        for(Avis a : listAvis) {
            String ch = a.toString();
            items.add(a);
        }
    txtlistavis.setItems(items);
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
    private void chercherAvis(ActionEvent event) {
        ObservableList<Avis> items =FXCollections.observableArrayList();
        List<Avis> listAvis;
        String tchoix=txtsearch.getText();
        try{
            int nchoix = Integer.parseInt(tchoix);
            listAvis = as.chercherAvis(nchoix);
        } catch (NumberFormatException e) {
            listAvis = as.chercherAvis(tchoix);
        }
        for(Avis a : listAvis) {
            String ch = a.toString();
            items.add(a);
        }
        txtlistavis.setItems(items);
    }
       @FXML
    private void ajouterAvis(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterAvisFXML.fxml"));
            Parent root =loader.load();
            AjouterAvisFXMLController tr = loader.getController();
            btnajouta.getScene().setRoot(root);
//            System.out.println("rating given by user:" + rating.getRating());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    

    @FXML
    private void supprimerAvis(ActionEvent event) {
         a = txtlistavis.getSelectionModel().getSelectedItem();
        as.supprimerAvis(a.getId_avis());
        displayMenu();
    }

    @FXML
    private void modifierAvis(ActionEvent event) {
        
     
        Avis a = new Avis();
        a = txtlistavis.getSelectionModel().getSelectedItem();
        try {
            System.out.println(a);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierAvisFXML.fxml"));
            Parent root =loader.load();
            
            ModifierAvisFXMLController mda = loader.getController();
            mda.prepare(a);
            btnmda.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            
        }
      }
      
       
    }
    


   

