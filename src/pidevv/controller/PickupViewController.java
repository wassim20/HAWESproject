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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pidevv.entity.PickUp;
import pidevv.service.PickUpService;

public class PickupViewController  implements Initializable {

    PickUp pickup;
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
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> hdH;

    @FXML
    private ComboBox<String> hdM;

    @FXML
    private TextField prix;

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/pickup.fxml"));
                ap.getChildren().setAll(pane);
    }

    @FXML
    void btnDelete(ActionEvent event) throws IOException {
        PickUpService pus = new PickUpService();
										Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
										alert.setTitle("Confirmation ");
										alert.setHeaderText(null);
										alert.setContentText("Vous voulez vraiment supprimer");
										Optional<ButtonType> action = alert.showAndWait();
									   if (action.get() == ButtonType.OK) {
											pus.delete(pickup);

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/pickup.fxml"));
                ap.getChildren().setAll(pane);
									   }

    }

    @FXML
    void btnUpdate(ActionEvent event) throws IOException {
if(!adresseD.getText().equals("") && !adresseD.getText().equals("") && !hdH.getValue().equals("") && !hdM.getValue().equals("") ) {
        PickUp pickUp = new PickUp(pickup.getId(), 0, adresseD.getText(), adresseA.getText(), hdH.getValue()+":"+hdM.getValue(), Float.parseFloat(prix.getText()));
        PickUpService pus = new PickUpService();
        pus.update(pickUp);

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

    void setPickup(PickUp pickUp) {
        pickup = pickUp;
        adresseA.setText(pickUp.getAdresseArrivee());
        adresseD.setText(pickUp.getAdresseDepart());
        hdH.setValue(pickUp.getHeureDepart().split(":", 2)[0]);
        hdM.setValue(pickUp.getHeureDepart().split(":", 2)[1]);
        prix.setText(((int)pickUp.getPrix())+"");
    }

}
