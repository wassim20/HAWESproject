/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pidevv.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;
import pidevv.entity.PickUp;
import pidevv.entity.Vol;
import pidevv.util.DataSource;

/**
 *
 * @author DELL
 */
public class VolService implements IService<Vol>{

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void add(Vol vol) {
        try {
                
            String requete = "INSERT INTO vol (compagnie, destination, dateDepart, heureDepart, heureArrivee, avion, places, prix) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, vol.getCompagnie());
            pst.setString(2, vol.getDestination());
            pst.setDate(3, vol.getDateDepart());
            pst.setString(4, vol.getHeureDepart());
            pst.setString(5, vol.getHeureArrivee());
            pst.setString(6, vol.getAvion());
            pst.setInt(7, vol.getPlaces());
            pst.setFloat(8, vol.getPrix());
			pst.executeUpdate();
            sendmail(vol);

Image img = new Image("/Check.png");
Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Vol ajouté")
                              .graphic(new ImageView(img))
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(2));
               n.darkStyle();
               n.show();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Vol> getAll() {
    ObservableList <Vol> listVol = FXCollections.observableArrayList();
       try {
            String requete = "SELECT * FROM Vol";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                listVol.add(new Vol(rs.getInt("id"), rs.getString("compagnie"), rs.getString("destination"), rs.getDate("dateDepart"), rs.getString("heureDepart"), rs.getString("heureArrivee"), rs.getString("avion"), rs.getInt("places"), rs.getFloat("prix")));
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listVol;
    }

    @Override
    public void update(Vol vol) {
        try {
                
            String requete = "UPDATE vol SET compagnie = ?, destination = ?, dateDepart = ?, heureDepart = ?, heureArrivee = ?, avion = ?, places = ?, prix = ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, vol.getCompagnie());
            pst.setString(2, vol.getDestination());
            pst.setDate(3, vol.getDateDepart());
            pst.setString(4, vol.getHeureDepart());
            pst.setString(5, vol.getHeureArrivee());
            pst.setString(6, vol.getAvion());
            pst.setInt(7, vol.getPlaces());
            pst.setFloat(8, vol.getPrix());
            pst.setFloat(9, vol.getId());
			pst.executeUpdate();
Image img = new Image("/Check.png");
Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Vol modifié")
                              .graphic(new ImageView(img))
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(2));
               n.darkStyle();
               n.show();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Vol vol) { 
        try {
            String requete = "DELETE FROM vol WHERE id=?";
              PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,vol.getId());
            pst.executeUpdate();
Image img = new Image("/Check.png");
Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Vol supprimé")
                              .graphic(new ImageView(img))
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(2));
               n.darkStyle();
               n.show();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ObservableList<Vol> search(String __input__) {
        ObservableList <Vol> listVol = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM vol where compagnie LIKE ? OR destination LIKE ? OR dateDepart LIKE ? OR heureDepart LIKE ? OR heureArrivee LIKE ? OR avion LIKE ? OR places LIKE ? OR prix LIKE ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, "%"+__input__+"%");
			pst.setString(2, "%"+__input__+"%");
			pst.setString(3, "%"+__input__+"%");
			pst.setString(4, "%"+__input__+"%");
			pst.setString(5, "%"+__input__+"%");
			pst.setString(6, "%"+__input__+"%");
			pst.setString(7, "%"+__input__+"%");
			pst.setString(8, "%"+__input__+"%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                listVol.add(new Vol(rs.getInt("id"), rs.getString("compagnie"), rs.getString("destination"), rs.getDate("dateDepart"), rs.getString("heureDepart"), rs.getString("heureArrivee"), rs.getString("avion"), rs.getInt("places"), rs.getFloat("prix")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      
        return listVol;
	}
    void sendmail(Vol p) {
final String username = "anwer.arfewi@esprit.tn";
        final String password = "181JMT0211";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("anwer.arfewi@esprit.tn"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("anwer.arfewi@esprit.tn, anwer.arfewi@esprit.tn")
            );
            message.setSubject("Vol Notification");
            message.setText(" Vol ajoute: \n "+ p);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
   }
}
