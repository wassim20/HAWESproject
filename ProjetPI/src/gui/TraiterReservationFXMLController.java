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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import services.PaiementService;
import services.ReservationService;
import services.TicketService;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class TraiterReservationFXMLController implements Initializable {

    @FXML
    private ListView txtlistReservation;
    @FXML
    private TextField txtidRes;
    @FXML
    private Button btnconfirmer;
    @FXML
    private RadioButton rdvalider;
    @FXML
    private RadioButton rdannuler;
    ToggleGroup rdchoix = new ToggleGroup();
    public int etat = 1;

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    Reservation r = new Reservation();
    ReservationService rs = new ReservationService();
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
        rdvalider.setToggleGroup(rdchoix);
        rdvalider.setSelected(true);
        rdannuler.setToggleGroup(rdchoix);
        
        ObservableList<String> items =FXCollections.observableArrayList();
        List<Reservation> listReservation = rs.afficherReservationNT();
        for(Reservation r : listReservation) {
            String ch = r.toString();
            items.add(ch);
        }
        txtlistReservation.setItems(items);
            rdvalider.setToggleGroup(rdchoice);
            rdvalider.setSelected(true);
            rdannuler.setToggleGroup(rdchoice);
    }
    @FXML
    private void traiterReservation(ActionEvent event) {
        //  System.out.println("Toggled: " + Integer.parseInt(rdchoice.getSelectedToggle().toString()));
        //  rdchoice.toString();
        String sidRes = txtidRes.getText();
        int idRes = parseInt(sidRes);
        r = rs.getReservationById(idRes);
        System.out.println(r);
        System.out.println(etat);
        if(etat == 1){
            rs.traiterReservation(r, 1);
        }else if(etat == -1){
            rs.traiterReservation(r, -1);
            Paiement p = ps.getPaiementByReservation(r);
            ps.annulerPaiement(p);
            int idTicket = ts.GetTicketByResAndPai(p,r);
            ts.supprimerTicket(idTicket);
        }
        refreshList();
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
    
    private void refreshList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TraiterReservationFXML.fxml"));
            Parent root =loader.load();
            //MenuReservationPaiementController mr = loader.getController();
            btnconfirmer.getScene().setRoot(root);
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void setEtatValid(ActionEvent event) {
         etat = 1;
    }

    @FXML
    private void CancelReservation(ActionEvent event) {
        etat = -1;
    }
}