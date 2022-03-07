/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.sun.org.apache.bcel.internal.Repository.instanceOf;
import entities.Paiement;
import entities.Reservation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.PaiementService;
import services.ReservationService;
import services.TicketService;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class BoiteConfirmationFXMLController implements Initializable {

    @FXML
    private Label hello;
    @FXML
    private Label entity;
    
    private Object object;
    @FXML
    private Button btncancel;
    @FXML
    private Button btndelete;

    public void setObject(Object object) {
        this.object = object;
    }
    Reservation r = new Reservation();
    ReservationService rs = new ReservationService();
    PaiementService ps = new PaiementService();
    TicketService ts = new TicketService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    void prepare(Object object) {
        if(object.getClass() == Reservation.class){
            hello.setText("Veuillez Confirmer la suppression du Réservation suivante \t");
            entity.setText(object.toString());
            
        }
        if(object.getClass() == Paiement.class){
            hello.setText("Fuck World!");
        }
    }

    @FXML
    private void loadMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuReservationPaiement.fxml"));
            Parent root =loader.load();
            MenuReservationPaiementController mr = loader.getController();
            btncancel.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherReservationsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteObject(ActionEvent event) {
        if(object.getClass() == Reservation.class){
            r = (Reservation) object;
        rs.traiterReservation(r, -1);
        Paiement p = ps.getPaiementByReservation(r);
        ps.annulerPaiement(p);
        int idTicket = ts.GetTicketByResAndPai(p,r);
        ts.supprimerTicket(idTicket);
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuReservationPaiement.fxml"));
            Parent root =loader.load();
            MenuReservationPaiementController mrp = loader.getController();
            btndelete.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
