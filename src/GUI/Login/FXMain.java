package GUI.Login;

import Entités.utilisateurs;
import Service.RealTimeService;
import Service.utilisateurService;
import Tools.LocalStorage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author Hassen Chouadah
 */
public class FXMain extends Application {

    utilisateurService US;
    RealTimeService realTime;
    @Override
    public void start(Stage primaryStage) {
        try {
            realTime = new RealTimeService();
        } catch (URISyntaxException ex) {
            System.out.println("exeption in init realtime service :"+ex.getMessage());
        }
        
        
        try {
            LocalStorage localStorage = new LocalStorage();
            utilisateurs storedUser = localStorage.getStoredUser();
            System.out.println("connected User :" + storedUser);

            if (storedUser.getIdUser() != 0) { //stored user exists
                US = new utilisateurService();
                US.updateCurrentUser(storedUser);
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("Template.fxml"));
                    Scene scene = new Scene(root, 1366, 768);
                    primaryStage.setTitle("Dashboard");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) {
                    System.out.println("GUI.FXMain.start()");
                }
            } else {//no user in storage
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                    Scene scene = new Scene(root, 1366, 768);
                    primaryStage.setTitle("Se connecter");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) {
                    System.out.println("GUI.FXMain.start()");
                }

            }

        } catch (IOException ex) {//error in parsing user
            System.out.println("error get storedUser");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene scene = new Scene(root, 1366, 768);
                primaryStage.setTitle("Se connecter");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException exp) {
                System.out.println("GUI.FXMain.start()");
            }
        }

    }
    
    @Override
    public void stop() throws Exception {
        System.out.println("closed connection");
        realTime.closeConnection();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
