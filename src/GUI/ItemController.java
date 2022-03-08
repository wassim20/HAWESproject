  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.hebergement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author GX15
 */
public class ItemController implements Initializable {

    @FXML
    private ImageView img1;
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
    private void Click(MouseEvent mouseEvent) {
        myListener.onClicklistener(h);
    }

    private hebergement h;
    private mylistener myListener;
    
    public void setData(hebergement h,mylistener myListener) throws FileNotFoundException {
        this.h=h;
        this.myListener=myListener;
        type1.setText(h.getNom());
        city1.setText(h.getCity());
        adress1.setText(h.getAdress());
        nomh1.setText(h.getNom_hotel());
        if(h.getPiscine()==1){
        pisc1.setText("YES");
        }else {pisc1.setText("NO");}
        prix1.setText("$"+String.valueOf(h.getPrix()));
        img1.setImage(new Image(new FileInputStream(h.getImage())));
        
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

   

    

    
    
}
