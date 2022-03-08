/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifierReclamtionFXMLController implements Initializable {

    @FXML
    private TextField txtdescrec;
    @FXML
    private Button btnmodi;
    @FXML
    private Button btngetback;
    ReclamationService rs = new ReclamationService();
    Reclamation reclamation = new Reclamation();
    Reclamation r = new Reclamation();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifierRec(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Réclamation Modifié avec succès "); //Succes
        //alert.setContentText("Réclamation Modifié avec succès");
        alert.showAndWait();

     /* int input = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment modifier ?", "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
      if (input==0) {
*/
        
        String desc_rec = txtdescrec.getText();
        
        r.setDesc_rec(desc_rec);
        r.setDateAjoutrec(reclamation.getDateAjoutrec());
        r.setDateTraitrec(reclamation.getDateTraitrec());
        r.setId_hbg(reclamation.getId_hbg());
        r.setId_user(reclamation.getId_user());
        r.setTraite(reclamation.getTraite());
        rs.modifierReclamation(r, reclamation.getId_rec());
        displayMenu();
        
     /* else {
          displayMenu();
          }
*/
      }
    

    @FXML
    private void loadMenu(ActionEvent event) {
        displayMenu();
    }
    
    private void displayMenu(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamationFXML.fxml"));
            Parent root =loader.load();
            AfficherReclamationFXMLController mr = loader.getController();
            btngetback.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void prepare(Reclamation r) {
        txtdescrec.setText(String.valueOf(r.getDesc_rec()));
        reclamation = r;
    }
    
}
