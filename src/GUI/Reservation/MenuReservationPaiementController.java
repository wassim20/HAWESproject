/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reservation;

import Entités.Paiement;
import java.io.IOException;
import Entités.Reservation;
import Entités.utilisateurs;
import Service.utilisateurService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class MenuReservationPaiementController implements Initializable {

    @FXML
    private Button btnaffr;
    @FXML
    private Button btntrtr;
    @FXML
    private Button btnaffp;
    @FXML
    private Button btnrect;
    @FXML
    private Button btnajr;
    @FXML
    private Button btnstat;

    Reservation r = new Reservation();
    Paiement p = new Paiement();
    utilisateurs u = new utilisateurs();
    utilisateurService us;
    @FXML
    private AnchorPane sideBarPane;
    @FXML
    private Button gestionUserButton;
    @FXML
    private Button gestionHebergementButton;
    @FXML
    private Button btngetback;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        us = new utilisateurService();
        System.out.println(us.currentUser.getRole());
        u=us.currentUser;
        if(u.getRole().equals("Client")){
            btnstat.setVisible(false);
            btnaffp.setVisible(false);
            btntrtr.setVisible(false);
        }else if(u.getRole().equals("Chef d'agence")){
            btnrect.setVisible(false);
        }
    }
    
    @FXML
    private void afficherReservations(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReservationsFXML.fxml"));
            Parent root =loader.load();
            AfficherReservationsFXMLController arc = loader.getController();
            //ap.setReservation(r);
            btnaffr.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }   
    }


    @FXML
    private void traiterReservation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TraiterReservationFXML.fxml"));
            Parent root =loader.load();
            TraiterReservationFXMLController tr = loader.getController();
            btntrtr.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    @FXML
    private void afficherPaiements(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherPaiementsFXML.fxml"));
            Parent root =loader.load();
            AfficherPaiementsFXMLController afp = loader.getController();
            //ap.setReservation(r);
            btnaffp.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }   
    }


    @FXML
    private void recupererTicket(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RecupererTicketFXML.fxml"));
            Parent root =loader.load();
            RecupererTicketFXMLController pt = loader.getController();
            //ap.setReservation(r);
            btnrect.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouterReservation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutReservationFXML.fxml"));
            Parent root =loader.load();
            AjoutReservationFXMLController arc = loader.getController();
            //ap.setReservation(r);
            btnajr.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }          
    }

    @FXML
    private void displayStatistics(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StatisticsFXML.fxml"));
            Parent root =loader.load();
            StatisticsFXMLController st = loader.getController();
            btnstat.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    
}
/// NOTIFICATIONS RESERVATIONS NON TRAITEES