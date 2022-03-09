/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entités.utilisateurs;
import Service.utilisateurService;
import static Service.utilisateurService.currentUser;
import Tools.LocalStorage;
import Tools.MyConnexion;
import Tools.Statics;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Hassen Chouadah
 */
public class ModifierprofilController implements Initializable {

    @FXML
    private Button modifierprofilBtn;
    @FXML
    private TextField TelText;
    @FXML
    private TextField AdresseTxt;
    @FXML
    private TextField CinTxt;
    @FXML
    private TextField EmailTxt;
    private PasswordField MdpTxt;

    @FXML
    private ImageView profilimg;

    private String imgName;
    private Path imgPath;
    private utilisateurService US;
    private LocalStorage localStorage;
    @FXML
    private PasswordField oldpwtxt;
    @FXML
    private PasswordField newpwTxt;
    @FXML
    private PasswordField newpwTxt2;
    @FXML
    private Pane ModalPane;
    @FXML
    private Label UserRole;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        US = new utilisateurService();
        try {
            localStorage = new LocalStorage();
        } catch (IOException ex) {
            System.out.println("error init localstorage");
        }
        TelText.setText(currentUser.getTelUser());
        AdresseTxt.setText(currentUser.getAdresseUser());
        CinTxt.setText(currentUser.getCinUser());
        EmailTxt.setText(currentUser.getEmailUser());
        UserRole.setText(currentUser.getRole());
        
        imgName = currentUser.getImage();
           
        profilimg.setImage(new Image(Statics.imgPath + currentUser.getImage()));
        Rectangle clip = new Rectangle(
                profilimg.getFitWidth(), profilimg.getFitHeight()
        );
        clip.setArcWidth(200);
        clip.setArcHeight(200);
        profilimg.setClip(clip);

        ModalPane.setVisible(false);

    }

    @FXML
    private void ModifierInfromations(ActionEvent event) {
        if (!TelText.getText().isEmpty() && !AdresseTxt.getText().isEmpty() && !CinTxt.getText().isEmpty() && !EmailTxt.getText().isEmpty()) {
            utilisateurs newUser = new utilisateurs();
            newUser.setIdUser(US.currentUser.getIdUser());
            newUser.setNomUser(US.currentUser.getNomUser());
            newUser.setPrenomUser(US.currentUser.getPrenomUser());
            newUser.setRole(US.currentUser.getRole());

            newUser.setTelUser(TelText.getText());
            newUser.setAdresseUser(AdresseTxt.getText());
            newUser.setCinUser(CinTxt.getText());
            newUser.setEmailUser(EmailTxt.getText());

            

            File file = new File(Statics.uploadDirectory + imgName);
            if (imgPath != null) {
                try {
                    Files.copy(imgPath, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                newUser.setImage(imgName);

                US.modifierUtilisateur(newUser);
                US.updateCurrentUser(newUser);
                localStorage.deleteStorage();
                try {
                    localStorage.writeToStorage(newUser);
                } catch (IOException ex) {
                    System.out.println("error write to storage");
                }
            }

        } else {
            System.out.println("remplir les champs");
        }
    }

    @FXML
    private void ProfilRedirect(ActionEvent event) {
    }

    @FXML
    private void openFileChooser(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        Window stage = null;
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            imgName = file.getName();
            imgPath = file.toPath();
            Image image1 = new Image(imgPath.toUri().toString());
            profilimg.setImage(image1);
        }
    }

    @FXML
    private void confirmChangePassword(ActionEvent event) {
        if (oldpwtxt.getText().isEmpty()) {
            System.out.println("old password is empty ");
            return;
        }
        if (newpwTxt.getText().isEmpty()) {
            System.out.println("new password is empty ");
            return;
        }
        if (newpwTxt2.getText().isEmpty()) {
            System.out.println("confirm password is empty ");
            return;
        }

        String dBPasswordHashed = US.currentUser.getMdpUser();
        String oldPassHashed = US.mdpconvert(oldpwtxt.getText());

        if (!dBPasswordHashed.equals(oldPassHashed)) {
            addNotifications("erreur", "Ancien mot de passe incorrect");
            return;
        }

        if (newpwTxt.getText().isEmpty() != newpwTxt2.getText().isEmpty()) {
            addNotifications("erreur", "Les mots de passes ne sont pas identiques");
            return;
        }

        US.modifierMotdepasse(US.currentUser.getCinUser(), newpwTxt.getText());
        US.currentUser.setMdpUser(US.mdpconvert(newpwTxt.getText()));

        localStorage.deleteStorage();
        try {
            localStorage.writeToStorage(US.currentUser);
        } catch (IOException ex) {
            System.out.println("error write to storage");
        }

        ModalPane.setVisible(false);
        addNotifications("succés", "Votre mot de passe a été bien modifié");
    }

    @FXML
    private void closeModal(ActionEvent event) {
        ModalPane.setVisible(false);
    }

    @FXML
    private void openModal(ActionEvent event) {
        ModalPane.setVisible(true);
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
