/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.hebergement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import services.hebergementService;

/**
 * FXML Controller class
 *
 * @author GX15
 */
public class FronthbgController implements Initializable {

    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img6;
    @FXML
    private ImageView img7;
    @FXML
    private ImageView img8;
    @FXML
    private ImageView img9;

    hebergementService ps = new hebergementService();
    @FXML
    private AnchorPane ap;
    int i=0;
    hebergement h = null;
    @FXML
    private TextField type1;
    @FXML
    private TextField city1;
    @FXML
    private TextField adress1;
    @FXML
    private TextField nomh1;
    @FXML
    private TextField pisc1;
    @FXML
    private TextField prix1;
    @FXML
    private TextField type2;
    @FXML
    private TextField city2;
    @FXML
    private TextField adress2;
    @FXML
    private TextField nomh2;
    @FXML
    private TextField pisc2;
    @FXML
    private TextField prix2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
        for (int i =1; i < 50; i++){
            
            
            
            
            h=ps.getfullHebergement(i);
           // System.out.println("ena houni "+h);
            if(h.getId_hbg()!=NULL){
        
                
           try {String s=h.getImage();
            System.out.println("aaa"+s);
           // boolean check=false;
           
           if(img1.getImage()==null){
                img1.setImage(new Image(new FileInputStream(s)));
                type1.setText(h.getNom());
                city1.setText(h.getCity());
                adress1.setText(h.getAdress());
                nomh1.setText(h.getNom_hotel());
                if(h.getEtoile()==1){pisc1.setText("YES");}
                else{pisc1.setText("NO");}
                prix1.setText(String.valueOf(h.getPrix()));
           
           }
           else if(img2.getImage()==null){
                img2.setImage(new Image(new FileInputStream(s)));}
          else if(img3.getImage()==null){
                img3.setImage(new Image(new FileInputStream(s)));}
             else if(img4.getImage()==null){
                img4.setImage(new Image(new FileInputStream(s)));}
            else if(img5.getImage()==null){
                img5.setImage(new Image(new FileInputStream(s)));}
             else if(img6.getImage()==null){
                img6.setImage(new Image(new FileInputStream(s)));}
             else if(img7.getImage()==null){
                img7.setImage(new Image(new FileInputStream(s)));}
             else if(img8.getImage()==null){
                img8.setImage(new Image(new FileInputStream(s)));}
             else if(img9.getImage()==null){
                img9.setImage(new Image(new FileInputStream(s)));}
            
            
            } catch (FileNotFoundException ex) {
            }
            }
    }
    }


public static ObservableList<hebergement> getlisthbg() throws SQLException {
        hebergementService ms = new hebergementService();
        ArrayList e=ms.afficherHebergement1();
        ObservableList<hebergement> listhebergement = FXCollections.observableArrayList(e);
        return listhebergement;
    }    

    @FXML
    private void popupss(MouseEvent event) throws IOException {
        
        /*Popup pop = PopupBuilder.create().content(contentNode).width(50).height(100).autoFix(true).build();
pop.show(stage);*/
  Parent root = FXMLLoader.load(getClass().getResource("Afficherhbg.fxml"));
  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  Scene scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    
    
}
