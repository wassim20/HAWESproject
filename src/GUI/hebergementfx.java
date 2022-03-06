
package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class hebergementfx extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
      try {
            Parent root =FXMLLoader.load(getClass().getResource("AddhbgFxml.fxml"));
            Scene scene = new Scene(root);
            String css=this.getClass().getResource("/css/dark-theme.css").toExternalForm();
            scene.getStylesheets().add(css);
            
            primaryStage.setTitle("Main");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public static void main(String[] args) {
        launch(args);
    }

    
}
