/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Paiement;
import java.io.IOException;
import services.PaiementService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class AfficherPaiementsFXMLController implements Initializable {

    @FXML
    private ListView txtlistPaiement;
    private Paiement p = new Paiement();
    private PaiementService ps = new PaiementService();
    @FXML
    private RadioButton rdafft;
    @FXML
    private RadioButton rdaffa;
    @FXML
    private TextField txtsearch;
    @FXML
    private Button btnsearch;
    @FXML
    private Button btngetback;
    ToggleGroup rdaff = new ToggleGroup();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rdafft.setToggleGroup(rdaff);
        rdafft.setSelected(true);
        rdaffa.setToggleGroup(rdaff);
        
        //PDFgenerator.
        ObservableList<String> items =FXCollections.observableArrayList();
        List<Paiement> listPaiement = ps.afficherPaiement();
        for(Paiement pmt : listPaiement) {
            String ch = pmt.toString();
            items.add(ch);
        }
    txtlistPaiement.setItems(items);
    }    

    public void setTxtlistPaiement(ListView<?> txtlistPaiement) {
        this.txtlistPaiement = txtlistPaiement;
    }

    @FXML
    private void afficherPaiements(ActionEvent event) {
        ObservableList<String> items =FXCollections.observableArrayList();
        List<Paiement> listPaiement = ps.afficherPaiement();
        for(Paiement p : listPaiement) {
            String ch = p.toString();
            items.add(ch);
        }
    txtlistPaiement.setItems(items);
    }

    @FXML
    private void afficherPaiementAnnulees(ActionEvent event) {
        ObservableList<String> items =FXCollections.observableArrayList();
        List<Paiement> listPaiement = ps.afficherPaiementA();
        for(Paiement p : listPaiement) {
            String ch = p.toString();
            items.add(ch);
        }
    txtlistPaiement.setItems(items);
    }

    @FXML
    private void chercherPaiement(KeyEvent event) {
        ObservableList<String> items =FXCollections.observableArrayList();
        List<Paiement> listPaiement;
        String tchoix=txtsearch.getText();
        try{
            int nchoix = Integer.parseInt(tchoix);
            listPaiement = ps.cherchePaiement(nchoix);
        } catch (NumberFormatException e) {
            listPaiement = ps.cherchePaiement(tchoix);
        }
        for(Paiement p : listPaiement) {
            String ch = p.toString();
            items.add(ch);
        }
        txtlistPaiement.setItems(items);
    }

    @FXML
    private void loadMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuReservationPaiement.fxml"));
            Parent root =loader.load();
            MenuReservationPaiementController mr = loader.getController();
            btngetback.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherReservationsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
}
 ///    EDIT PAIEMENT           - DONE
 ///    CANCEL PAIEMENT         - DONE
 ///    RECHERCHE AVANCEE       - DONE