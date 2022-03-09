/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Avis;

import Entités.Avis;
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
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;
import Service.AvisService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherAvisFXMLController implements Initializable {

    @FXML
    private ListView<Avis> txtlistavis;
    @FXML
    private Button btngetback;
    AvisService as = new AvisService();
    Avis a = new Avis();
    @FXML
    private TextField txtsearch;
    @FXML
    private Button btnsuppa;
    @FXML
    private Button btnmda;
    @FXML
    private Button btnajouta;
    @FXML
    private AnchorPane mainPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Avis> items = FXCollections.observableArrayList();
        List<Avis> listAvis = as.afficherAvis();
        //int etoile=RaitingId.getRating();         
        for (Avis a : listAvis) {
            String ch = a.toString();
            items.add(a);
        }
        txtlistavis.setItems(items);
    }

    @FXML
    private void loadMenu(ActionEvent event) {
        displayMenu();
    }

    private void displayMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuReclamationAvis.fxml"));
            Parent root = loader.load();
            MenuReclamationAvisController mr = loader.getController();
            btngetback.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouterAvis(ActionEvent event) {

        try {
            AnchorPane pane;
            pane = FXMLLoader.load(getClass().getResource("../Avis/AjouterAvisFXML.fxml"));

            mainPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.out.println("error in redirect");
        }
    }

    @FXML
    private void supprimerAvis(ActionEvent event) {
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer ?", "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {

            a = txtlistavis.getSelectionModel().getSelectedItem();
            as.supprimerAvis(a.getId_avis());
            try {
                AnchorPane pane;
                pane = FXMLLoader.load(getClass().getResource("../Avis/AfficherAvisFXML.fxml"));

                mainPane.getChildren().setAll(pane);
            } catch (IOException ex) {
                System.out.println("error in redirect");
            }
        } else {
            try {
                AnchorPane pane;
                pane = FXMLLoader.load(getClass().getResource("../Avis/AfficherAvisFXML.fxml"));

                mainPane.getChildren().setAll(pane);
            } catch (IOException ex) {
                System.out.println("error in redirect");
            }

        }

    }

    @FXML
    private void modifierAvis(ActionEvent event) {

        int input = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment modifier ?", "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {

            Avis a = new Avis();
            a = txtlistavis.getSelectionModel().getSelectedItem();
            try {
                AnchorPane pane;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Avis/ModifierAvisFXML.fxml"));
                pane = loader.load();
                ModifierAvisFXMLController mda = loader.getController();
                mda.prepare(a);
                
                mainPane.getChildren().setAll(pane);
            } catch (IOException ex) {
                System.out.println("error in redirect");
            }
        } else {

            try {
                AnchorPane pane;
                pane = FXMLLoader.load(getClass().getResource("../Avis/AfficherAvisFXML.fxml"));

                mainPane.getChildren().setAll(pane);
            } catch (IOException ex) {
                System.out.println("error in redirect");
            }

        }

    }

    @FXML
    private void chercherAvis(KeyEvent event) {
        ObservableList<Avis> items = FXCollections.observableArrayList();
        List<Avis> listAvis;
        String tchoix = txtsearch.getText();
        try {
            int nchoix = Integer.parseInt(tchoix);
            listAvis = as.chercherAvis(nchoix);
        } catch (NumberFormatException e) {
            listAvis = as.chercherAvis(tchoix);
        }
        for (Avis a : listAvis) {
            String ch = a.toString();
            items.add(a);
        }
        txtlistavis.setItems(items);
    }

}
