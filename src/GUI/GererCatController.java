/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.categorieeve;
import entities.evenement;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import services.CategorieeveService;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author achre
 */
public class GererCatController implements Initializable {

    @FXML
    private VBox listevent;
    @FXML
    private Label nomcat;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;
    @FXML
    private Button ajoute;
    @FXML
    private Button retour;
    
        CategorieeveService es = new CategorieeveService();
    List<categorieeve> evenements =new ArrayList();
    evenement et = new evenement();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       evenements= es.afficherCategorie();
        displayEvents();
    }    

    
    
            private void displayEvents() {
            
             


            
            
            
            
            
        listevent.getChildren().clear();
        for (int i = 0; i < evenements.size(); i++) {
            categorieeve e = evenements.get(i);
            Pane userPane = new Pane();
            userPane.setBackground(Background.EMPTY);
            userPane.setPrefHeight(150.0);
            userPane.setPrefWidth(788.0);
            userPane.setLayoutX(0);
            userPane.setLayoutY(0);
            
           Label ide = new Label();
            ide.setLayoutY(20);
            ide.setTextFill(Color.BLACK);
            String id =Integer.toString(e.getIdCat()) ;
            ide.setText(id);


            Label Nom = new Label();
            Nom.setLayoutX(100);
            Nom.setLayoutY(20);
            Nom.setTextFill(Color.BLACK);
            Nom.setText(e.getNomCat());
            
            
            Button deleteBtn = new Button();
            deleteBtn.setPrefHeight(25);
            deleteBtn.setPrefWidth(74);            
            deleteBtn.setLayoutX(700);
            deleteBtn.setLayoutY(16);
            deleteBtn.setText("Supprimer");
    
            
            userPane.getChildren().add(ide);
            userPane.getChildren().add(Nom);
            userPane.getChildren().add(deleteBtn);
            
            deleteBtn.setOnMouseClicked((MouseEvent event) -> {
                int input = JOptionPane.showConfirmDialog(null, "Etes-vous sure de supprimer cette categorie ?",
                        "Confirmer la supression",
                        JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

                if (input == 0) {
            String sidevent = ide.getText();
            int idevent = parseInt(sidevent);
            es.supprimerCategorie(idevent);
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GererCat.fxml"));
            Parent root =loader.load();
            GererCatController tr = loader.getController();
            deleteBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
            
                }
            });
            
            


            listevent.getChildren().add(userPane);
        }

    }
    
    
    @FXML
    private void ajouterCat(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCat.fxml"));
            Parent root =loader.load();
            AjouterCatController tr = loader.getController();
            ajoute.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @FXML
    private void loadMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceAdmin.fxml"));
            Parent root =loader.load();
            InterfaceAdminController tr = loader.getController();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    
}
