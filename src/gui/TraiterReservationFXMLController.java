/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Paiement;
import entities.Reservation;
import entities.Ticket;
import entities.utilisateurs;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.PaiementService;
import services.ReservationService;
import services.TicketService;
import services.utilisateurService;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class TraiterReservationFXMLController implements Initializable {

    @FXML
    private ListView<Reservation> txtlistReservation;
    private Button btnconfirmer;
    @FXML
    private RadioButton rdvalider;
    @FXML
    private RadioButton rdannuler;
    @FXML
    private AnchorPane anchor2;
    ToggleGroup rdchoix = new ToggleGroup();
    private String role;
    public int etat = 1;

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    Reservation r = new Reservation();
    Paiement p = new Paiement();
    Ticket t = new Ticket();
    utilisateurs u = new utilisateurs();
    ReservationService rs = new ReservationService();
    private utilisateurService us = new utilisateurService();
    PaiementService ps = new PaiementService();
    TicketService ts = new TicketService(); 
    ToggleGroup rdchoice = new ToggleGroup();
    @FXML
    private Button btngetback;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            rdvalider.setToggleGroup(rdchoix);
            rdvalider.setSelected(true);
            rdannuler.setToggleGroup(rdchoix);
            u.setCinUser("07239033");
            u.setIdUser(1);
            role = us.getRoleByCin(u.getCinUser());
            u.setRole(role);
            ObservableList<Reservation> items =FXCollections.observableArrayList();
            List<Reservation> listReservation = rs.afficherReservationNT(u);
            for(Reservation r : listReservation) {
                String ch = r.toString();
                items.add(r);
            }
            txtlistReservation.setItems(items);
            rdvalider.setToggleGroup(rdchoice);
            rdvalider.setSelected(true);
            rdannuler.setToggleGroup(rdchoice);
        } catch (SQLException ex) {
            FXMain.showAlertWithHeaderText(u, "User Not Found", "-");
        }
    }
    
    @FXML
    private void prepareTR(MouseEvent click) throws Exception {
        //  System.out.println("Toggled: " + Integer.parseInt(rdchoice.getSelectedToggle().toString()));
        //  rdchoice.toString();
        if (click.getClickCount() == 2) {
            r=txtlistReservation.getSelectionModel().getSelectedItem();
            System.out.println(r);
            System.out.println(etat);
            if (etat == 0){
                FXMain.showErrorAlert();        //// ERREUR RADIO NOT CHECKED
            }else if(etat == 1){
                FXMain.showAlertWithHeaderText(r, "Merci d'attendre l'apparition du confirmation", "");
                    p = ps.getPaiementByReservation(r);
                    t = ts.GetTicketByResAndPai(p,r);
                    //System.out.println(r);
                    String filename = "R"+t.getIdTicket()+"VCHR";
                    PDFgenerator.setR(r);
                    PDFgenerator.setP(p);
                    PDFgenerator.setU(u);
                    PDFgenerator.setFilename(filename);
                    PDFgenerator.main();
                    //method retourne l'email de l'utilisateur
                    boolean sent = Mailing.sendMail("houssem.hosni@esprit.tn",t);
                    //mylert.hide();
                    if(sent==true){
                        rs.traiterReservation(r, 1);
                        FXMain.showAlertWithHeaderText(r, "Réservation Accepté", "");
                    }else{
                        FXMain.showAlertWithHeaderText(r, "Réservation non validée", "-");
                    }
            }else if(etat == -1){
                rs.traiterReservation(r, -1);
                Paiement p = ps.getPaiementByReservation(r);
                ps.annulerPaiement(p);
                t = ts.GetTicketByResAndPai(p,r);
                ts.supprimerTicket(t.getIdTicket());
                FXMain.showAlertWithHeaderText(r, "", "-");
            }
            displayMenu();
        }
    }    
    
    private void displayMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuReservationPaiement.fxml"));
            Parent root =loader.load();
            MenuReservationPaiementController mr = loader.getController();
            btngetback.getScene().setRoot(root);
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void setEtatValid(MouseEvent event) {
         etat = 1;
    }

    @FXML
    private void CancelReservation(MouseEvent event) {
        etat = -1;
    }

    @FXML
    private void loadMenu(ActionEvent event) {
        displayMenu();
    }
}