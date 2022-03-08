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
import pidevv.service.PickUpService;
import pidevv.service.ReservationPickUpService;
import pidevv.service.VolService;

public class VolController implements Initializable {

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
    private TextField search;

    @FXML
    private Button btnAddVol;

    @FXML
    private TableColumn<Vol, String> tcAvion;

    @FXML
    private TableColumn<Vol, String> tcCompagnie;

    @FXML
    private TableColumn<Vol, String> tcDateDepart;

    @FXML
    private TableColumn<Vol, String> tcDestination;

    @FXML
    private TableColumn<Vol, String> tcHeureArrivee;

    @FXML
    private TableColumn<Vol, String> tcHeureDepart;

    @FXML
    private TableColumn<Vol, String> tcId;

    @FXML
    private TableColumn<Vol, String> tcPlaces;

    @FXML
    private TableColumn<Vol, String> tcPrix;

    @FXML
    private TableView<Vol> tvVol;

    @FXML
    void btnAddVol(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/pidevv/gui/volAdd.fxml"));
                ap.getChildren().setAll(pane);
    }

    @FXML
    void tvDoubleClicked(MouseEvent event)  throws IOException {
if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                Vol vol = tvVol.getSelectionModel().getSelectedItem();
        if (vol != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidevv/gui/volView.fxml"));              
                    Parent parent = loader.load();
                    ap.getChildren().setAll(parent);

                       VolViewController controller = (VolViewController) loader.getController();
                        controller.setVol(vol);
        }
            }
        }
        
    }

    @FXML
    void search(InputMethodEvent event) {
    }

@FXML
    void search__(KeyEvent event) {
VolService vs = new VolService();

                    tvVol.setItems((ObservableList<Vol>) vs.search(search.getText()));

    }
private void read()
	{
		          VolService vs = new VolService();

                    tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
                    tcAvion.setCellValueFactory(new PropertyValueFactory<>("avion"));
                    tcCompagnie.setCellValueFactory(new PropertyValueFactory<>("compagnie"));
                    tcDateDepart.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
                    tcDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
                    tcHeureArrivee.setCellValueFactory(new PropertyValueFactory<>("heureArrivee"));
                    tcHeureDepart.setCellValueFactory(new PropertyValueFactory<>("heureDepart"));
                    tcPlaces.setCellValueFactory(new PropertyValueFactory<>("places"));
                    tcPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
                    tvVol.setItems((ObservableList<Vol>) vs.getAll());
	}

}
