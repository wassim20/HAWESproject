/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reservation;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class AfficherReservationsNTFXMLController implements Initializable {

    @FXML
    private ListView txtlistResNT;
    @FXML
    private TextField txtidRes;
    @FXML
    private Button btnModifier;
    Reservation r = new Reservation();
    ReservationService rs = new ReservationService();
    @FXML
    private Button btngetback;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReservationService rs = new ReservationService();
        ObservableList<String> items =FXCollections.observableArrayList();
        List<Reservation> listReservation = rs.afficherReservationNT();
        for(Reservation r : listReservation) {
            String ch = r.toString();
            items.add(ch);
        }
    txtlistResNT.setItems(items);
    }    

    @FXML
    private void modifierReservation(ActionEvent event) {
            String sIdRes = txtidRes.getText();
            int idRes = parseInt(sIdRes);
            r = rs.getReservationById(idRes);
        try {
            System.out.println(r);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReservationFXML.fxml"));
            Parent root =loader.load();
            
            ModifierReservationFXMLController mdr = loader.getController();
            mdr.prepare(r);
            btnModifier.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
