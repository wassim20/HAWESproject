package pidevv.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private AnchorPane ap;

    @FXML
    private Button btnPickup;

    @FXML
    private Button btnReservationPickup;

    @FXML
    private Button btnVol;

    @FXML
    void btnPickupClicked(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/pickup.fxml"));
                ap.getChildren().setAll(pane);

    }

    @FXML
    void btnReservationPickupClicked(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/reservationPickup.fxml"));
                ap.getChildren().setAll(pane);

    }

    @FXML
    void btnVolClicked(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/vol.fxml"));
                ap.getChildren().setAll(pane);
    }

}
