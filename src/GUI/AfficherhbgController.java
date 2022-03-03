/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entities.hebergement;
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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private TextField affp;
    @FXML
    private TextField affprix;
    @FXML
    private TextField affnb_sui;
    @FXML
    private Button bupdate;
    @FXML
    private Button bdelete;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        try {
            AfficherTable();
            
            
            
            
            
            //hberglist=(ObservableList<hebergement>) ps.afficherHebergement1();  
        } catch (SQLException ex) {
          System.out.println(ex.getMessage());
        }
            
           
           
        
    }   
    
          /*  public static ObservableMap<hebergement,Integer> gethbg() throws ClassNotFoundException,SQLException{
        hebergementService ps = new hebergementService();
                
                    ResultSet rs = (ResultSet) ps.afficherHebergement();
                    ObservableMap<hebergement,Integer> hbglist=gethbgObject(rs);
                    return hbglist;
                    
                
        
            
            }
            private static ObservableMap<hebergement, Integer> gethbgObject(ResultSet rs) throws SQLException,ClassNotFoundException {
           Connection cnx ;
           cnx=MaConnexion.getInstance().getCnx();
            
                ObservableMap<hebergement,Integer> hbglist = FXCollections.observableMap((Map<hebergement,Integer>)rs);
                
                while(rs.next()){
                    hebergement h=new hebergement();
                    h.setId_hbg(rs.getInt("id_hbg"));
                h.setNom(rs.getString("nom"));
                h.setCity(rs.getString("city"));
                h.setDate_ajout(rs.getDate("date_ajout"));
                h.setAdress(rs.getString("adress"));
                
                h.setNom_hotel(rs.getString("nom_hotel"));
                
                h.setNb_chambres(rs.getInt("nb_chambres"));
                h.setNb_suites(rs.getInt("nb_suites"));
                h.setPiscine(rs.getInt("piscine"));
                h.setImage(rs.getString("image"));
                h.setPrix(rs.getInt("prix"));
                
                String query1="select * from categorie WHERE id_hbg="+rs.getInt("id_hbg")+"";
                PreparedStatement ste1;
                ste1 = cnx.prepareStatement(query1);
                ResultSet rs1;
                rs1 = ste1.executeQuery();
                rs1.next();

                hbglist.put(h, rs1.getInt("etoile"));
                    
                }
            
            return hbglist;
            }

    private void showtable(ObservableMap<hebergement, Integer> hbglist1) {

        listhbg.setItems((ObservableList<hebergement>) (ObservableMap<hebergement,Integer>) hbglist1);
        
    }

    private void loaddata() {
        ArrayList e=new ArrayList();
        e=ps.afficherHebergement1();
        ObservableList<hebergement> hberglist = FXCollections.observableList(e);


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
            
            
     listhbg.setItems(hberglist);
    
            //hberglist =  (ObservableList<hebergement>) ps.afficherHebergement1();
            
            */
    
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
        affp.setText(String.valueOf(pisc.getCellData(index)));
        affprix.setText(String.valueOf(price.getCellData(index)));
        
        
        //combo.setValue(String.valueOf(id_promo.getCellData(index)));
    }

    @FXML
    private void update(ActionEvent event) throws IOException, ParseException, SQLException {
        int index = listhbg.getSelectionModel().getSelectedIndex();
        
        if(affnom.getText().equals(String.valueOf(nomhbg.getCellData(index))) && affcity.getText().equals(String.valueOf(cityhbg.getCellData(index))) && affdate.getText().equals(String.valueOf(datehbg.getCellData(index))) && affadress.getText().equals(String.valueOf(adresshbg.getCellData(index))) && affnom_h.getText().equals(String.valueOf(nom_h.getCellData(index))) && affnb_ch.getText().equals(String.valueOf(nb_ch.getCellData(index)))&& affnb_sui.getText().equals(String.valueOf(nb_sui.getCellData(index)))&& affp.getText().equals(String.valueOf(pisc.getCellData(index))) && affprix.getText().equals(String.valueOf(price.getCellData(index))) ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            
            // set content text
            alert.setContentText("verifié vos parametre ");
            
            // show the dialog
            alert.show();
        }
else {
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
        int k=Integer.parseInt(affp.getText());
        int l=Integer.parseInt(affprix.getText());
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
            heb = new hebergement(a,b, c, d, e,f, i, j, k, rs1.getString("image"), l);
            hebergementService pss =new hebergementService();
            pss.updatehebergement(heb, a,3);
            
             AnchorPane pane = FXMLLoader.load(getClass().getResource("/hawes/GUI/Afficherhbg.fxml"));
                ap.getChildren().setAll(pane);
        }
        
        
    }

    @FXML
    private void delete(ActionEvent event) {
    }
            
    }
    
    
    
    
    
    
    
    
    
    
    
    

    


