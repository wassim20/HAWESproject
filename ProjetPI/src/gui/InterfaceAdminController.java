/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.utilisateurs;
import services.utilisateurService;
import tools.MaConnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hassen
 */
public class InterfaceAdminController implements Initializable {

    @FXML
    private TableColumn<utilisateurs, Integer> idCol;
    @FXML
    private TableColumn<utilisateurs, String> nomCol;
    @FXML
    private TableColumn<utilisateurs, String> prenomCol;
    @FXML
    private TableColumn<utilisateurs, String> cinCol;
    @FXML
    private TableColumn<utilisateurs, String> telCol;
    @FXML
    private TableColumn<utilisateurs, String> adresseCol;
    @FXML
    private TableColumn<utilisateurs, String> emailCol;
    @FXML
    private TableColumn<utilisateurs, String> roleCol;
    @FXML
    private TableView<utilisateurs> tableUsers;
    @FXML
    private AnchorPane anchor;
    @FXML
    private AnchorPane anchor2;
    @FXML
    private AnchorPane anchor3;

    /**
     * Initializes the controller class.
     */
    String query = null;

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    utilisateurs user = null;
    Connection cnx = null;

    ObservableList<utilisateurs> usersList = FXCollections.observableArrayList();
    @FXML
    private Button deleteusrBtn;
    @FXML
    private Button confirmroleBtn;
    @FXML
    private TextField roleTxt;
    @FXML
    private Button ModifierRoleBtn;
    @FXML
    private Pane HiddenPane;
    @FXML
    private Button ajouteruserBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadData(); // TODO
        

    }

    private void loadData() {

        cnx = MaConnexion.getInstance().getCnx();
        refreshTable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenomUser"));
        cinCol.setCellValueFactory(new PropertyValueFactory<>("cin"));
        telCol.setCellValueFactory(new PropertyValueFactory<>("telUser"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresseUser"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("emailUser"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

    }

    private void refreshTable() {
        try {
            usersList.clear();

            query = "SELECT * FROM `utilisateurs`";
            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                usersList.add(new utilisateurs(
                        resultSet.getInt("idUser"),
                        resultSet.getString("nomUser"),
                        resultSet.getString("prenomUser"),
                        resultSet.getString("cin"),
                        resultSet.getString("telUser"),
                        resultSet.getString("adresseUser"),
                        resultSet.getString("emailUser"),
                        resultSet.getString("role")));
                tableUsers.setItems(usersList);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

   

    @FXML
    private void deleteUserConfirm(MouseEvent event) {
        int input = JOptionPane.showConfirmDialog(null, "Etes-vous sure de supprimer cet utilisateur ?", "Confirmer la supression",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {
            utilisateurService us = new utilisateurService();
            user = tableUsers.getSelectionModel().getSelectedItem();
            us.supprimerUtilisateur(user.getIdUser());
            loadData();
   }
        // 0=yes, 1=no, 2=cancel
        System.out.println(input);
    }


    @FXML
    private void ModifierRole(MouseEvent event) {
     cnx = MaConnexion.getInstance().getCnx();
        utilisateurService us = new utilisateurService();
       user = tableUsers.getSelectionModel().getSelectedItem();
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment modifier ?", "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

        // 0=yes, 1=no, 2=cancel
        if (input == 0) {
            HiddenPane.setVisible(true);
            roleTxt.setText(user.getRole());
    }


    }

    @FXML
    private void ConfirmModifRole(ActionEvent event) {
        cnx = MaConnexion.getInstance().getCnx();
        utilisateurService us = new utilisateurService();
        user = tableUsers.getSelectionModel().getSelectedItem();
        us.ModifierUtilisateurByRole(user.getCinUser(),roleTxt.getText());
        JOptionPane.showMessageDialog(null, "Utilisateur est mise a jour avec succées!!");
        loadData();
        HiddenPane.setVisible(false);
    }

    @FXML
    private void AjouterUser(ActionEvent event) {
        //INTERFACE OKHRAA ....
    }
}