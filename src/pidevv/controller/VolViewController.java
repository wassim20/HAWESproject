package pidevv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pidevv.entity.PickUp;
import pidevv.entity.Vol;
import pidevv.service.PickUpService;
import pidevv.service.VolService;

public class VolViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Vol vol;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prix.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    prix.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        places.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    prix.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        for (int i = 0; i < 24; i++) {
            String item;
            item = i+"";
            if (i < 10) {
                item = "0"+i;
            }
            haH.getItems().add(item);
            hdH.getItems().add(item);

        }

        for (int i = 0; i < 60; i++) {
            String item;
            item = i+"";
            if (i < 10) {
                item = "0"+i;
            }
            haM.getItems().add(item);
            hdM.getItems().add(item);

        }

    }

    @FXML
    private AnchorPane ap;

    @FXML
    private TextField avion;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDeleteVol;

    @FXML
    private Button btnUpdateVol;

    @FXML
    private TextField compagnie;

    @FXML
    private DatePicker dateD;

    @FXML
    private TextField destination;

    @FXML
    private ComboBox<String> haH;

    @FXML
    private ComboBox<String> haM;

    @FXML
    private ComboBox<String> hdH;

    @FXML
    private ComboBox<String> hdM;

    @FXML
    private TextField places;

    @FXML
    private TextField prix;

    Alert alert = new Alert(Alert.AlertType.NONE);


    @FXML
    void btnBack(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/vol.fxml"));
                ap.getChildren().setAll(pane);
    }

    @FXML
    void btnDeleteVol(ActionEvent event) throws IOException {
        VolService pus = new VolService();
										Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
										alert.setTitle("Confirmation ");
										alert.setHeaderText(null);
										alert.setContentText("Vous voulez vraiment supprimer");
										Optional<ButtonType> action = alert.showAndWait();
									   if (action.get() == ButtonType.OK) {
											pus.delete(vol);

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/vol.fxml"));
                ap.getChildren().setAll(pane);
									   }
    }

    @FXML
    void btnUpdateVol(ActionEvent event) throws IOException {

if(!avion.getText().equals("") && !destination.getText().equals("") && !compagnie.getText().equals("") && !hdH.getValue().equals("") && !haH.getValue().equals("")&& !haH.getValue().equals("") && !hdM.getValue().equals("") ) {
    Vol voll = new Vol(vol.getId(), compagnie.getText(), destination.getText(), java.sql.Date.valueOf( dateD.getValue() ), hdH.getValue()+":"+hdM.getValue(), haH.getValue()+":"+haM.getValue(), avion.getText(), Integer.parseInt(places.getText()), Float.parseFloat(prix.getText()));
        VolService pus = new VolService();
        pus.update(vol);

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/vol.fxml"));
                ap.getChildren().setAll(pane);
}
else {
alert.setAlertType(Alert.AlertType.WARNING);
           
           // set content text
           alert.setContentText("verifié vos parametre ");
           
           // show the dialog
           alert.show();
}
    }

void setVol(Vol voll) {
        vol = voll;
        avion.setText(vol.getAvion());
        destination.setText(vol.getDestination()+"");
        compagnie.setText(vol.getDestination()+"");
        places.setText(vol.getPlaces()+"");
        hdH.setValue(vol.getHeureDepart().split(":", 2)[0]);
        hdM.setValue(vol.getHeureDepart().split(":", 2)[1]);
        haH.setValue(vol.getHeureDepart().split(":", 2)[0]);
        haM.setValue(vol.getHeureDepart().split(":", 2)[1]);

        prix.setText(vol.getPrix()+"");
    }


}
