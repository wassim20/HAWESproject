/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entités.Reclamation;
import java.io.IOException;
import static java.lang.Integer.parseInt;
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
import Service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class SupprimerReclamationFXMLController implements Initializable {

    @FXML
    private ListView listsupprec;
    @FXML
    private Button btngetback;
    @FXML
    private TextField txtidrec;
    @FXML
    private Button btnsupprec;
    ReclamationService rs = new ReclamationService();

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
    listsupprec.setItems(items);
        // TODO
    }    

   
 @FXML
    private void loadMenu(ActionEvent event) {
        displayMenu();
    }
    
    private void displayMenu(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamationFXML.fxml"));
            Parent root =loader.load();
            AfficherReclamationFXMLController mr = loader.getController();
            btngetback.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimerrec(ActionEvent event) {
        
        String sidrec = txtidrec.getText();
        int idRec = parseInt(sidrec);
        rs.supprimerReclamation(idRec);
        displayMenu();
    }
    
    
}
