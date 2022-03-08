/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import entities.categorieeve;
import entities.evenement;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import services.EvenementService;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author achre
 */
public class ModifEventController implements Initializable {

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
    private Label myLabel;
    @FXML
    private TextField id_heb;
     final ObservableList options = FXCollections.observableArrayList();
    
    evenement evenement = new evenement();
    evenement e = new evenement();
    EvenementService es = new EvenementService ();
    @FXML
    private Button btngetback;
    @FXML
    private ComboBox catcombo;
    @FXML
    private Button btnModif;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillbox();
        
                nbplace.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) 
                     {
				
                int myPlace = (int) nbplace.getValue();
                myLabel.setText(Integer.toString(myPlace) + "Places");

				
			}
        });
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


    int id;
    
    public void prepare(evenement e){
        id = e.getIdEvent();
        nomEve.setText(String.valueOf(e.getNom()));        
        DD.setValue(e.getHeureDebut().toLocalDate());
        DF.setValue(e.getHeureFin().toLocalDate());     
        prixEve.setText(String.valueOf(e.getPrix()));
        nbplace.setValue((e.getNb_place()));
        catcombo.setValue(String.valueOf(e.getIdCat()));
        id_heb.setText(String.valueOf(e.getId_heb()));
        evenement = e;
    }
    @FXML
        
    private void modifEvent(ActionEvent event) {
        
                 LocalDate dd= DD.getValue();
         LocalDate df= DF.getValue();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
         String formattedString = formatter.format(dd);
         String formattedString1 = formatter.format(df);
         
         
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
            String cat = (String) catcombo.getValue();
            String prix = prixEve.getText();
            String idHeb = id_heb.getText();
           
            categorieeve c = new categorieeve ();
            
            evenement e = new evenement ();
            e.setCategorie(cat);
            e.setHeureDebut(java.sql.Date.valueOf(DateD));
            e.setHeureFin(java.sql.Date.valueOf(DateF));
            e.setId_heb(parseInt(idHeb));
            e.setPrix((float) Double.parseDouble(prix));
            e.setNom(nom);
            int nbPlaces= (int)((double)nbp);
            e.setNb_place(nbPlaces);  

        evenement = e;
            es.modifierEvenement(e , id);

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
