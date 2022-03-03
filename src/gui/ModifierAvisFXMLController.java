/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Avis;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import services.AvisService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifierAvisFXMLController implements Initializable {

    @FXML
    private TextField txtdescavis;
    @FXML
    private RadioButton rd1;
    @FXML
    private RadioButton rd5;
    @FXML
    private RadioButton rd4;
    @FXML
    private RadioButton rd3;
    @FXML
    private RadioButton rd2;
    @FXML
    private Button btnmodifavis;
    @FXML
    private Button btngetback;
    ToggleGroup etoile = new ToggleGroup();
    AvisService as = new AvisService();
    Avis avis = new Avis();
    Avis a = new Avis();
    int choix = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rd1.setToggleGroup(etoile);
        rd2.setToggleGroup(etoile);
        rd3.setToggleGroup(etoile);
        rd4.setToggleGroup(etoile);
        rd5.setToggleGroup(etoile);
    }    

    @FXML
    private void modifierAvis(ActionEvent event) {
        String desc_avis = txtdescavis.getText();
        a.setDesc_avis(desc_avis);
        a.setDateAjoutavis(avis.getDateAjoutavis());
        a.setDesc_avis(desc_avis);
        a.setEtoile(choix);
        a.setId_avis(avis.getId_avis());
        a.setId_hbg(avis.getId_hbg());
        a.setId_user(avis.getId_user());
        as.modifierAvis(a, avis.getId_avis());
        displayMenu();
    }

    @FXML
    private void loadMenu(ActionEvent event) {
        displayMenu();
    }
    
    private void displayMenu(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuReclamationAvis.fxml"));
            Parent root =loader.load();
            MenuReclamationAvisController mr = loader.getController();
            btngetback.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void prepare(Avis a) {
        txtdescavis.setText(String.valueOf(a.getDesc_avis()));
        switch (a.getEtoile()) {
            case 1:
                rd1.setSelected(true);
                break;
            case 2:
                rd2.setSelected(true);
                break;
            case 3:
                rd3.setSelected(true);
                break;
            case 4:
                rd4.setSelected(true);
                break;
            case 5:
                rd5.setSelected(true);
                break;
            default:
                break;
        }
        avis = a;
    }
    
    @FXML
    private void setEtoile1(ActionEvent event) {
        choix = 1;
    }

    @FXML
    private void setEtoile5(ActionEvent event) {
        choix = 5;
    }

    @FXML
    private void setEtoile4(ActionEvent event) {
        choix = 4;
    }

    @FXML
    private void setEtoile3(ActionEvent event) {
        choix = 3;
    }

    @FXML
    private void setEtoile2(ActionEvent event) {
        choix = 2;
    }
    
}
