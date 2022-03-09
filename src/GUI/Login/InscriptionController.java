/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entités.utilisateurs;
import Service.utilisateurService;
import Tools.SendEmail;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Hassen Chouadah
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField nomtxt;
    @FXML
    private TextField prenomtxt;
    @FXML
    private TextField emailtxt;
    @FXML
    private TextField teltxt;
    @FXML
    private TextField cintxt;
    @FXML
    private TextField adressetxt;
    @FXML
    private TextField mdptxt;
    @FXML
    private ComboBox<String> comb;
    @FXML
    private PasswordField confirmationpassword;
    @FXML
    private Button Confirmerinscription;
    private utilisateurService US;
    private SendEmail sendEmail;
    @FXML
    private Label codeTxt;
    int generatedCode;
    @FXML
    private AnchorPane anchorHide;
    @FXML
    private Button confirmerCodebtn;
    @FXML
    private TextField codeActivation;
    @FXML
    private TextArea messageCode;
    @FXML
    private Pane voiturePane;
    @FXML
    private TextField voitureTxt;
    @FXML
    private Button quitBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Client", "Chauffeur");
        comb.setItems(list);
        US = new utilisateurService();
        generatedCode = 0;
        voiturePane.setVisible(false);
        comb.setValue("Client");
        comb.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue == "Chauffeur") {
                voiturePane.setVisible(true);
            } else {
                voiturePane.setVisible(false);
            }
        });
    }

    @FXML
    private void SignUpAction(ActionEvent event) {
        String role = comb.getSelectionModel().getSelectedItem().toString();
        if (nomtxt.getText().isEmpty() == false
                && prenomtxt.getText().isEmpty() == false
                && emailtxt.getText().isEmpty() == false && cintxt.getText().isEmpty() == false
                && adressetxt.getText().isEmpty() == false && mdptxt.getText().isEmpty() == false
                && confirmationpassword.getText().isEmpty() == false && mdptxt.getText().equals(confirmationpassword.getText())) {
            utilisateurs u = new utilisateurs(0, cintxt.getText(), nomtxt.getText(), prenomtxt.getText(), emailtxt.getText(), mdptxt.getText(), adressetxt.getText(), teltxt.getText(), role, voitureTxt.getText(), "");
            if (US.getUtilisateurByEmail(emailtxt.getText()) == true) {
                JOptionPane.showMessageDialog(null, "Un compte lié à cette adresse est déjà crée");
                System.out.println("utilisateur existe deja (email)");
            } else if (US.getUtilisateurByCin(cintxt.getText()) == true) {
                JOptionPane.showMessageDialog(null, "Un compte lié à cette CIN est déjà crée");
                System.out.println("utilisateur existe deja (cin)");
            } else {
                int resultat = US.ajouterUtilisateur(u);

                System.out.println("ajout");
                if (resultat == 1) {
                    codeTxt.setVisible(true);
                    anchorHide.setVisible(true);
                    confirmerCodebtn.setVisible(true);
                    codeActivation.setVisible(true);
                    messageCode.setVisible(true);
                }

                try {
                    int min = 10000;
                    int max = 99999;

                    generatedCode = (int) Math.floor(Math.random() * (max - min + 1) + min);
                    sendEmail = new SendEmail("hawes.voyage@gmail.com", "Hassen1998", emailtxt.getText(), "Activation de votre compte", "<h1> Bonjour , \n </h1> <h3>Afin de finaliser votre inscription , Voici un CODE pour verifier votre compte : " + generatedCode + "\n </h3>");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } else {
            addNotifications("erreur", "veuillez remplir les champs");
        }
    }

    @FXML
    private void confirmCode(ActionEvent event) {
        System.out.println(codeActivation.getText() + " vs " + Integer.toString(generatedCode));
        if (codeActivation.getText().equals(Integer.toString(generatedCode))) {
            US.verifierUtililsateur(cintxt.getText());
            JOptionPane.showMessageDialog(null, "Félicitations votre compte est désormais activé ");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            try {
                Parent root = loader.load();
                voitureTxt.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Code incorrect");
            System.out.println("code incorrect");
        }

    }

    private void addNotifications(String title, String content) {

        if (null != content) {
            if (content.length() > 50) {
                content = content.substring(0, 49) + "......";
            }
        }
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(content)
                .hideAfter(Duration.seconds(31536000))
                .position(Pos.BOTTOM_RIGHT);

        notificationBuilder.showInformation();
    }

    @FXML
    private void LoginRedirect(MouseEvent event) {
        {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));

        try {

            Parent root = loader.load();

            quitBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
}
