/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entités.utilisateurs;
import Service.utilisateurService;
import static Service.utilisateurService.currentUser;
import Tools.SendEmail;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Hassen Chouadah
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtmail;
    @FXML
    private TextField txtpassword;
    @FXML
    private Button seconnecter;
    @FXML
    private Button sinscrire;
    @FXML
    private Label mdpoublie;
    private Label erreur;

    private SendEmail sendEmail;
    @FXML
    private TextField emailverif;
    @FXML
    private Label label_email;

    int generatedCode;
    @FXML
    private Pane forgetPasswordPane;
    @FXML
    private TextField codeInput;
    @FXML
    private Pane VerifyCodePane;
    @FXML
    private Pane ChangePasswordPane;
    @FXML
    private TextField oldPassword;
    @FXML
    private TextField newPassword;
    private utilisateurService US;
    @FXML
    private CheckBox rememberMe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        generatedCode = 0;
        forgetPasswordPane.setVisible(false);
        VerifyCodePane.setVisible(false);
        ChangePasswordPane.setVisible(false);
        US = new utilisateurService();
    }

    @FXML
    private void SeConnecter(ActionEvent event) throws IOException {

        String email = txtmail.getText();
        String pwd = txtpassword.getText();
        if (email.isEmpty() || pwd.isEmpty()) {
            addNotifications("erreur", "Les champs sont vides ou incorrects");
        } else {
            utilisateurService us = new utilisateurService();

            String output = us.Seconnecter(email, pwd, rememberMe.isSelected());

            if (output == "success") {
                if (currentUser.getRole().equals("Administrateur")) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Template.fxml"));

                    try {
                        Parent root = loader.load();
                        txtmail.getScene().setRoot(root);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (currentUser.getRole().equals("Client")) {
                    Stage stage = (Stage) txtpassword.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Template.fxml"));
                    Parent root = loader.load();
                    Stage stageprofil = new Stage();
                    stageprofil.show();
                } else if (currentUser.getRole().equals("Chef d'agence")) {
                    Stage stage = (Stage) txtpassword.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Template.fxml"));
                    Parent root = loader.load();
                    Stage stageprofil = new Stage();
                    stageprofil.show();
                } else if (currentUser.getRole().equals("Chauffeur")) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Template.fxml"));

                    try {
                        Parent root = loader.load();
                        txtmail.getScene().setRoot(root);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                }else{
                    System.out.println("role invalid");
                }
            } else {
                addNotifications("erreur", "quelque chose s'est mal passé");
            }

        }
    }

    private void setLblError(Color color, String text) {
        erreur.setTextFill(color);
        erreur.setText(text);
        System.out.println(text);
    }

    @FXML
    private void Sinscrire(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("inscription.fxml"));

        try {

            Parent root = loader.load();

            txtmail.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void mdpoublieAction(MouseEvent event) {

        forgetPasswordPane.setVisible(true);
    }

    @FXML
    private void sendForgetPasswordCode(ActionEvent event) {
        boolean exist = US.getUtilisateurByEmail(emailverif.getText());
        if (exist) {
            try {
                int min = 10000;
                int max = 99999;

                generatedCode = (int) Math.floor(Math.random() * (max - min + 1) + min);
                sendEmail = new SendEmail("hawes.voyage@gmail.com", "Hassen1998", emailverif.getText(), "Mot de passe oublié", "<h3> Voici un CODE pour modifier votre mot de passe : " + generatedCode + "\n </h3>");
                forgetPasswordPane.setVisible(false);
                VerifyCodePane.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            addNotifications("erreur", "utlisateur n'exsite pas");
        }

    }

    @FXML
    private void VerifyCode(ActionEvent event) {
        if (Integer.toString(generatedCode).equals(codeInput.getText())) {
            System.out.println("code correct");

            VerifyCodePane.setVisible(false);
            ChangePasswordPane.setVisible(true);
        } else {
            addNotifications("erreur", "code incorrect");
        }
    }

    @FXML
    private void ChangePassword(ActionEvent event) {
        if (oldPassword.getText().equals(newPassword.getText())) {
            US.ChangePasswordWithEmail(emailverif.getText(), newPassword.getText());
            ChangePasswordPane.setVisible(false);
            addNotifications("erreur", "Mot de passe modifier avec succées");
        } else {
            addNotifications("erreur", "les mots de passes ne sont pas identiques");
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

}
