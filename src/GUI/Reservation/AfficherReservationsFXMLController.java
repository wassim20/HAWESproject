/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reservation;
import Entités.Reservation;
import Entités.utilisateurs;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
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
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import Service.ReservationService;
import Service.utilisateurService;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class AfficherReservationsFXMLController implements Initializable {

    @FXML
    private ListView<Reservation> txtlistReservation;
    private Reservation r = new Reservation();
    private utilisateurs u = new utilisateurs();
    private utilisateurService us;
    private ReservationService rs = new ReservationService();
    @FXML
    private RadioButton rdafft;
    @FXML
    private RadioButton rdaffc;
    @FXML
    private RadioButton rdaffa;
    @FXML
    private RadioButton rdaffnt;
    @FXML
    private TextField txtsearch;
    @FXML
    private Button btngetback;
    private String role;
    ToggleGroup rdaff = new ToggleGroup();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        us = new utilisateurService();
            rdafft.setToggleGroup(rdaff);
            rdafft.setSelected(true);
            rdaffc.setToggleGroup(rdaff);
            rdaffa.setToggleGroup(rdaff);
            rdaffnt.setToggleGroup(rdaff);
            u= us.currentUser;
            role= u.getRole();
            if(role.equals("Administrateur")){
                ObservableList<Reservation> items =FXCollections.observableArrayList();
                List<Reservation> listReservation = rs.afficherReservation(u);
                for(Reservation r : listReservation) {
                    items.add(r);
                }
                txtlistReservation.setItems(items);
            }else if(role.equals("Client")){
                ObservableList<Reservation> items =FXCollections.observableArrayList();
                List<Reservation> listReservation = rs.afficherMesReservation(u);
                for(Reservation r : listReservation) {
                    items.add(r);
                }
                txtlistReservation.setItems(items);
            }
    }
    
    public void prepareUD(MouseEvent click) throws Exception {
        if (click.getClickCount() == 2) {
            Reservation r = txtlistReservation.getSelectionModel().getSelectedItem();
            r= rs.getIdByReservation(r);
            try {
                System.out.println(r);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReservationFXML.fxml"));
                Parent root =loader.load();

                ModifierReservationFXMLController mdr = loader.getController();
                mdr.prepare(r);
                txtlistReservation.getScene().setRoot(root);
            } catch (IOException ex) {
                    FXMain.showAlertWithHeaderText(r,ex.getMessage(),".");
            }   
        }
    }    
    
    public void setTxtlistReservation(ListView<Reservation> txtlistReservation) {
        this.txtlistReservation = txtlistReservation;
    }
    
    @FXML
    private void afficherReservations(ActionEvent event) {
        ObservableList<Reservation> items =FXCollections.observableArrayList();
        List<Reservation> listReservation = rs.afficherReservation(u);
        for(Reservation r : listReservation) {
            String ch = r.toString();
            items.add(r);
        }
    txtlistReservation.setItems(items);
    }

    @FXML
    private void afficherReservationCourantes(ActionEvent event) {
        ObservableList<Reservation> items =FXCollections.observableArrayList();
        List<Reservation> listReservation = rs.afficherReservationC(u);
        for(Reservation r : listReservation) {
            String ch = r.toString();
            items.add(r);
        }
    txtlistReservation.setItems(items);
    }

    @FXML
    private void afficherReservationsAnnulees(ActionEvent event) {
        ObservableList<Reservation> items =FXCollections.observableArrayList();
        List<Reservation> listReservation = rs.afficherReservationA(u);
        for(Reservation r : listReservation) {
            String ch = r.toString();
            items.add(r);
        }
    txtlistReservation.setItems(items);
    }

    @FXML
    private void afficherReservationNonTraites(ActionEvent event) {
        ObservableList<Reservation> items =FXCollections.observableArrayList();
        List<Reservation> listReservation = rs.afficherReservationNT(u);
        for(Reservation r : listReservation) {
            String ch = r.toString();
            items.add(r);
        }
    txtlistReservation.setItems(items);
    }
    
    @FXML
    private void chercherReservation(KeyEvent event) {
        List<Reservation> listReservation;
        String tchoix=txtsearch.getText();
        try{
            int nchoix = Integer.parseInt(tchoix);
            listReservation = rs.chercheReservation(nchoix);
        } catch (NumberFormatException e) {
            listReservation = rs.chercheReservation(tchoix);
        }
        ObservableList<Reservation> items =FXCollections.observableArrayList();
        for(Reservation r : listReservation) {
            String ch = r.toString();
            items.add(r);
        }
        txtlistReservation.setItems(items);
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
 ///    RECHERCHE AVANCEE           - DONE
 ///    STATISTIQUES                - PROBLEM
 ///    EDIT RESERVATION            - DONE
 ///    CANCEL RESERVATION          - DONE