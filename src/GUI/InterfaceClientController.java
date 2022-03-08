/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import entities.evenement;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author achre
 */
public class InterfaceClientController implements Initializable {

    @FXML
    private TextField cherche;
    @FXML
    private VBox listevent;
    @FXML
    private Label idevent;
    @FXML
    private Label idheb;
    @FXML
    private Label nomevent;
    @FXML
    private Label nbplace;
    @FXML
    private Label datedebut;
    @FXML
    private Label datefin;
    @FXML
    private Label categorie;
    @FXML
    private Label prix;
    @FXML
    private Button retour;
    EvenementService es = new EvenementService();
    List<evenement> evenements =new ArrayList();
    evenement et = new evenement();
    @FXML
    private RadioButton part;
    @FXML
    private RadioButton part1;
    
    ToggleGroup r = new ToggleGroup();
    @FXML
    private ImageView imageqr;
    /**            
     * 
     *
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        evenements = es.afficherEvenement(); 
        try {
            qrcode();
        } catch (WriterException ex) {
            Logger.getLogger(InterfaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        displayEvents();
    }    
    
    
    
    
            private void displayEvents() {
            
             
            DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");  


            
            
            
            
            
        listevent.getChildren().clear();
        for (int i = 0; i < evenements.size(); i++) {
            evenement e = evenements.get(i);
            Pane userPane = new Pane();
            userPane.setBackground(Background.EMPTY);
            userPane.setPrefHeight(150.0);
            userPane.setPrefWidth(788.0);
            userPane.setLayoutX(0);
            userPane.setLayoutY(0);


            Label ide = new Label();
            ide.setLayoutY(20);
            ide.setTextFill(Color.BLACK);
            String id =Integer.toString(e.getIdEvent()) ;
            ide.setText(id);

            Label idh = new Label();
            idh.setLayoutX(100);
            idh.setLayoutY(20);
            idh.setTextFill(Color.BLACK);
            String idhe =Integer.toString(e.getId_heb()) ;
            idh.setText(idhe);

            Label nom = new Label();
            nom.setLayoutX(204);
            nom.setLayoutY(20);
            nom.setTextFill(Color.BLACK);
            nom.setText(e.getNom());

            Label nbp = new Label();
            nbp.setLayoutX(309);
            nbp.setLayoutY(20);
            nbp.setTextFill(Color.BLACK);
            String nb =Integer.toString(e.getNb_place());
            nbp.setText(nb);

            Label dd = new Label();
            Date date = e.getHeureDebut();
            String dated = dateFormat.format(date);
            dd.setLayoutX(410);
            dd.setLayoutY(20);
            dd.setTextFill(Color.BLACK);
            dd.setText(dated);

            Label df = new Label();
            Date date1 = e.getHeureFin();
            String datef = dateFormat.format(date1);
            df.setLayoutX(500);
            df.setLayoutY(20);
            df.setTextFill(Color.BLACK);
            df.setText(datef);  
            
            Label cat = new Label();
            cat.setLayoutX(595);
            cat.setLayoutY(20);
            cat.setTextFill(Color.BLACK);
            cat.setText(e.getIdCat()); 
            
            Label prx = new Label();
            prx.setLayoutX(657);
            prx.setLayoutY(20);
            prx.setTextFill(Color.BLACK);
            String px =Float.toString(e.getPrix());
            prx.setText(px);
            
            RadioButton rPart1 =new RadioButton();
            rPart1.setToggleGroup(r);
            rPart1.setPrefHeight(25);
            rPart1.setPrefWidth(74);            
            rPart1.setLayoutX(714);
            rPart1.setLayoutY(8);
            rPart1.setText("Non");
            
            RadioButton rPart =new RadioButton();
            rPart.setToggleGroup(r);
            rPart.setPrefHeight(25);
            rPart.setPrefWidth(74);            
            rPart.setLayoutX(714);
            rPart.setLayoutY(41);
            rPart.setText("Participer");
            


            userPane.getChildren().add(ide);
            userPane.getChildren().add(idh);
            userPane.getChildren().add(nom);
            userPane.getChildren().add(nbp);
            userPane.getChildren().add(dd);
            userPane.getChildren().add(df);
            userPane.getChildren().add(cat);
            userPane.getChildren().add(prx);
            userPane.getChildren().add(rPart1);
            userPane.getChildren().add(rPart);
            
            
            
            


            listevent.getChildren().add(userPane);
        }

    }
    
    
    

        @FXML
    private void loadMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuEvenement.fxml"));
            Parent root =loader.load();
            MenuEvenementController tr = loader.getController();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    
    
    
        private void qrcode  () throws WriterException {
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        List<evenement> event = es.afficherEvenement();
        String events = String.valueOf(event);
        int width = 300;
        int height = 300;
        
        BufferedImage bufferedImage = null;
        BitMatrix byteMatrix = qrCodeWriter.encode(events, BarcodeFormat.QR_CODE, width, height);
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bufferedImage.createGraphics();
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
        graphics.setColor(java.awt.Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(java.awt.Color.BLACK);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        imageqr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

        
    }
    
}
