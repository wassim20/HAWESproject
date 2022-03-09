/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entités.Reclamation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.Graphics;
//import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import Service.ReclamationService;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterReclamationFXMLController implements Initializable {
//String[] badword=new String[5];
String[] badword = {"russia", "ukraine", "nato","lolo","holo"};
//badword = ;
 
 






            @FXML
    private TextField txtdescrec;
    @FXML
    private Button btngetback;
     Reclamation r = new Reclamation();
    ReclamationService rs = new ReclamationService();
    @FXML
    private Button btnajoutrec1;

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamationFXML.fxml"));
            Parent root =loader.load();
            AfficherReclamationFXMLController mr = loader.getController();
            btngetback.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajoutrec(ActionEvent event) {
        String des=txtdescrec.getText();
     
if(txtdescrec.getText().trim().isEmpty()){
        Alert fail= new Alert(AlertType.INFORMATION);
        fail.setHeaderText("Echec");
        fail.setContentText("Vous n'avez rien saisi ");
        fail.showAndWait();
}
        
           
        else if(des.contains("russia")||des.contains("ukraine"))
        {
            System.out.println("wselet");
           
            
                  Alert fail= new Alert(AlertType.INFORMATION);
        fail.setHeaderText("failure");
        fail.setContentText("you have typed bad words");
        fail.showAndWait();
}   
     else {
    
       /* Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Succes");
        alert.setContentText("Account succesfully created!");
        alert.showAndWait();
   */
       Image image= new Image("assets/image.png" );
       Notifications notifications =Notifications.create();
       notifications.graphic(new ImageView(image));
       
       notifications.text("Votre Opération a été faite avec succès");
       notifications.title("success Message");
       notifications.hideAfter(Duration.seconds(4));
       notifications.show();
          
        String desc_rec = txtdescrec.getText();
        r.setDesc_rec(desc_rec);
        r.setTraite(0);
        r.setId_hbg(1);
        r.setId_user(21);
        rs.ajouterReclamation(r);
        displayMenu();
         }
        
    }
    
    
}
