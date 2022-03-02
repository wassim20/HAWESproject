
package GUI;

import entities.hebergement;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import services.hebergementService;


public class AfficherhbgController implements Initializable {

    @FXML
    private TextField listhbg;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public void setListHbg(hebergementService ps) {
        HashMap<hebergement,Integer> result = new HashMap<hebergement,Integer>();
       result=(HashMap<hebergement, Integer>) ps.afficherHebergement();
        
        
    
        
        for (Map.Entry mapentry : result.entrySet()) {
            //System.out.println(" "+mapentry.getKey()
                 //   + " | etoile: " + mapentry.getValue());
                 this.listhbg.setText((String) mapentry.getKey());
                 this.listhbg.setText((String) mapentry.getValue());
    }
      
    
}
}
