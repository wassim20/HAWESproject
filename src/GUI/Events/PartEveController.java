/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Events;

import Service.EvenementService;
import Service.ParticipantsService;
import entities.evenement;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author achre
 */
public class PartEveController implements Initializable {

    @FXML
    private VBox listevent;
    @FXML
    private Label idevent;
    @FXML
    private Label idheb;
    @FXML
    private Label nomevent;
    @FXML
    private Label nbplace;
    @FXML
    private Label datedebut;
    @FXML
    private Label datefin;
    @FXML
    private Label categorie;
    @FXML
    private Label prix;
    @FXML
    private Button btnsupp;
    @FXML
    private Button retour;

    ParticipantsService ps = new ParticipantsService();
    EvenementService es = new EvenementService();
    List<evenement> evenements = new ArrayList();
    evenement et = new evenement();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        evenements = es.afficherEvenement();

        displayEvents();
    }

    private void displayEvents() {

        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

        listevent.getChildren().clear();
        for (int i = 0; i < evenements.size(); i++) {
            evenement e = evenements.get(i);
            Pane userPane = new Pane();
            userPane.setBackground(Background.EMPTY);
            userPane.setPrefHeight(150.0);
            userPane.setPrefWidth(788.0);
            userPane.setLayoutX(0);
            userPane.setLayoutY(0);

            Label ide = new Label();
            ide.setLayoutY(20);
            ide.setTextFill(Color.BLACK);
            String id = Integer.toString(e.getIdEvent());
            ide.setText(id);

            Label idh = new Label();
            idh.setLayoutX(100);
            idh.setLayoutY(20);
            idh.setTextFill(Color.BLACK);
            String idhe = Integer.toString(e.getId_heb());
            idh.setText(idhe);

            Label nom = new Label();
            nom.setLayoutX(204);
            nom.setLayoutY(20);
            nom.setTextFill(Color.BLACK);
            nom.setText(e.getNom());

            Label nbp = new Label();
            nbp.setLayoutX(309);
            nbp.setLayoutY(20);
            nbp.setTextFill(Color.BLACK);
            String nb = Integer.toString(e.getNb_place());
            nbp.setText(nb);

            Label dd = new Label();
            Date date = e.getHeureDebut();
            String dated = dateFormat.format(date);
            dd.setLayoutX(410);
            dd.setLayoutY(20);
            dd.setTextFill(Color.BLACK);
            dd.setText(dated);

            Label df = new Label();
            Date date1 = e.getHeureFin();
            String datef = dateFormat.format(date1);
            df.setLayoutX(500);
            df.setLayoutY(20);
            df.setTextFill(Color.BLACK);
            df.setText(datef);

            Label cat = new Label();
            cat.setLayoutX(595);
            cat.setLayoutY(20);
            cat.setTextFill(Color.BLACK);
            cat.setText(e.getIdCat());

            Label prx = new Label();
            prx.setLayoutX(657);
            prx.setLayoutY(20);
            prx.setTextFill(Color.BLACK);
            String px = Float.toString(e.getPrix());
            prx.setText(px);

            Button deleteBtn = new Button();
            deleteBtn.setPrefHeight(25);
            deleteBtn.setPrefWidth(74);
            deleteBtn.setLayoutX(714);
            deleteBtn.setLayoutY(5);
            deleteBtn.setText("Annuler");

            userPane.getChildren().add(ide);
            userPane.getChildren().add(idh);
            userPane.getChildren().add(nom);
            userPane.getChildren().add(nbp);
            userPane.getChildren().add(dd);
            userPane.getChildren().add(df);
            userPane.getChildren().add(cat);
            userPane.getChildren().add(prx);
            userPane.getChildren().add(deleteBtn);

            deleteBtn.setOnMouseClicked((MouseEvent event) -> {
                int input = JOptionPane.showConfirmDialog(null, "Etes-vous sure d'annuler la participation ?",
                        "Confirmer la supression",
                        JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

                if (input == 0) {
                    String sidevent = ide.getText();
                    int idevent = parseInt(sidevent);
                    es.supprimerEvenement(idevent);
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("PartEve.fxml"));
                        Parent root = loader.load();
                        PartEveController tr = loader.getController();
                        deleteBtn.getScene().setRoot(root);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                }
            });

            listevent.getChildren().add(userPane);
        }

    }

    @FXML
    private void loadMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceClient.fxml"));
            Parent root = loader.load();
            InterfaceClientController tr = loader.getController();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
