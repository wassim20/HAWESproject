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
import pidevv.service.PickUpService;

public class PickupController implements Initializable {

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
    private Button btnAddPickup;

    @FXML
    private TextField search;

    @FXML
    private TableColumn<PickUp, String> tcAdresseArrivee;

    @FXML
    private TableColumn<PickUp, String> tcAdresseDepart;

    @FXML
    private TableColumn<PickUp, String> tcHeureDepart;

    @FXML
    private TableColumn<PickUp, String> tcId;

    @FXML
    private TableColumn<PickUp, String> tcPrix;

    @FXML
    private TableColumn<PickUp, String> tcUser;

    @FXML
    private TableView<PickUp> tvVol;

    @FXML
    void btnAddPickup(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/pickupAdd.fxml"));
                ap.getChildren().setAll(pane);
    }

    @FXML
    void search(InputMethodEvent event) {

    }
    @FXML
    void searchPressed(KeyEvent event) {
        PickUpService pus = new PickUpService();
         tvVol.setItems((ObservableList<PickUp>) pus.search(search.getText()));

    }


    private void read()
	{
		          PickUpService pus = new PickUpService();

                    tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
                    tcUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
                    tcAdresseArrivee.setCellValueFactory(new PropertyValueFactory<>("adresseArrivee"));
                    tcAdresseDepart.setCellValueFactory(new PropertyValueFactory<>("adresseDepart"));
					tcHeureDepart.setCellValueFactory(new PropertyValueFactory<>("heureDepart"));
					tcPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
                    tvVol.setItems((ObservableList<PickUp>) pus.getAll());
	}

    @FXML
    void tvDoubleClicked(MouseEvent event) throws IOException {
if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                PickUp pickUp = tvVol.getSelectionModel().getSelectedItem();
        if (pickUp != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidevv/gui/pickupView.fxml"));              
                    Parent parent = loader.load();
                    ap.getChildren().setAll(parent);

                       PickupViewController controller = (PickupViewController) loader.getController();
                        controller.setPickup(pickUp);
        }
            }
        }
        
    }

}
