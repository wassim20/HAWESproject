/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import services.ReservationService;
import entities.Reservation;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class AjoutReservationFXMLController implements Initializable {
    @FXML
    private Button btnAjouter;
    @FXML
    private DatePicker txtdateArr;
    @FXML
    private DatePicker txtdateDep;
    @FXML
    private TextField txtidVol;
    @FXML
    private ChoiceBox txtforfait = new ChoiceBox(FXCollections.observableArrayList(
    "All-in", "Pension Compléte", "Demi-Pension", "Petit Déjeuner"));
    @FXML
    private TextField txtnbPersonne;
    @FXML
    private TextField txtnbSuite;
    @FXML
    private TextField txtnbChambre;
    @FXML
    private Button btngetback;
    
    Reservation r = new Reservation();
    ReservationService rs = new ReservationService();
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
    
    @FXML
    private void addReservation(ActionEvent event) {
            DatePicker d = new DatePicker();
            try{
                String idVoltxt =txtidVol.getText();
                String nbPersonnetxt = txtnbPersonne.getText();
                String nbChambretxt = txtnbChambre.getText();
                String nbSuitetxt = txtnbSuite.getText();
                int idVol = Integer.parseInt(idVoltxt);
                int nbPersonne = Integer.parseInt(nbPersonnetxt);
                int nbSuite = Integer.parseInt(nbSuitetxt);
                int nbChambre = Integer.parseInt(nbChambretxt);
                r.setIdVol(idVol);
                r.setNbChambre(nbChambre);
                r.setNbPersonne(nbPersonne);
                r.setNbSuite(nbSuite);
                try{
                    r.setForfait(txtforfait.getValue().toString());
                    r.setIdHebr(1);
                    r.setIdRes(0);
                    r.setIdUser(1);
                    r.setValide(0);
                    //rs.ajouterReservation(r);
                    //r = rs.getIdByReservation(r);
                    try {
                        if( (txtdateArr.getValue()==null) || (txtdateDep.getValue()==null) ){
                            FXMain.showErrorAlert(); //// ERREUR DATE PICKER EMPTY
                        }else{
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutPaiementFXML.fxml"));
                            Parent root =loader.load();
                            AjoutPaiementFXMLController ap = loader.getController();
                            ap.prepare(r);
                            btnAjouter.getScene().setRoot(root);
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
    private void getDateArr(ActionEvent event){
        LocalDate dateArr = txtdateArr.getValue();
        LocalDate deadlineAnnulation = dateArr.minusDays(5);
        r.setDateArr(java.sql.Date.valueOf(dateArr));
        r.setDeadlineAnnulation(java.sql.Date.valueOf(deadlineAnnulation));
        UpdateDateDep(dateArr);
    }

    @FXML
    private void getDateDep(ActionEvent event){
        LocalDate dateDep = txtdateDep.getValue();
        r.setDateDep(java.sql.Date.valueOf(dateDep));
    }

    private void setChoiceBoxValue(MouseEvent event) {
        System.out.println(txtforfait.getValue());
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