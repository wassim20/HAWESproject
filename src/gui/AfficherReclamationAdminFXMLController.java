/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entités.Reclamation;
import Entités.Reclamation1;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import Service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherReclamationAdminFXMLController implements Initializable {

    ReclamationService rs = new ReclamationService();
    Reclamation r = new Reclamation();

    @FXML
    private ListView<Reclamation1> txtlistrec;
    @FXML
    private TextField txtsearch;
    @FXML
    private Button btngetback;
    @FXML
    private Button btntraiterrec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<Reclamation1> items = FXCollections.observableArrayList();
        List<Reclamation1> listReclamation = rs.afficherReclamation2();
        for (Reclamation1 r : listReclamation) {
            //  String ch = r.Showall(); 
            items.add(r);

        }
        txtlistrec.setItems(items);

    }

    @FXML
    private void chercherReclamation(KeyEvent event) {
        ObservableList<Reclamation1> items = FXCollections.observableArrayList();
        List<Reclamation1> listReclamation;
        String tchoix = txtsearch.getText();
        try {
            int nchoix = Integer.parseInt(tchoix);
            listReclamation = rs.chercherReclamation2(nchoix);
        } catch (NumberFormatException e) {
            listReclamation = rs.chercherReclamation2(tchoix);
        }
        for (Reclamation1 a : listReclamation) {
            String ch = a.toString();
            items.add(a);
        }
        txtlistrec.setItems(items);
    }

    @FXML
    private void loadMenu(ActionEvent event) {
        displayMenu();
    }

    private void displayMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuReclamationAvis.fxml"));
            Parent root = loader.load();
            MenuReclamationAvisController mr = loader.getController();
            btngetback.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Traiterreccal(ActionEvent event) {
        Reclamation1 r1 = new Reclamation1();

        r1 = txtlistrec.getSelectionModel().getSelectedItem();
        rs.traiterReclamation(r1);
        try {
            System.out.println(r);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamationAdminFXML.fxml"));
            Parent root = loader.load();

            AfficherReclamationAdminFXMLController mda = loader.getController();

            btntraiterrec.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        this.EnvoyerEmail("mohamedbenhamida10@gmail.com");
        //com.tutorialspoint.SendAttachmentInEmail.main();

    }

    public void EnvoyerEmail(String adressemail) {

        Properties props;
        props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
//props.setProperty("mail.smtp.ssl.enable", "true");

        String username = "tt6800734@gmail.com";
        String password = "M20199caca";
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress("ServiceReclamation@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(adressemail));

            message.setSubject("Reclamation Client");
            message.setText("Votre réclamation a été traitée ");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
