/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Paiement;
import entities.Reservation;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import services.PaiementService;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class StatisticsFXMLController implements Initializable {

    @FXML
    private ListView<String> txtlistStat;
    @FXML
    private Button btncharger;
    @FXML
    private Button btngetback;
    @FXML
    private RadioButton rdorc;
    @FXML
    private RadioButton rdord;
    ToggleGroup rden = new ToggleGroup();
    ToggleGroup rdor = new ToggleGroup();
    @FXML
    private TextField txtattmoy = null;
    @FXML
    private TextField txtattsom = null;
    @FXML
    private TextField txtattnb = null;
    @FXML
    private TextField txtattgrb = null;
    @FXML
    private TextField txtattidh = null;
    private static int lines,columns; 
    Reservation r = new Reservation();
    Paiement p = new Paiement(); 
    PaiementService ps = new PaiementService();
    ReservationService rs = new ReservationService();
    private int order = 1;
    private int idHebr = 0;
    @FXML
    private AnchorPane anchor2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rdorc.setToggleGroup(rdor);
        rdord.setToggleGroup(rdor);
        rdorc.setSelected(true);
    }    

    public void setRAC(int l, int c){
        this.columns = c;
        this.lines = l;
    }
    
    @FXML
    private void chargerStat(ActionEvent event) {
        String[][] ts;
        ObservableList<String> items =FXCollections.observableArrayList();
        List<String> stats = new ArrayList<String>();
        List<Reservation> listReservation;
        List<Paiement> listPaiement;
        String moyenne=txtattmoy.getText();
        String occurrence=txtattnb.getText();
        String somme=txtattsom.getText();
        String groupby=txtattgrb.getText();
        String sidh=txtattidh.getText();
        if(sidh.length() != 0){
            idHebr = parseInt(sidh);
        }
            System.out.println(somme);
            ts = rs.showStatistics(moyenne,occurrence,somme,groupby,idHebr,order);
            int i,j;
            String ch = "";
            for ( j = 0; j < lines; j++) {
                ch="";
                for ( i = 0; i < columns; i++) {
                    ch = ch+" "+ts[i][j];
		}
                stats.add(ch);
		System.out.println();
            }
            System.out.println(stats);
            items.addAll(stats);
            txtlistStat.setItems(items);
            
    }

    @FXML
    private void loadMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuReservationPaiement.fxml"));
            Parent root =loader.load();
            MenuReservationPaiementController mr = loader.getController();
            btngetback.getScene().setRoot(root);
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void setOrderByASC(ActionEvent event) {
        this.order = 1;
    }

    @FXML
    private void setOrderByDESC(ActionEvent event) {
        this.order = 0;
    }
    
}
