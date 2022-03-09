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
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TextField;
import services.PaiementService;
import services.ReservationService;
import services.TicketService;
import services.utilisateurService;

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
    private TextField txtCin;
    @FXML
    private Label reservation;
    @FXML
    private Label paiement;
    
    int idUser = 0;
    int cin = 0;
    String role="";
    String filename="";
    utilisateurs u = new utilisateurs();
    Paiement p = new Paiement();
    Reservation r = new Reservation();
    Ticket t = new Ticket();
    TicketService ts = new TicketService();
    utilisateurService us = new utilisateurService();
    ReservationService rs = new ReservationService();
    PaiementService ps = new PaiementService();
    List<Reservation> reservations = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void recupererTicket(ActionEvent event) {
        String sidCin = txtCin.getText();
                u.setCinUser("07239033");
                u.setIdUser(1);
        if(sidCin.length() == 8){
            try {
                cin = parseInt(sidCin);
                u=us.getUserByCin(sidCin);
                reservations = rs.getReservationByUser(u.getIdUser());
                reservation.setText("Merci de patientez");
                paiement.setText("Votre Ticket est en cours d'envoi");
                for(Reservation res : reservations){
                    p = ps.getPaiementByReservation(res);
                    t = ts.GetTicketByResAndPai(p, res);
                    filename = "R"+t.getIdTicket()+"VCHR";
                    try {
                        role = us.getRoleByCin(u.getCinUser());
                    } catch (SQLException ex) {
                        FXMain.showAlertWithHeaderText(u, "User Not Found", "-");
                    }
                    u.setRole(role);
                    PDFgenerator.setP(p);
                    PDFgenerator.setR(res);
                    PDFgenerator.setU(u);
                    PDFgenerator.setFilename(filename);
                    PDFgenerator.main();
                    boolean sent = Mailing.sendMail(u.getEmailUser(),t);
                }
            } catch (NumberFormatException a) {
                FXMain.showErrorAlert();
            }
        }else{
            FXMain.showErrorAlert();
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
