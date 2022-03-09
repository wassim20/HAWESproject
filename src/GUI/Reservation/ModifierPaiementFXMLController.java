/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reservation;

import Entités.Paiement;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import Service.PaiementService;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class ModifierPaiementFXMLController implements Initializable {

    @FXML
    private ChoiceBox txtmethode;
    @FXML
    private TextField txtmontant;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btngetback;
    private Paiement paiement;
    Paiement p = new Paiement();
    PaiementService ps = new PaiementService();
    @FXML
    private AnchorPane sideBarPane;
    @FXML
    private Button gestionUserButton;
    @FXML
    private Button gestionHebergementButton;
    @FXML
    private Button gestionPickupButton;
    @FXML
    private Button gestionAvisButton;
    @FXML
    private Button gestionEvenementButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button gestionPaiementButton;
    @FXML
    private Button gestionRelamationButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        ObservableList<String> langs = FXCollections.observableArrayList("Especes", "Cheque", "Virement");
        txtmethode.setItems(langs);
    }    

    public void prepare(Paiement p){
        txtmontant.setText(String.valueOf(p.getMontant()));
        txtmethode.setValue(p.getMethode());
        paiement = p;
    }    
    
    @FXML
    private void editPaiement(ActionEvent event) {
        Paiement np = new Paiement();
        np = paiement;
        String montanttxt =txtmontant.getText();
        try{
            Double montant = parseDouble(montanttxt);
            np.setMontant(montant);
            try{
                np.setMethode(txtmethode.getValue().toString());
                ps.modifierPaiement(paiement, np);
                System.out.println(np);
                System.out.println(paiement);
                FXMain.showAlertWithHeaderText(np,"","*");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuReservationPaiement.fxml"));
                    Parent root =loader.load();
                    MenuReservationPaiementController mr = loader.getController();
                    btngetback.getScene().setRoot(root);
                } catch (IOException ex) {
                    FXMain.showAlertWithHeaderText(np,ex.getMessage(),".");
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

    @FXML
    private void gestionReserations(ActionEvent event) {
    }
    
}
