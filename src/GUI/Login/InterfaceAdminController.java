/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entités.utilisateurs;
import Service.utilisateurService;
import Tools.MyConnexion;
import Tools.Statics;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Hassen Chouadah
 */
public class InterfaceAdminController implements Initializable {

    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    String query = null;
    utilisateurService us;
    List<utilisateurs> utilisateurs;
    List<utilisateurs> AllUsers;
    private TextField roleTxt;
    private Pane HiddenPane;
    @FXML
    private Label BonjourPrenom;

    @FXML
    private VBox usersList;
    @FXML
    private Pane ModalPane;
    @FXML
    private ComboBox<String> roleComboBox;

    public int userToUpdateIndex;
    @FXML
    private Button TousFilterBtn;
    @FXML
    private Button AdminFilterBtn;
    @FXML
    private Button ClientsFilterBtn;
    @FXML
    private Button ChauffeurFilterBtn;
    @FXML
    private Button ChefFilterBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            us = new utilisateurService();
            utilisateurs = new ArrayList<>();
            utilisateurs = us.getUsers();
            AllUsers = utilisateurs;
            BonjourPrenom.setText("Bonjour " + us.currentUser.getPrenomUser());
            displayUsers(utilisateurs);

            roleComboBox.setItems(FXCollections.observableArrayList("Administrateur", "Chef d'agence", "Chauffeur"));
            userToUpdateIndex = 0;
            ModalPane.setVisible(false);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void displayUsers(List<utilisateurs> users) {

        usersList.getChildren().clear();
        for (int i = 0; i < users.size(); i++) {
            utilisateurs current = users.get(i);
            int currentIndex = i;
            Pane userPane = new Pane();
            userPane.setBackground(Background.EMPTY);
            userPane.setPrefHeight(55);
            userPane.setPrefWidth(745);
            userPane.setLayoutX(0);
            userPane.setLayoutY(0);

            userPane.setOnMouseEntered((MouseEvent event) -> {
                userPane.setBackground(new Background(new BackgroundFill(Color.web("#f0f2f5"), new CornerRadii(7), Insets.EMPTY)));
            });

            userPane.setOnMouseExited((MouseEvent event) -> {
                userPane.setBackground(Background.EMPTY);
            });

            ImageView img = new ImageView();
            img.setFitHeight(44);
            img.setFitWidth(44);
            img.setPreserveRatio(false);
            img.setLayoutX(5);
            img.setLayoutY(5);
            Image image = new Image(Statics.imgPath + current.getImage());
            img.setImage(image);

            Label fullName = new Label();
            fullName.setPrefHeight(44);
            fullName.setPrefWidth(134);
            fullName.setLayoutX(51);
            fullName.setLayoutY(5);
            fullName.setTextFill(Color.BLACK);
            fullName.setText(current.getPrenomUser() + " " + current.getNomUser());

            Label cin = new Label();
            cin.setPrefHeight(44);
            cin.setPrefWidth(71);
            cin.setLayoutX(173);
            cin.setLayoutY(5);
            cin.setTextFill(Color.BLACK);
            cin.setText(current.getCinUser());

            Label email = new Label();
            email.setPrefHeight(44);
            email.setPrefWidth(186);
            email.setLayoutX(247);
            email.setLayoutY(5);
            email.setTextFill(Color.BLACK);
            email.setText(current.getEmailUser());

            Label tel = new Label();
            tel.setPrefHeight(44);
            tel.setPrefWidth(71);
            tel.setLayoutX(436);
            tel.setLayoutY(5);
            tel.setTextFill(Color.BLACK);
            tel.setText(current.getTelUser());

            Label role = new Label();
            role.setPrefHeight(44);
            role.setPrefWidth(107);
            role.setLayoutX(509);
            role.setLayoutY(5);
            role.setTextFill(Color.BLACK);
            role.setText(current.getRole());

            Image updateImg = new Image(Statics.imgPath + "edit.png", 20, 20, false, true);
            Button updateBtn = new Button("", new ImageView(updateImg));
            updateBtn.setPrefHeight(44);
            updateBtn.setPrefWidth(40);
            updateBtn.setLayoutX(662);
            updateBtn.setLayoutY(5);

            Image deleteImg = new Image(Statics.imgPath + "delete.png", 20, 20, false, true);
            Button deleteBtn = new Button("", new ImageView(deleteImg));
            deleteBtn.setPrefHeight(44);
            deleteBtn.setPrefWidth(40);
            deleteBtn.setLayoutX(705);
            deleteBtn.setLayoutY(5);

            userPane.getChildren().add(img);
            userPane.getChildren().add(fullName);
            userPane.getChildren().add(cin);
            userPane.getChildren().add(email);
            userPane.getChildren().add(tel);
            userPane.getChildren().add(role);
            userPane.getChildren().add(updateBtn);
            userPane.getChildren().add(deleteBtn);
            updateBtn.setOnMouseClicked((MouseEvent event) -> {
                roleComboBox.getSelectionModel().select(current.getRole());
                userToUpdateIndex = currentIndex;
                ModalPane.setVisible(true);
            });

            deleteBtn.setOnMouseClicked((MouseEvent event) -> {
                int input = JOptionPane.showConfirmDialog(null, "Etes-vous sure de supprimer cet utilisateur ?", "Confirmer la supression",
                        JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

                if (input == 0) {
                    us.supprimerUtilisateur(current.getIdUser());
                    utilisateurs.remove(current);
                    addNotifications("succés", "utilisateur supprimé avec succés");
                    displayUsers(utilisateurs);
                };
            });

            usersList.getChildren().add(userPane);
        }

    }

    @FXML
    private void closeModal(ActionEvent event) {
        ModalPane.setVisible(false);
    }

    @FXML
    private void confirmUpadteRole(ActionEvent event) {
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment modifier le role ?", "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

        // 0=yes, 1=no, 2=cancel
        if (input == 0) {
            us.ModifierUtilisateurByRole(roleComboBox.getValue(), utilisateurs.get(userToUpdateIndex).getCinUser());
            utilisateurs updatedUser = utilisateurs.get(userToUpdateIndex);
            updatedUser.setRole(roleComboBox.getValue());

            utilisateurs.set(userToUpdateIndex, updatedUser);
            addNotifications("succés", "Role modifié avec succés");
            displayUsers(utilisateurs);
            ModalPane.setVisible(false);
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

    private void defaultStateButtons() {

        TousFilterBtn.setTextFill(Color.web("#000000"));
        TousFilterBtn.setStyle("-fx-background-color :#ffffff");

        AdminFilterBtn.setTextFill(Color.web("#000000"));
        AdminFilterBtn.setStyle("-fx-background-color :#ffffff");

        ClientsFilterBtn.setTextFill(Color.web("#000000"));
        ClientsFilterBtn.setStyle("-fx-background-color :#ffffff");

        ChauffeurFilterBtn.setTextFill(Color.web("#000000"));
        ChauffeurFilterBtn.setStyle("-fx-background-color :#ffffff");

        ChefFilterBtn.setTextFill(Color.web("#000000"));
        ChefFilterBtn.setStyle("-fx-background-color :#ffffff");

    }

    @FXML
    private void FilterTous(ActionEvent event) {
        defaultStateButtons();
        TousFilterBtn.setTextFill(Color.web("#5b4ebd"));
        TousFilterBtn.setStyle("-fx-background-color :#ffffff");

        utilisateurs = AllUsers;
        displayUsers(utilisateurs);
    }

    @FXML
    private void FilterAdministrateur(ActionEvent event) {
        defaultStateButtons();
        AdminFilterBtn.setTextFill(Color.web("#5b4ebd"));
        AdminFilterBtn.setStyle("-fx-background-color :#ffffff");

        utilisateurs = AllUsers.stream().filter((user) -> {
            return user.getRole().equals("Administrateur");
        }).collect(Collectors.toList());
        displayUsers(utilisateurs);
    }

    @FXML
    private void FilterClients(ActionEvent event) {
        defaultStateButtons();
        ClientsFilterBtn.setTextFill(Color.web("#5b4ebd"));
        ClientsFilterBtn.setStyle("-fx-background-color :#ffffff");

        utilisateurs = AllUsers.stream().filter((user) -> {
            return user.getRole().equals("Client");
        }).collect(Collectors.toList());
        displayUsers(utilisateurs);
    }

    @FXML
    private void FilterChauffeur(ActionEvent event) {
        defaultStateButtons();
        ChauffeurFilterBtn.setTextFill(Color.web("#5b4ebd"));
        ChauffeurFilterBtn.setStyle("-fx-background-color :#ffffff");

        utilisateurs = AllUsers.stream().filter((user) -> {
            return user.getRole().equals("Chauffeur");
        }).collect(Collectors.toList());
        displayUsers(utilisateurs);
    }

    @FXML
    private void FiltrerChef(ActionEvent event) {
        defaultStateButtons();
        ChefFilterBtn.setTextFill(Color.web("#5b4ebd"));
        ChefFilterBtn.setStyle("-fx-background-color :#ffffff");

        utilisateurs = AllUsers.stream().filter((user) -> {
            return user.getRole().equals("Chef d'agence");
        }).collect(Collectors.toList());
        displayUsers(utilisateurs);
    }

}
