/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Avis;
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
import services.AvisService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherAvisModifFXMLController implements Initializable {

    @FXML
    private ListView txtlistavis;
    @FXML
    private Button btngetback;
    @FXML
    private TextField txtidavis;
    @FXML
    private Button btncon;
    AvisService as = new AvisService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        ObservableList<String> items =FXCollections.observableArrayList();
        List<Avis> listAvis = as.afficherAvis();
        for(Avis a : listAvis) {
            String ch = a.toString();
            items.add(ch);
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
    private void modifierAvis(ActionEvent event) {
        String sidavis = txtidavis.getText();
        int idAvis = parseInt(sidavis);
        Avis a = new Avis();
        a = as.getAvisById(idAvis);
        try {
            //System.out.println(a);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierAvisFXML.fxml"));
            Parent root =loader.load();
            
            ModifierAvisFXMLController mda = loader.getController();
            mda.prepare(a);
            btncon.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
        
    }
    
}
