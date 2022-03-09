
package GUI;

import entities.hebergement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import services.hebergementService;

/**
 * FXML Controller class
 *
 * @author GX15
 */
public class Test1Controller implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label price;
    @FXML
    private ImageView img;
    @FXML
    private Label adressh;
    @FXML
    private Label pisch;
    @FXML
    private VBox chosenh;
    @FXML
    private Label etoileh;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
    hebergementService ps=new hebergementService();
    List<hebergement> hbgs=new ArrayList<>();
    private mylistener myListener;
    @FXML
    private Label pricer;
    @FXML
    private ImageView hawes;
    
    
    private List<hebergement> getData() {
        List<hebergement> hbgs = new ArrayList<>();
        hbgs.addAll(ps.afficherHebergement1());
        return hbgs;
    
    }
    private List<hebergement> getData2() {
        List<hebergement> hbgs = new ArrayList<>();
        hbgs.addAll(ps.afficherHebergement2());
        return hbgs;
    
    }
    static String imagepath;
    private void setChosenhbg(hebergement h) throws FileNotFoundException {
        nom.setText(h.getNom_hotel());
        price.setText(String.valueOf(h.getPrix())+"DT");
        img.setImage(new Image(new FileInputStream(h.getImage())));
        imagepath=h.getImage();
        if(h.getPiscine()==1){
        pisch.setText("YES");
        }else {pisch.setText("NO");}
        adressh.setText(h.getAdress());
        etoileh.setText(String.valueOf(h.getEtoile()));
        pricer.setText("             20%\n pour 3 nuite ou plus ");
        
       // chosenh.setStyle("-fx-background-color: #efefef22" + ";\n" +
         //       "    -fx-background-radius: 30;");
    }
    
     
     
     

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        try {
            hawes.setImage(new Image(new FileInputStream("C:\\Users\\GX15\\Downloads\\logohawes.PNG")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        hbgs.addAll(getData());
        if(hbgs.size()>0){
            try {
                setChosenhbg(hbgs.get(0));
                myListener = new mylistener() {
                    @Override
                    public void onClicklistener(hebergement h) {
                        try {
                            setChosenhbg(h);
                        } catch (FileNotFoundException ex) {

                        }
                    }
                };
            } catch (FileNotFoundException ex) {
               
            }
        }
        
        
        int column=1;int row=0;
        try {
            for (int i = 0; i < hbgs.size(); i++) {
                FXMLLoader fxmlLoader1 = new FXMLLoader();
                fxmlLoader1.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader1.load();

                ItemController itemController = fxmlLoader1.getController();
                itemController.setData(hbgs.get(i),myListener);
                
                if (column == 2) {
                    column = 0;
                    row++;
                }

               

                
                
                grid.add(anchorPane, column, row++); //(child,column,row)
               GridPane.setMargin(anchorPane, new javafx.geometry.Insets(0, 0, 0, 0));
               grid.setMinWidth(Region.USE_COMPUTED_SIZE);
               grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
               grid.setMaxWidth(Region.USE_PREF_SIZE);
               
               //now height
               
               grid.setMinHeight(Region.USE_COMPUTED_SIZE);
               grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
               grid.setMaxHeight(Region.USE_PREF_SIZE);
               
               
               
               
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void pdf(ActionEvent event) {
        
        String a=nom.getText();
        String b=price.getText();
        String c=adressh.getText();
        String d=etoileh.getText();
        
        Image image;
        image=img.getImage();
        
        pdf p =new pdf();
       if( p.makepdf(a,b,c,d,imagepath))
       {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.show();
                        alert.setTitle(" PDF CREATION!");
                        alert.setContentText("votre pdf est creé");
       }
    }

}
        
       
    

