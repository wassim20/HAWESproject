/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Paiement;
import entities.Reservation;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import services.PaiementService;
import services.TicketService;
import entities.Ticket;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class AjoutPaiementFXMLController implements Initializable {

    @FXML
    private ChoiceBox txtmethode = new ChoiceBox(FXCollections.observableArrayList(
    "Especes", "Cheque", "Virement"));
    @FXML
    private TextField txtmontant;
    @FXML
    private Button btngetback;

    public Reservation reservation;
    

    
    Ticket t = new Ticket();
    Paiement p = new Paiement();
    Reservation r1 = new Reservation();
    ReservationService rs = new ReservationService();
    PaiementService ps = new PaiementService();
    TicketService ts = new TicketService();
    @FXML
    private AnchorPane anchor2;
    @FXML
    private Button btnajoutP;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> langs = FXCollections.observableArrayList("Especes", "Cheque", "Virement");
        txtmethode.setItems(langs);
        // Vérification date Arrivé
    }    
    @FXML
    private void addPaiement(ActionEvent event) {
            try {
                String montanttxt = txtmontant.getText();
                Double montant = parseDouble(montanttxt);
                try {
                    p.setMethode(txtmethode.getValue().toString());
                        p.setCanceled(0);
                        p.setMontant(montant);
                        rs.ajouterReservation(reservation);
                        r1 = rs.getIdByReservation(reservation);
                        p.setIdRes(r1.getIdRes());
                        ps.ajouterPaiement(p);
                        p = ps.getPaiementByReservation(r1);
                        t.setIdRes(r1.getIdRes());
                        t.setIdPmt(p.getIdPmt());
                        t.setDeleted(0);
                        ts.ajouterTicket(t);
                        t = ts.GetTicketByResAndPai(p, r1);
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherPaiementsFXML.fxml"));
                        Parent root =loader.load();
                        AfficherPaiementsFXMLController afp = loader.getController();
                        txtmontant.getScene().setRoot(root);
                        FXMain.showAlertWithHeaderText(r1,"","+");
                    } catch (IOException ex) {
                        System.out.println("fxmlload");
                        FXMain.showAlertWithHeaderText(r1,ex.getMessage(),"."); /// ERREUR FXML.LOADER 
                    }
                } catch (RuntimeException e){
                    System.out.println("choicebox");
                   // if(t==null){
                        FXMain.showErrorAlert(); ///    ERREUR CHOICEBOX EMPTY 
                    //}else{
                        //FXMain.showAlertWithHeaderText(r1,e.getMessage(),".");
                    //}
                }
            }catch (NumberFormatException a){
                System.out.println("champsnm");
                FXMain.showErrorAlert(); ///    ERREUR CHAMPS NUMERIQUE 
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

    public void prepare(Reservation r) {
        reservation = r;
    }
   
}