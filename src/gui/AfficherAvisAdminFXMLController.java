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
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import services.AvisService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherAvisAdminFXMLController implements Initializable {
      AvisService as = new AvisService();
    Avis a = new Avis();

    @FXML
    private ListView<Avis> txtlistavis;
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
         ObservableList<Avis> items =FXCollections.observableArrayList();
        List<Avis> listAvis = as.afficherAvis();
        //int etoile=RaitingId.getRating();         
        for(Avis a : listAvis) {
            String ch = a.toString();
            items.add(a);
        }
    txtlistavis.setItems(items);
        // TODO
    }    

    @FXML
    private void chercherAvis(KeyEvent event) {
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
    private void supprimerAvis(ActionEvent event) {
        
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer ?", "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
      if (input==0) {
       
         a = txtlistavis.getSelectionModel().getSelectedItem();
        as.supprimerAvis(a.getId_avis());
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherAvisAdminFXML.fxml"));
            Parent root =loader.load();
            AfficherAvisAdminFXMLController mr = loader.getController();
            btnsuppa.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      }
      else
      {
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherAvisAdminFXML.fxml"));
            Parent root =loader.load();
            AfficherAvisAdminFXMLController mr = loader.getController();
            btnsuppa.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
          
      }
      
        
    
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

    
}
