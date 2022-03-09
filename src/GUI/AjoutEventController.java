/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import Services.EvenementService;
import entities.evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.DateCell;
import javax.swing.JOptionPane;
import Services.CategorieeveService;
import tools.MaConnexion;



/**
 * FXML Controller class
 *
 * @author achre
 */
public class AjoutEventController implements Initializable {

    @FXML
    private TextField nomEve;
    @FXML
    private Slider nbplace;
    @FXML
    private DatePicker DD;
    @FXML
    private DatePicker DF;    
    @FXML
    private TextField prixEve;
    @FXML
    private Button btnAjout;  
    @FXML
    private Label myLabel;
    @FXML
    private TextField id_heb;
    @FXML
    private Button btngetback;
    @FXML
    private ComboBox catcombo;
    
    
    evenement e = new evenement ();
    
    final ObservableList options = FXCollections.observableArrayList();
    
    EvenementService es = new EvenementService();
    CategorieeveService cs = new CategorieeveService();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillbox();
        // Initialiser range
        nbplace.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) 
            {		
                int myPlace = (int) nbplace.getValue();
                myLabel.setText(Integer.toString(myPlace) + "Places");
                }     });
        catcombo.setMaxHeight(30);
        catcombo.setItems(options);        
        // Vérification date Arrivé
        LocalDate minDate = LocalDate.now();
        DD.setDayCellFactory(d ->
           new DateCell() {
               @Override public void updateItem(LocalDate item, boolean empty) {
                   super.updateItem(item, empty);
                   setDisable(item.isBefore(minDate));
               }});
        // Disable date Départ
        LocalDate maxDate = minDate.plusDays(1);
        DF.setDayCellFactory(d ->
           new DateCell() {
               @Override public void updateItem(LocalDate item, boolean empty) {
                   super.updateItem(item, empty);
                   setDisable(true);
               }}); 
        
        

        
    }
    
        public void UpdateDF(LocalDate dateD){
        LocalDate maxDate = dateD.plusDays(1);
        DF.setDayCellFactory(d ->
           new DateCell() {
               @Override public void updateItem(LocalDate item, boolean empty) {
                   super.updateItem(item, empty);
                   setDisable(item.isBefore(maxDate));
               }});
    }
        
        

        
            @FXML
    private void getDateArr(ActionEvent event){
        LocalDate dateD = DD.getValue();
        e.setHeureDebut(java.sql.Date.valueOf(dateD));
        UpdateDF(dateD);
    }
        

    @FXML
    private void getDateDep(ActionEvent event){
        LocalDate dateDep = DF.getValue();
        e.setHeureFin(java.sql.Date.valueOf(dateDep));
    }
        

        

        
        
    @FXML
    public void fillbox(){
       
        try {
            Connection cnx;
            cnx =MaConnexion.getInstance().getCnx();
            
            String sql="SELECT nomCat,idCat from `categorieeve` ";
            
                PreparedStatement ste = cnx.prepareStatement(sql);
                ResultSet rs= ste.executeQuery();
                while(rs.next()){
                options.add(rs.getString("nomCat"));
                                       
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(AjoutEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void ajouterEvent(ActionEvent event) { 
         //   String selected = GetItemText(catcombo.SelectedItem.ToString);
         LocalDate dd= DD.getValue();
         LocalDate df= DF.getValue();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
         String formattedString = formatter.format(dd);
         String formattedString1 = formatter.format(df);


        int id=0;
        try {
            id= cs.getCatid();
            if (nomEve.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Le Nom d'evenement est obligatoire");
                
            } else if (prixEve.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Le Prix d'evenement est obligatoire");
                
            } else if (formattedString.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Doit selectionner une Date de debut");
                
            } else if (formattedString1.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Doit selectionner une Date de Fin");
                
            } else if (id_heb.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "L'hebergement d'evenement est obligatoire");
                
            } else if (catcombo.isDisable()) {
                JOptionPane.showMessageDialog(null, "La categorie d'evenement est obligatoire");
                
            } else {
                JOptionPane.showMessageDialog(null, "Evenement ajouté avec succés");
            }
                       
            String nom = nomEve.getText();
            Double nbp = nbplace.getValue();
            LocalDate DateD= DD.getValue();
            LocalDate DateF= DF.getValue();
            String cat =(String) catcombo.getValue();
            String prix = prixEve.getText();
            String idHeb = id_heb.getText();
           


            e.setCategorie(cat);
            e.setHeureDebut(java.sql.Date.valueOf(DateD));
            e.setHeureFin(java.sql.Date.valueOf(DateF));
            e.setId_heb(parseInt(idHeb));
            e.setPrix((float) Double.parseDouble(prix));
            e.setNom(nom);
            int nbPlaces= (int)((double)nbp);
            e.setNb_place(nbPlaces);  

            es.ajouterEvenement(e);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceAdmin.fxml"));        
            Parent root = loader.load();
            InterfaceAdminController ae = loader.getController();       
            btnAjout.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }    
    
    



    @FXML
    private void loadMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceAdmin.fxml"));
            Parent root =loader.load();
            InterfaceAdminController tr = loader.getController();
            btngetback.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }





    
}
