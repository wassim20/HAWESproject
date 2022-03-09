/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Avis;

import Entités.Avis;
import Entités.Reclamation;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import Service.ReclamationService;
import Service.utilisateurService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherReclamationFXMLController implements Initializable {

    private ListView<Reclamation> txtlistreclamation;

    ReclamationService rs = new ReclamationService();
    Reclamation r = new Reclamation();
    @FXML
    private Button btngetback;
    @FXML
    private TextField txtsearch;
    @FXML
    private Button btnsupprec;
    @FXML
    private Button btnajoutr;
    @FXML
    private ListView<Reclamation> txtlistrec;
    @FXML
    private Button btncon;

    utilisateurService US;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        US = new utilisateurService();
        ObservableList<Reclamation> items = FXCollections.observableArrayList();
        List<Reclamation> listReclamation = rs.afficherReclamation1(US.currentUser.getIdUser());//session user
        
        System.out.println(listReclamation);
        for (Reclamation r : listReclamation) {
            String ch = r.toString();
            items.add(r);
        }
        //txtlistrec.getItems().add(r);
        txtlistrec.setItems(items);

    }

    // TODO
    @FXML
    private void loadMenu(ActionEvent event) {
        displayMenu1();
    }

    private void displayMenu1() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuReclamationAvis.fxml"));
            Parent root = loader.load();
            MenuReclamationAvisController mr = loader.getController();
            btngetback.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void displayMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamationFXML.fxml"));
            Parent root = loader.load();
            AfficherReclamationFXMLController mr = loader.getController();
            btngetback.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouterReclamation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReclamationFXML.fxml"));
            Parent root = loader.load();
            AjouterReclamationFXMLController tr = loader.getController();
            btnajoutr.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimerrec(ActionEvent event) {

        r = txtlistrec.getSelectionModel().getSelectedItem();
        if (r.getTraite() == 0) {

            int input = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer ?", "Choisir une option...",
                    JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (input == 0) {
                rs.supprimerReclamation(r.getId_rec());
                displayMenu();
            } else {
                displayMenu();
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Alert");
            alert.setContentText("Réclamation deja traitée, impossible de supprimer");
            alert.showAndWait();
        }
        //displayMenu();

    }

    @FXML
    private void modifierReclamation(ActionEvent event) {
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment modifier ?", "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {

            Reclamation r = new Reclamation();
            r = txtlistrec.getSelectionModel().getSelectedItem();
            if (r.getTraite() == 0) {

                try {
                    System.out.println(r);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReclamtionFXML.fxml"));
                    Parent root = loader.load();

                    ModifierReclamtionFXMLController mda = loader.getController();
                    mda.prepare(r);
                    btncon.getScene().setRoot(root);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("Alert");
                alert.setContentText("reclamation deja traiteé , impossible de modifier");
                alert.showAndWait();
            }
        } else {
            displayMenu();
        }

    }

    @FXML
    private void chercherReclamation(KeyEvent event) {
        ObservableList<Reclamation> items = FXCollections.observableArrayList();
        List<Reclamation> listReclamation;
        String tchoix = txtsearch.getText();
        try {
            int nchoix = Integer.parseInt(tchoix);
            listReclamation = rs.chercherReclamation(nchoix);
        } catch (NumberFormatException e) {
            listReclamation = rs.chercherReclamation(tchoix);
        }
        for (Reclamation a : listReclamation) {
            String ch = a.toString();
            items.add(a);
        }
        txtlistrec.setItems(items);
    }

}
