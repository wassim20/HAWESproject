/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reservation;

import Entités.Paiement;
import Entités.Reservation;
import Entités.Ticket;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import Service.PaiementService;
import Service.ReservationService;
import Service.TicketService;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class ModifierReservationFXMLController implements Initializable {

    @FXML
    private DatePicker txtdateArr;
    @FXML
    private DatePicker txtdateDep;
    @FXML
    private TextField txtidVol;
    @FXML
    private TextField txtnbPersonne;
    @FXML
    private TextField txtnbSuite;
    @FXML
    private TextField txtnbChambre;
    @FXML
    private Button btnModifier;
    @FXML
    private ChoiceBox txtforfait = new ChoiceBox(FXCollections.observableArrayList(
    "All-in", "Pension Compléte", "Demi-Pension", "Petit Déjeuner"));
    @FXML
    private Button btngetback;
    @FXML
    private AnchorPane anchor2;
    @FXML
    private Button btndelete;
    private Reservation reservation;
    
    Reservation r = new Reservation();
    Reservation nr = new Reservation();
    ReservationService rs = new ReservationService();
    PaiementService ps = new PaiementService();
    TicketService ts = new TicketService();
    Ticket t = new Ticket();
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> langs = FXCollections.observableArrayList("All-in", "Pension Compléte", "Demi-Pension", "Petit Déjeuner");
        txtforfait.setItems(langs);
        // Vérification date Arrivé
        LocalDate minDate = LocalDate.now();
        txtdateArr.setDayCellFactory(d ->
           new DateCell() {
               @Override public void updateItem(LocalDate item, boolean empty) {
                   super.updateItem(item, empty);
                   setDisable(item.isBefore(minDate));
               }});
        // Disable date Départ
        LocalDate maxDate = minDate.plusDays(1);
        txtdateDep.setDayCellFactory(d ->
           new DateCell() {
               @Override public void updateItem(LocalDate item, boolean empty) {
                   super.updateItem(item, empty);
                   setDisable(true);
               }});    
    }    
    
    public void UpdateDateDep(LocalDate dateArr){
        LocalDate maxDate = dateArr.plusDays(1);
        txtdateDep.setDayCellFactory(d ->
           new DateCell() {
               @Override public void updateItem(LocalDate item, boolean empty) {
                   super.updateItem(item, empty);
                   setDisable(item.isBefore(maxDate));
               }});
    }
    
    
    public void prepare(Reservation r){
        txtidVol.setText(String.valueOf(r.getIdVol()));
        txtnbChambre.setText(String.valueOf(r.getNbChambre()));
        txtnbPersonne.setText(String.valueOf(r.getNbPersonne()));
        txtnbSuite.setText(String.valueOf(r.getNbSuite()));
        txtforfait.setValue(r.getForfait());
        txtdateArr.setValue(r.getDateArr().toLocalDate());
        txtdateDep.setValue(r.getDateDep().toLocalDate());
        nr.setDateArr(r.getDateArr());
        nr.setDateDep(r.getDateDep());
        nr.setDateRes(r.getDateRes());
        nr.setDeadlineAnnulation(r.getDeadlineAnnulation());
        reservation = r;
    }
    
    @FXML
    private void getDateArr(ActionEvent event) {
        LocalDate dateArr = txtdateArr.getValue();
        LocalDate deadlineAnnulation = dateArr.minusDays(5);
        nr.setDateArr(java.sql.Date.valueOf(dateArr));
        nr.setDeadlineAnnulation(java.sql.Date.valueOf(deadlineAnnulation));
        UpdateDateDep(dateArr);
    }

    @FXML
    private void getDateDep(ActionEvent event) {
        LocalDate dateDep = txtdateDep.getValue();
        nr.setDateDep(java.sql.Date.valueOf(dateDep));
    }
    
    private void setDateArr(ActionEvent event) {
        LocalDate dateArr = reservation.getDateArr().toLocalDate();
        txtdateArr.setValue(dateArr);
    }
    
    private void setDateDep(ActionEvent event) {
        LocalDate dateDep = reservation.getDateDep().toLocalDate();
        txtdateDep.setValue(dateDep);
    }

    @FXML
    private void editReservation(ActionEvent event) {
        String idVoltxt =txtidVol.getText();
        String nbPersonnetxt = txtnbPersonne.getText();
        String nbChambretxt = txtnbChambre.getText();
        String nbSuitetxt = txtnbSuite.getText();
        try{
            int idVol = Integer.parseInt(idVoltxt);
            int nbPersonne = Integer.parseInt(nbPersonnetxt);
            int nbSuite = Integer.parseInt(nbSuitetxt);
            int nbChambre = Integer.parseInt(nbChambretxt);
            FXMain.showAlertWithHeaderText(nr,"","*");
            try{        
                nr.setForfait(txtforfait.getValue().toString());
                nr.setIdHebr(reservation.getIdHebr());
                nr.setIdUser(reservation.getIdUser());
                nr.setIdRes(reservation.getIdRes());
                nr.setIdVol(idVol);
                nr.setNbChambre(nbChambre);
                nr.setNbPersonne(nbPersonne);
                nr.setNbSuite(nbSuite);
                nr.setDateRes(reservation.getDateRes());
                rs.modifierReservation(reservation,nr);
                //rs.ajouterReservation(r);
                //r = rs.getIdByReservation(r);
                try {
                    if( (txtdateArr.getValue()==null) || (txtdateDep.getValue()==null) ){
                        FXMain.showErrorAlert(); //// ERREUR DATE PICKER EMPTY
                    }else{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReservationsFXML.fxml"));
                    Parent root =loader.load();
                    AfficherReservationsFXMLController ar = loader.getController();
                    btnModifier.getScene().setRoot(root);
                }
                } catch (IOException ex) {
                System.out.println(ex.getMessage()); //// ERREUR FXML.LOADER
                }
            }catch (RuntimeException r){
            FXMain.showErrorAlert();        //// ERREUR CHOICEBOX EMPTY
            }
        }catch (NumberFormatException e){
            FXMain.showErrorAlert();  ///// ERREUR CHAMPS NUMERIQUE
        }
    }
    
    @FXML
    private void loadMenu(ActionEvent event) {
        displayMenu();
    }

    @FXML
    private void deleteReservation(ActionEvent event) {
        r = rs.getIdByReservation(reservation);
        int c = FXMain.showConfirmationAlert(r);
        if(c == 0){
            FXMain.showAlertWithHeaderText(r,"Annuler","-");            
            displayMenu();
        }else{
        if(r.getValide()!=-1){
            rs.traiterReservation(r, -1);
            Paiement p = ps.getPaiementByReservation(r);
            System.out.println(p);
            ps.annulerPaiement(p);
            t = ts.GetTicketByResAndPai(p,r);
            //System.out.println(t.getIdTicket());
            try{
            ts.supprimerTicket(t.getIdTicket());   
            t = ts.GetTicketById(t.getIdTicket());
            System.out.println(t);
            if(t.getDeleted()==1){
                FXMain.showAlertWithHeaderText(t, "", "-");
                displayMenu();
            }else{
                FXMain.showAlertWithHeaderText(t,"Erreur lors du suppression","-");
                displayMenu();
            }
            }catch( RuntimeException e){
                FXMain.showAlertWithHeaderText(t, e.getMessage(), "-");
                displayMenu();
            }
        }else{
            FXMain.showAlertWithHeaderText(r,"Réservation Déjà Annulé","-");            
        }
        }
    }
    
    private void displayMenu(){
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
