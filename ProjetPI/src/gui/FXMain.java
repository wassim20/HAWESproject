/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Paiement;
import entities.Reservation;
import entities.Ticket;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author Houssem
 */
public class FXMain extends Application {


    static Reservation r = new Reservation();
    static Paiement p = new Paiement();
    static Ticket t = new Ticket();
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("MenuReservationPaiement.fxml"));
            Scene scene = new Scene(root,822,767);    
            scene.getStylesheets().add(getClass().getResource("styleFXML.css").toExternalForm());
            primaryStage.setTitle("Menu");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
        //////////////////////// BOITE DE DIALOGUE ////////////////////////////
    public static void showAlertWithHeaderText(Object o, String s, String op) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Boite de Dialogue");
        if(s.length()==0){
            if ( o.getClass() == r.getClass() ){
                if (op.equals("+")){
                    alert.setHeaderText("Reservation Ajoutée avec succés:");
                }
                if (op.equals("-")){
                    alert.setHeaderText("Reservation Supprimé avec succés:");
                }
                if (op.equals("*")){
                    alert.setHeaderText("Reservation Modifié avec succés:");
                }
            }else if ( o.getClass() == t.getClass() ){
                if (op.equals("+")){
                    alert.setHeaderText("Ticket Ajoutée avec succés:");
                }
                if (op.equals("-")){
                    alert.setHeaderText("Ticket Supprimé avec succés:");
                }
                if (op.equals("*")){
                    alert.setHeaderText("Ticket Modifié avec succés:");
                }
            }
        }else{
            alert.setHeaderText(s);
        }
        alert.setContentText("Appuyez sur OK");
        alert.showAndWait();
    }
    
    
    public static int showConfirmationAlert(Object o) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Boite de Dialogue");
        alert.setHeaderText("Voulez vous vraiment supprimer : ");
        alert.setContentText(o.toString());
        //alert.showAndWait();
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.CANCEL) {
           alert.setContentText("Annulé");
           return 0;
        }else{
            return 1;
        }
    }
    
    public static void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Boite de Dialogue");
        alert.setHeaderText("Priére de remplir les champs convenablement");
        alert.setContentText("Appuyez sur OK");
        alert.showAndWait(); 
    }
        /////////////// FFFFIIINNN BOITE DE DIALOGUE /////////////
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
