/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Paiement;
import entities.Reservation;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.PaiementService;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class RecupererTicketFXMLController implements Initializable {

    @FXML
    private Button btnrecuperer;
    @FXML
    private Button btngetback;
    @FXML
    private TextField txtidRes;
    @FXML
    private TextField txtidPmt;
    @FXML
    private Label reservation;
    @FXML
    private Label paiement;
    
    int idRes = 0;
    int idPmt = 0;
    Paiement p = new Paiement();
    Reservation r = new Reservation();
    ReservationService rs = new ReservationService();
    PaiementService ps = new PaiementService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void recupererTicket(ActionEvent event) {
        String sidRes = txtidRes.getText();
        String sidPmt = txtidPmt.getText();
        if( (sidRes.length() != 0) && (sidPmt.length() != 0)){
            idRes = parseInt(sidRes);
            idPmt = parseInt(sidPmt);
            r = rs.getReservationById(idRes);
            p = ps.getPaiementById(idPmt);
            reservation.setText(r.toString());
            paiement.setText(p.toString());
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
             System.out.println(ex.getMessage());
        }
    }
    
}
