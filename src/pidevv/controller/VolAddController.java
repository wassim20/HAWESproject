package pidevv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pidevv.entity.PickUp;
import pidevv.entity.Vol;
import pidevv.service.PickUpService;
import pidevv.service.VolService;

public class VolAddController implements Initializable {

    /**
     * Initializes the controller class.
     */
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
                    places.setText(newValue.replaceAll("[^\\d]", ""));
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
    private Button btnAddVol;

    @FXML
    private Button btnBack;

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
    void btnAddVol(ActionEvent event) throws IOException {
if(!avion.getText().equals("") && !destination.getText().equals("") && !compagnie.getText().equals("") && !hdH.getValue().equals("") && !haH.getValue().equals("")&& !haH.getValue().equals("") && !hdM.getValue().equals("") ) {

    Vol vol = new Vol(0, compagnie.getText(), destination.getText(), java.sql.Date.valueOf( dateD.getValue() ), hdH.getValue()+":"+hdM.getValue(), haH.getValue()+":"+haM.getValue(), avion.getText(), Integer.parseInt(places.getText()), Float.parseFloat(prix.getText()));
        VolService vs = new VolService();
        vs.add(vol);

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

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/vol.fxml"));
                ap.getChildren().setAll(pane);
    }



}
