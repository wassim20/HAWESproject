/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reservation;

import Entités.Paiement;
import Entités.Reservation;
import java.io.IOException;
import Service.PaiementService;
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
import javafx.scene.input.MouseEvent;
import Service.ReservationService;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class AfficherPaiementsFXMLController implements Initializable {

    @FXML
    private ListView<Paiement> txtlistPaiement;
    private Paiement p = new Paiement();
    private PaiementService ps = new PaiementService();
    @FXML
    private RadioButton rdafft;
    @FXML
    private RadioButton rdaffa;
    @FXML
    private TextField txtsearch;
    @FXML
    private Button btngetback;
    ToggleGroup rdaff = new ToggleGroup();
    Reservation r = new Reservation();
    ReservationService rs = new ReservationService();
    @FXML
    private AnchorPane sideBarPane;
    @FXML
    private Button gestionUserButton;
    @FXML
    private Button gestionHebergementButton;
    @FXML
    private Button gestionPickupButton;
    @FXML
    private Button gestionAvisButton;
    @FXML
    private Button gestionEvenementButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button gestionPaiementButton;
    @FXML
    private Button gestionRelamationButton;
    @FXML
    private Button btngetback1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rdafft.setToggleGroup(rdaff);
        rdafft.setSelected(true);
        rdaffa.setToggleGroup(rdaff);
        
        //PDFgenerator.
        ObservableList<Paiement> items =FXCollections.observableArrayList();
        List<Paiement> listPaiement = ps.afficherPaiement();
        for(Paiement pmt : listPaiement) {
            String ch = pmt.toString();
            items.add(pmt);
        }
    txtlistPaiement.setItems(items);
    }    

    public void setTxtlistPaiement(ListView<Paiement> txtlistPaiement) {
        this.txtlistPaiement = txtlistPaiement;
    }

    @FXML
    private void afficherPaiements(ActionEvent event) {
        ObservableList<Paiement> items =FXCollections.observableArrayList();
        List<Paiement> listPaiement = ps.afficherPaiement();
        for(Paiement p : listPaiement) {
            String ch = p.toString();
            items.add(p);
        }
    txtlistPaiement.setItems(items);
    }

    @FXML
    private void afficherPaiementAnnulees(ActionEvent event) {
        ObservableList<Paiement> items =FXCollections.observableArrayList();
        List<Paiement> listPaiement = ps.afficherPaiementA();
        for(Paiement p : listPaiement) {
            String ch = p.toString();
            items.add(p);
        }
    txtlistPaiement.setItems(items);
    }

    @FXML
    private void chercherPaiement(KeyEvent event) {
        ObservableList<Paiement> items =FXCollections.observableArrayList();
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
            items.add(p);
        }
        txtlistPaiement.setItems(items);
    }
    @FXML
    public void prepareUD(MouseEvent click) throws Exception {
        if (click.getClickCount() == 2) {
            Paiement p = txtlistPaiement.getSelectionModel().getSelectedItem();
            r=rs.getReservationById(p.getIdRes());
            p= ps.getPaiementByReservation(r);
            System.out.println(p);
            try {
                System.out.println(p);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierPaiementFXML.fxml"));
                Parent root =loader.load();

                ModifierPaiementFXMLController mdr = loader.getController();
                mdr.prepare(p);
                txtlistPaiement.getScene().setRoot(root);
            } catch (IOException ex) {
                    FXMain.showAlertWithHeaderText(r,ex.getMessage(),".");
            }   
        }
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

    @FXML
    private void gestionReserations(ActionEvent event) {
    }
}
 ///    EDIT PAIEMENT           - DONE
 ///    CANCEL PAIEMENT         - DONE
 ///    RECHERCHE AVANCEE       - DONE