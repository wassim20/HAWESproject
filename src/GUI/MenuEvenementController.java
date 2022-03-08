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
import services.EvenementService;
/**
 * FXML Controller class
 *
 * @author achre
 */
public class MenuEvenementController implements Initializable {

    @FXML
    private Button btnaffe;
    
    EvenementService es = new EvenementService();

    @FXML
    private Button clt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void SessionAdmin(ActionEvent event) {
        try {
            System.out.println("test");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceAdmin.fxml"));
            Parent root =loader.load();
            InterfaceAdminController tr = loader.getController();
            btnaffe.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    
    
        @FXML
    private void SessionClient(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceClient.fxml"));
            Parent root =loader.load();
            InterfaceClientController tr = loader.getController();
            clt.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }


    


    
    

   
    
}
