/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Avis;
import entities.Reclamation;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherReclamationFXMLController implements Initializable {

    @FXML
    private ListView txtlistreclamation;
    
    ReclamationService rs = new ReclamationService();
    Reclamation r =new Reclamation();
    @FXML
    private Button btngetback;
    @FXML
    private Button btnsearch;
    @FXML
    private TextField txtsearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
          
        ObservableList<String> items =FXCollections.observableArrayList();
        List<Reclamation> listReclamation = rs.afficherReclamation();
        for(Reclamation r : listReclamation) {
            String ch = r.toString();
            items.add(ch);
        }
    txtlistreclamation.setItems(items);
        
    }    
        
        
        // TODO

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
    private void chercherReclamation(ActionEvent event) {
                ObservableList<String> items =FXCollections.observableArrayList();
        List<Reclamation> listReclamation;
        String tchoix=txtsearch.getText();
        try{
            int nchoix = Integer.parseInt(tchoix);
            listReclamation = rs.chercherReclamation(nchoix);
        } catch (NumberFormatException e) {
            listReclamation = rs.chercherReclamation(tchoix);
        }
        for(Reclamation a : listReclamation) {
            String ch = a.toString();
            items.add(ch);
        }
        txtlistreclamation.setItems(items);
    }
    }
      
    
