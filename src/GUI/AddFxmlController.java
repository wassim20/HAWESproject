package GUI;

import entities.hebergement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.FileChooser;
import services.hebergementService;
import tools.MaConnexion;

public class AddFxmlController implements Initializable {

    ObservableList<String> tpiscine1 = FXCollections.observableArrayList("YES", "NO");
    List<String> L;
    
    String d = null;

    
    @FXML
    private AnchorPane ap;
    @FXML
    private Button add;
    @FXML
    private TextField tnom;
    @FXML
    private TextField tcity;
    @FXML
    private DatePicker tdate_ajout;
    @FXML
    private TextField tadress;
    @FXML
    private TextField tnom_hotel;
    @FXML
    private TextField tprix;
    @FXML
    private TextField tnb_suites;
    @FXML
    private TextField tnb_chambres;
    @FXML
    private Button tchoisir;
    @FXML
    private ComboBox<String> tpiscine;
    ObservableList<String> options = FXCollections.observableArrayList(
            "YES",
            "NO"
    );
    @FXML
    private TextField tetoile;
    @FXML
    private Button aff;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //tpiscine.setValue();
        //tpiscine.setItems(tpiscine1);
        tpiscine.setItems(options);
        L = new ArrayList<>();
        L.add("*.jpg");
        L.add("*.jpeg");
        L.add("*.png");
    }
    
    @FXML
    private String filechoix(ActionEvent event) {

       FileChooser fC = new FileChooser();

        fC.getExtensionFilters().add(new FileChooser.ExtensionFilter("image :", L));
        File F = fC.showOpenDialog(null);

        if (F != null) {
            d = F.getAbsolutePath();

        }
        return d;

    }

    @FXML
    private void addhbg(ActionEvent event) throws ParseException, SQLException {
 
        
        String n = tnom.getText();
        String city = tcity.getText();

        String adress = tadress.getText();

        String datee = tdate_ajout.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
         Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(datee); 

        
        
        String nom_hotel = tnom_hotel.getText();
        String s = tprix.getText();
        String e = tetoile.getText();
        String s1 = tnb_chambres.getText();
        String s2 = tnb_suites.getText();
        int p = 1;
        if ("NO".equals(tpiscine.getValue())) {
            p = 0;
        }
        int prix = Integer.parseInt(s);
        int etoile =Integer.parseInt(e);
        int nb_chambres = Integer.parseInt(s1);
        int nb_suites = Integer.parseInt(s2);
        //id_hbg;nom;city;date_ajout;adress;nom_hotel;nb_chambres;nb_suites;piscine;image;prix;
        String query = "select * from hebergement";
        int adder=0;
        Connection cnx;
        cnx=MaConnexion.getInstance().getCnx();
        PreparedStatement ste;
            ste = cnx.prepareStatement(query);
            ResultSet rs;
            rs = ste.executeQuery();
            while (rs.next()) {
                
            
            ++adder;
                
                 
            }
            rs.last();
            if(adder<rs.getInt("id_hbg")){
                adder=rs.getInt("id_hbg")+1;
            }else if(adder==rs.getInt("id_hbg")) {
                adder=adder+1;
                
            }
            
            String choi=d;
            
        hebergement h = new hebergement(adder, n, city, date1, adress, nom_hotel, nb_chambres, nb_suites, p,d/*choi=filechoix(event)*/,prix);

        MaConnexion mc = MaConnexion.getInstance();
        hebergementService ps = new hebergementService();
        
        
        if(ps.ajouterhebergement(h,etoile)==1){
            
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.show();
                        alert.setTitle("ADDED !");
                        alert.setContentText("ADDED succesfully");
                        
            
        }else {
             Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                        alert.setTitle("fail !");
                        alert.setContentText("failed");
        }
        
        System.out.println(h);
        
        
        
        
        
    }

    

   
    @FXML
    private void afficher(ActionEvent event) throws IOException {
       
       
         AnchorPane pane = FXMLLoader.load(getClass().getResource("Afficherhbg.fxml"));
                ap.getChildren().setAll(pane);
       
    }

    
        
        
       
    


}

