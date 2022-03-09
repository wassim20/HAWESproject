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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pidevv.entity.PickUp;
import pidevv.service.PickUpService;

public class PickupAddController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        prix.textProperty().addListener(new ChangeListener<String>() {
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
            hdH.getItems().add(item);

        }

        for (int i = 0; i < 60; i++) {
            String item;
            item = i+"";
            if (i < 10) {
                item = "0"+i;
            }
            hdM.getItems().add(item);

        }

    } 

    Alert alert = new Alert(Alert.AlertType.NONE);

    @FXML
    private TextField adresseA;

    @FXML
    private TextField adresseD;

    @FXML
    private AnchorPane ap;

    @FXML
    private Button btnAddVol;

    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<String> hdH;

    @FXML
    private ComboBox<String> hdM;

    @FXML
    private TextField prix;

    @FXML
    void btnAddVol(ActionEvent event) throws IOException {
if(!adresseD.getText().equals("") && !adresseA.getText().equals("") && !hdH.getValue().equals("") && !hdM.getValue().equals("") ) {
        PickUp pickUp = new PickUp(0, 0, adresseD.getText(), adresseA.getText(), hdH.getValue()+":"+hdM.getValue(), Float.parseFloat(prix.getText()));
        PickUpService pus = new PickUpService();
        pus.add(pickUp);

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/pickup.fxml"));
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
    void btnBack(ActionEvent event)throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/pickup.fxml"));
                ap.getChildren().setAll(pane);
    }

}
