/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterReclamationFXMLController implements Initializable {

    @FXML
    private TextField txtdescrec;
    @FXML
    private Button btnajoutrec;
    @FXML
    private Button btngetback;
     Reclamation r = new Reclamation();
    ReclamationService rs = new ReclamationService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void ajoutrec(ActionEvent event) {
          
        String desc_rec = txtdescrec.getText();
        r.setDesc_rec(desc_rec);
        r.setTraite(0);
        r.setId_hbg(1);
        r.setId_user(21);
        rs.ajouterReclamation(r);
        displayMenu();
        
        
    }
    
    
}
