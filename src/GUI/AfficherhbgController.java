/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entities.hebergement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javax.annotation.processing.Filer;
import services.hebergementService;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author GX15
 */
public class AfficherhbgController implements Initializable {

    

    @FXML
    private AnchorPane ap;
    @FXML
    private TableView<hebergement> listhbg;
    @FXML
    private TableColumn<hebergement, Integer> idhbg;
    @FXML
    private TableColumn<hebergement, String> nomhbg;
    @FXML
    private TableColumn<hebergement, String> cityhbg;
    @FXML
    private TableColumn<hebergement, Date> datehbg;
    @FXML
    private TableColumn<hebergement, String> adresshbg;
    @FXML
    private TableColumn<hebergement, String> nom_h;
    @FXML
    private TableColumn<hebergement, Integer> nb_ch;
    @FXML
    private TableColumn<hebergement, Integer> nb_sui;
    @FXML
    private TableColumn<hebergement, Integer> pisc;
    @FXML
    private TableColumn<hebergement, Integer> price;

    
    
    hebergementService ps = new hebergementService();
    @FXML
    private TextField affid;
    @FXML
    private TextField affnom;
    @FXML
    private TextField affcity;
    @FXML
    private TextField affadress;
    @FXML
    private TextField affdate;
    @FXML
    private TextField affnom_h;
    @FXML
    private TextField affnb_ch;
    private TextField affp;
    @FXML
    private TextField affprix;
    @FXML
    private TextField affnb_sui;
    @FXML
    private Button bupdate;
    @FXML
    private Button bdelete;
    @FXML
    private TextField chercher;
    @FXML
    private TableColumn<?, ?> etoile;
    @FXML
    private TextField affet;
    @FXML
    private ComboBox<String> tpiscine;
    ObservableList<String> options = FXCollections.observableArrayList(
            "YES",
            "NO"
    );
    @FXML
    private ImageView displayimg;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tpiscine.setItems(options);
        try {
            displayimg.setImage(new Image(new FileInputStream("C:\\Users\\GX15\\Downloads\\logohawes.PNG")));
        } catch (FileNotFoundException ex) {
           
        }
        TranslateTransition trans = new TranslateTransition();
        trans.setNode(displayimg);
        trans.setByY(50);
        trans.setDuration(Duration.millis(2000));
        trans.setCycleCount(TranslateTransition.INDEFINITE);
        trans.setAutoReverse(true);
        trans.play();
        
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(displayimg);
        rotate.setDuration(Duration.millis(2000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.play();
        
        
        
        
        try {
            AfficherTable();
            
            
            
            
            
            //hberglist=(ObservableList<hebergement>) ps.afficherHebergement1();  
        } catch (SQLException ex) {
          System.out.println(ex.getMessage());
        }
            
           
           
        
    }   
    
          
    
    public static ObservableList<hebergement> getlisthbg() throws SQLException {
        hebergementService ms = new hebergementService();
        ArrayList e=ms.afficherHebergement1();
        ObservableList<hebergement> listhebergement = FXCollections.observableArrayList(e);
        return listhebergement;
    }

    public void AfficherTable() throws SQLException {
        ObservableList<hebergement> list = getlisthbg();
        
        idhbg.setCellValueFactory(new PropertyValueFactory<>("id_hbg"));
            nomhbg.setCellValueFactory(new PropertyValueFactory<>("nom"));
            cityhbg.setCellValueFactory(new PropertyValueFactory<>("city"));
            datehbg.setCellValueFactory(new PropertyValueFactory<>("date_ajout"));
            adresshbg.setCellValueFactory(new PropertyValueFactory<>("adress"));
            nom_h.setCellValueFactory(new PropertyValueFactory<>("nom_hotel"));
            nb_ch.setCellValueFactory(new PropertyValueFactory<>("nb_chambres"));
            nb_sui.setCellValueFactory(new PropertyValueFactory<>("nb_suites"));
            pisc.setCellValueFactory(new PropertyValueFactory<>("piscine"));
            price.setCellValueFactory(new PropertyValueFactory<>("prix"));
            etoile.setCellValueFactory(new PropertyValueFactory<>("etoile"));
            listhbg.setItems(list);

    }
    
    
    @FXML
    private void getSelected(MouseEvent event) {
        int index = listhbg.getSelectionModel().getSelectedIndex();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (index <= -1) {
            return;
        }
        affid.setText(String.valueOf(idhbg.getCellData(index)));
        affnom.setText(String.valueOf(nomhbg.getCellData(index)));
        affcity.setText(String.valueOf(cityhbg.getCellData(index)));
        affdate.setText(String.valueOf(datehbg.getCellData(index)));
        affadress.setText(String.valueOf(adresshbg.getCellData(index)));
        affnom_h.setText(String.valueOf(nom_h.getCellData(index)));
        affnb_ch.setText(String.valueOf(nb_ch.getCellData(index)));
        affnb_sui.setText(String.valueOf(nb_sui.getCellData(index)));
       // affp.setText(String.valueOf(pisc.getCellData(index)));
       if(pisc.getCellData(index)==1)
       tpiscine.setValue("YES");
       else tpiscine.setValue("NO");
           
        affprix.setText(String.valueOf(price.getCellData(index)));
        affet.setText(String.valueOf(etoile.getCellData(index)));
        
        
        //combo.setValue(String.valueOf(id_promo.getCellData(index)));
    }

    @FXML
    private void update(ActionEvent event) throws IOException, ParseException, SQLException {
        
        
        String o="";
        int index = listhbg.getSelectionModel().getSelectedIndex();
        
        if(pisc.getCellData(index)==1)
        {
            o="YES";
        }else o = "NO";
        
        if(affnom.getText().equals(String.valueOf(nomhbg.getCellData(index))) && affcity.getText().equals(String.valueOf(cityhbg.getCellData(index))) && affdate.getText().equals(String.valueOf(datehbg.getCellData(index))) && affadress.getText().equals(String.valueOf(adresshbg.getCellData(index))) && affnom_h.getText().equals(String.valueOf(nom_h.getCellData(index))) && tpiscine.getValue()==o && affnb_ch.getText().equals(String.valueOf(nb_ch.getCellData(index)))&& affnb_sui.getText().equals(String.valueOf(nb_sui.getCellData(index)))  && affprix.getText().equals(String.valueOf(price.getCellData(index))) && affet.getText().equals(String.valueOf(etoile.getCellData(index))) ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            
            // set content text
            alert.setContentText("verifié vos parametre ");
            
            // show the dialog
            alert.show();
        }
else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment modifier");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
           int a= Integer.parseInt(affid.getText());
        String b= affnom.getText();
        String c= affcity.getText();
        String sDate1=affdate.getText();
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
        Date d=formatter1.parse(sDate1);
        String e= affadress.getText();
        String f=affnom_h.getText();
        
        int i =Integer.parseInt(affnb_ch.getText());
        int j=Integer.parseInt(affnb_sui.getText());
        //int k=Integer.parseInt(affp.getText());
        int k = 1;
        if ("NO".equals(tpiscine.getValue())) {
            k = 0;
        }
        int l=Integer.parseInt(affprix.getText());
        int m=Integer.parseInt(affet.getText());
            System.out.println("wselt");
        
            //id_hbg;nom;city;date_ajout;adress;nom_hotel;nb_chambres;nb_suites;piscine;image;prix;
            hebergement heb;
            Connection cnx;
             cnx=MaConnexion.getInstance().getCnx();
            String query1="SELECT image from hebergement where id_hbg="+a+"";
                PreparedStatement ste1;
                ste1 = cnx.prepareStatement(query1);
                ResultSet rs1;
                rs1 = ste1.executeQuery();
                rs1.next();
            heb = new hebergement(a,b, c, d, e,f, i, j, k, rs1.getString("image"), l,m);
            hebergementService pss =new hebergementService();
            pss.updatehebergement(heb, a,m);
            
             AnchorPane pane = FXMLLoader.load(getClass().getResource("Afficherhbg.fxml"));
                ap.getChildren().setAll(pane);
        }
        }
        
        
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException, IOException {
        
        hebergementService ps = new hebergementService();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment supprimer");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            int a= Integer.parseInt(affid.getText());
            
            hebergement heb;
            heb=ps.gethbg(a);
            ps.deletehebergement(heb);
        }

        AnchorPane pane = FXMLLoader.load(getClass().getResource("Afficherhbg.fxml"));
                ap.getChildren().setAll(pane);
        
    }

    @FXML
    private void chercher(ActionEvent event) throws SQLException {
        FilteredList<hebergement> filter = new FilteredList<>(getlisthbg(), e -> true);
        chercher.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(h -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (h.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if(h.getCity().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    } else if(h.getAdress().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    } else if(h.getNom_hotel().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    } else if(String.valueOf(h.getNb_chambres()).contains(lowerCaseFilter)){
                        return true;
                    } else if(String.valueOf(h.getNb_suites()).contains(lowerCaseFilter)){
                        return true;
                    } else if(String.valueOf(h.getPrix()).contains(lowerCaseFilter)){
                        return true;
                    }else 
                        return false;
                });

            });
        listhbg.setItems(filter);
    }


   
    }
    
    
    
    
    
    
    
    
    
    
    
    

    


