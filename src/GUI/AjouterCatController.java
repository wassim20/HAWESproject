/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.categorieeve;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author achre
 */
public class AjouterCatController implements Initializable {

    @FXML
    private TextField nomcat;
    @FXML
    private Button btngetback;
    @FXML
    private Button btnajoutc;
    
    CategorieeveService cs = new CategorieeveService();
    categorieeve c = new categorieeve ();
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadMenu(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GererCat.fxml"));
            Parent root =loader.load();
            GererCatController tr = loader.getController();
            btngetback.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @FXML
    private void ajouterCat(ActionEvent event) {
        
        try {
             if (nomcat.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Le Nom du categorie est obligatoire");
                        } else {
                JOptionPane.showMessageDialog(null, "Categorie ajoutée");
            }
            
            String nom = nomcat.getText();



            c.setNomCat(nom);


            cs.ajouterCategorie(c);
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GererCat.fxml"));        
            Parent root = loader.load();
            GererCatController ac = loader.getController();
            nomcat.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
