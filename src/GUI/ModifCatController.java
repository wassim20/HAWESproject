/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.categorieeve;
import entities.evenement;
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
import Services.CategorieeveService;

/**
 * FXML Controller class
 *
 * @author achre
 */
public class ModifCatController implements Initializable {

    @FXML
    private TextField nomcat;
    @FXML
    private Button btngetback;
    @FXML
    private Button btnmodifc;
    
    CategorieeveService cs = new CategorieeveService();
    categorieeve c = new categorieeve();
    categorieeve categorie = new categorieeve();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadMenu(ActionEvent event) {
                                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuEvenement.fxml"));
            Parent root =loader.load();
            MenuEvenementController tr = loader.getController();
            btngetback.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    int idCat;
        public void prepare(categorieeve c){
        nomcat.setText(c.getNomCat()); 
        idCat = c.getIdCat();
        categorie = c;
    }

    @FXML
    private void modifierCat(ActionEvent event) {
            String nom = nomcat.getText();
            c.setNomCat(nom);
            cs.modifierCategorie(c, idCat);
    }
    
}
