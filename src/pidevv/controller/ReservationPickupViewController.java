package pidevv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import pidevv.entity.PickUp;
import pidevv.entity.ReservationPickUp;
import pidevv.service.PickUpService;
import pidevv.service.ReservationPickUpService;
import pidevv.service.VolService;

public class ReservationPickupViewController  implements Initializable {

    /**
     * Initializes the controller class.
     */
    ReservationPickUp reservationPickUp;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
PickUpService pus = new PickUpService();
       List<PickUp> listPickUps = pus.getAllList();
        for (PickUp pickUp : listPickUps) {
            pickup.getItems().add(pickUp);
        }
    }  
  
    @FXML
    private AnchorPane ap;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox<PickUp> pickup;
    Alert alert = new Alert(Alert.AlertType.NONE);


    @FXML
    void btnBack(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/reservationPickup.fxml"));
                ap.getChildren().setAll(pane);
    }

    @FXML
    void btnDelete(ActionEvent event) throws IOException {
        ReservationPickUpService rpus = new ReservationPickUpService();
										Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
										alert.setTitle("Confirmation ");
										alert.setHeaderText(null);
										alert.setContentText("Vous voulez vraiment supprimer");
										Optional<ButtonType> action = alert.showAndWait();
									   if (action.get() == ButtonType.OK) {
											rpus.delete(reservationPickUp);

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/reservationPickup.fxml"));
                ap.getChildren().setAll(pane);
									   }
    }
    

    @FXML
    void btnUpdate(ActionEvent event) throws IOException {
if(!date.getValue().toString().equals("") ) {
        ReservationPickUp rpu = new ReservationPickUp(reservationPickUp.getId(), pickup.getValue(), 0, java.sql.Date.valueOf( date.getValue() ), 1);
        ReservationPickUpService rpus = new ReservationPickUpService();
        rpus.update(rpu);

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/reservationPickup.fxml"));
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

    void setReservationPickup(ReservationPickUp rpu) {
        reservationPickUp = rpu;
        pickup.setValue(rpu.getPickUp());
    }

}
