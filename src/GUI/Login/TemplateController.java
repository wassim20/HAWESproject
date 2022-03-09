/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Service.RealTimeService;
import Service.utilisateurService;
import Tools.LocalStorage;
import Tools.Statics;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Hassen Chouadah
 */
public class TemplateController implements Initializable {

    @FXML
    private AnchorPane sideBarPane;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane ProfilePane;

    @FXML
    private Button logoutButton;
    @FXML
    private Button gestionUserButton;
    @FXML
    private Button gestionHebergementButton;
    @FXML
    private Button gestionReservationsButton;
    @FXML
    private Button gestionPickupButton;
    @FXML
    private Button gestionAvisButton;
    @FXML
    private Button gestionEvenementButton;
    @FXML
    private Button gestionPaiementButton;
    @FXML
    private Label connectedFullname;
    @FXML
    private Label connectedRole;
    @FXML
    private ImageView connectedImage;

    LocalStorage localStorage;
    utilisateurService US;
    RealTimeService realTime;
    @FXML
    private Button gestionRelamationButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnchorPane pane;
        US = new utilisateurService();
        try {
            realTime = new RealTimeService();
        } catch (URISyntaxException ex) {
            System.out.println("error in init realtime service");
        }

        connectedFullname.setText(US.currentUser.getPrenomUser() + " " + US.currentUser.getNomUser());
        connectedRole.setText(US.currentUser.getRole());
        Image img = new Image(Statics.imgPath + US.currentUser.getImage());
        connectedImage.setImage(img);
        try {
            pane = FXMLLoader.load(getClass().getResource("InterfaceAdmin.fxml"));
            mainPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void gestionUser(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("InterfaceAdmin.fxml"));
            mainPane.getChildren().setAll(pane);
            defaultStateButtons();
            gestionUserButton.setTextFill(Color.WHITE);
            gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void defaultStateButtons() {
        gestionUserButton.setTextFill(Color.web("#5b4ebd"));
        gestionUserButton.setStyle("-fx-background-color :#ffffff");

        gestionHebergementButton.setTextFill(Color.web("#5b4ebd"));
        gestionHebergementButton.setStyle("-fx-background-color :#ffffff");

        gestionReservationsButton.setTextFill(Color.web("#5b4ebd"));
        gestionReservationsButton.setStyle("-fx-background-color :#ffffff");

        gestionPickupButton.setTextFill(Color.web("#5b4ebd"));
        gestionPickupButton.setStyle("-fx-background-color :#ffffff");

        gestionAvisButton.setTextFill(Color.web("#5b4ebd"));
        gestionAvisButton.setStyle("-fx-background-color :#ffffff");

        gestionRelamationButton.setTextFill(Color.web("#5b4ebd"));
        gestionRelamationButton.setStyle("-fx-background-color :#ffffff");

        gestionEvenementButton.setTextFill(Color.web("#5b4ebd"));
        gestionEvenementButton.setStyle("-fx-background-color :#ffffff");

        gestionPaiementButton.setTextFill(Color.web("#5b4ebd"));
        gestionPaiementButton.setStyle("-fx-background-color :#ffffff");
    }

    @FXML
    private void GestionHebergement(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("test.fxml"));
            mainPane.getChildren().setAll(pane);
            defaultStateButtons();
            gestionHebergementButton.setTextFill(Color.WHITE);
            gestionHebergementButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gestionReserations(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("../Reservation/MenuReservationPaiement.fxml"));
            mainPane.getChildren().setAll(pane);
            defaultStateButtons();
            gestionReservationsButton.setTextFill(Color.WHITE);
            gestionReservationsButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void gestionPickup(ActionEvent event) {

        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("test.fxml"));
            mainPane.getChildren().setAll(pane);
            defaultStateButtons();
            gestionPickupButton.setTextFill(Color.WHITE);
            gestionPickupButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gestionAvis(ActionEvent event) {

        AnchorPane pane;
        try {
            if (US.currentUser.getRole().equals("Administrateur")) {
                pane = FXMLLoader.load(getClass().getResource("../Avis/AfficherAvisAdminFXML.fxml"));
            } else {
                pane = FXMLLoader.load(getClass().getResource("../Avis/AfficherAvisFXML.fxml"));
            }

            mainPane.getChildren().setAll(pane);
            defaultStateButtons();
            gestionAvisButton.setTextFill(Color.WHITE);
            gestionAvisButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gestionReclamation(ActionEvent event) {
        AnchorPane pane;
        try {

            if (US.currentUser.getRole().equals("Administrateur")) {
                pane = FXMLLoader.load(getClass().getResource("../Avis/AfficherReclamationAdminFXML.fxml"));
            } else {
                pane = FXMLLoader.load(getClass().getResource("../Avis/AfficherReclamationFXML.fxml"));
            }
            
            mainPane.getChildren().setAll(pane);
            defaultStateButtons();
            gestionRelamationButton.setTextFill(Color.WHITE);
            gestionRelamationButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gestionPaiement(ActionEvent event) {

        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("test.fxml"));
            mainPane.getChildren().setAll(pane);
            defaultStateButtons();
            gestionPaiementButton.setTextFill(Color.WHITE);
            gestionPaiementButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gestionEvenement(ActionEvent event) {
        AnchorPane pane;
        try {
            if (US.currentUser.getRole().equals("Administrateur")) {
                pane = FXMLLoader.load(getClass().getResource("../Events/InterfaceAdmin.fxml"));
            } else {
                pane = FXMLLoader.load(getClass().getResource("../Events/InterfaceClient.fxml"));
            }
            
            
            mainPane.getChildren().setAll(pane);
            defaultStateButtons();
            gestionEvenementButton.setTextFill(Color.WHITE);
            gestionEvenementButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Messagerie(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("Chat.fxml"));
            mainPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logoutAction(ActionEvent event) {
        try {
            localStorage = new LocalStorage();
            localStorage.deleteStorage();

            System.out.println("closed connection");
            realTime.closeConnection();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            try {
                Parent root = loader.load();
                logoutButton.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (IOException ex) {
            System.out.println("error in init localstorage in logout");
        }
    }

    @FXML
    private void voirProfileAction(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("modifierprofil.fxml"));
            mainPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
