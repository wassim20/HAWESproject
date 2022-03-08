package pidevv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import pidevv.entity.PickUp;
import pidevv.entity.ReservationPickUp;
import pidevv.entity.Vol;
import pidevv.service.ReservationPickUpService;
import pidevv.service.VolService;

public class ReservationPickupController  implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        read();
    }
    @FXML
    private AnchorPane ap;

    @FXML
    private Button btnReservation;

    @FXML
    private TextField search;

    @FXML
    private TableColumn<ReservationPickUp, String> tcDate;

    @FXML
    private TableColumn<ReservationPickUp, String> tcEtat;

    @FXML
    private TableColumn<ReservationPickUp, String> tcId;

    @FXML
    private TableColumn<ReservationPickUp, PickUp> tcPickup;

    @FXML
    private TableColumn<ReservationPickUp, String> tcUser;

    @FXML
    private TableView<ReservationPickUp> tvVol;

    @FXML
    void btnReservation(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/reservationPickupAdd.fxml"));
                ap.getChildren().setAll(pane);
    }

    @FXML
    void search(InputMethodEvent event) {
    }
@FXML
    void search__(KeyEvent event) {
        ReservationPickUpService rpus = new ReservationPickUpService();
        tvVol.setItems((ObservableList<ReservationPickUp>) rpus.search(search.getText()));

    }

    @FXML
    void tvDoubleClicked(MouseEvent event)  throws IOException {
if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                ReservationPickUp rpu = tvVol.getSelectionModel().getSelectedItem();
        if (rpu != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidevv/gui/reservationPickupView.fxml"));              
                    Parent parent = loader.load();
                    ap.getChildren().setAll(parent);

                       ReservationPickupViewController controller = (ReservationPickupViewController) loader.getController();
                        controller.setReservationPickup(rpu);
        }
            }
        }
        
    }

private void read()
	{
        ReservationPickUpService rpus = new ReservationPickUpService();

                    tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
                    tcUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
                    tcPickup.setCellValueFactory(new PropertyValueFactory<>("pickUp"));
                    tcEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
                    tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                    tvVol.setItems((ObservableList<ReservationPickUp>) rpus.getAll());
	}

}
