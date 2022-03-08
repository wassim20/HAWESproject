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
import pidevv.entity.ReservationPickUp;
import pidevv.entity.Vol;
import pidevv.util.DataSource;

/**
 *
 * @author DELL
 */
public class ReservationPickUpService implements IService<ReservationPickUp>{
    
    Connection cnx = DataSource.getInstance().getCnx();
    PickUpService pus = new PickUpService();

    @Override
    public void add(ReservationPickUp rpu) {
        try {
                
            String requete = "INSERT INTO ReservationPickUp (iduser, idpickup, date, etat) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, rpu.getIdUser());
            pst.setInt(2, rpu.getPickUp().getId());
            pst.setDate(3, rpu.getDate());
            pst.setInt(4, rpu.getEtat());
			pst.executeUpdate();
            sendmail(rpu);

Image img = new Image("/Check.png");
Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Reservation pickup ajouté")
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
    public ObservableList<ReservationPickUp> getAll() {
        ObservableList <ReservationPickUp> listRPU = FXCollections.observableArrayList();
       try {
            String requete = "SELECT * FROM ReservationPickUp";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PickUp pickUp = pus.getById(rs.getInt("idPickUp"));
                listRPU.add(new ReservationPickUp(rs.getInt("id"), pickUp, rs.getInt("idUser"), rs.getDate("date"), rs.getInt("etat")));
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listRPU;
    }

    @Override
    public void update(ReservationPickUp rpu) {
        try {
                
            String requete = "UPDATE ReservationPickUp SET iduser = ?, idpickup = ?, date = ?, etat = ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, rpu.getIdUser());
            pst.setInt(2, rpu.getPickUp().getId());
            pst.setDate(3, rpu.getDate());
            pst.setInt(4, rpu.getEtat());
            pst.setInt(5, rpu.getId());
			pst.executeUpdate();
Image img = new Image("/Check.png");
Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Reservation pickup modifié")
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
    public void delete(ReservationPickUp rpu) { 
        try {
            String requete = "DELETE FROM ReservationPickUp WHERE id=?";
              PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,rpu.getId());
            pst.executeUpdate();
Image img = new Image("/Check.png");
Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Reservation pickup supprimeé")
                              .graphic(new ImageView(img))
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(2));
               n.darkStyle();
               n.show();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ObservableList<ReservationPickUp> search(String __input__) {
    ObservableList <ReservationPickUp> listRPU = FXCollections.observableArrayList();


        try {
            String requete = "SELECT * FROM ReservationPickUp r INNER JOIN pickUp p ON r.idPickUp = p.id where r.iduser LIKE ? OR r.idpickup LIKE ? OR r.date LIKE ? OR r.etat LIKE ? OR p.adresseArrivee LIKE ? OR p.adresseDepart LIKE ? OR p.heureDepart LIKE ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, "%"+__input__+"%");
			pst.setString(2, "%"+__input__+"%");
			pst.setString(3, "%"+__input__+"%");
			pst.setString(4, "%"+__input__+"%");
			pst.setString(5, "%"+__input__+"%");
			pst.setString(6, "%"+__input__+"%");
			pst.setString(7, "%"+__input__+"%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PickUp pickUp = pus.getById(rs.getInt("idPickUp"));
                listRPU.add(new ReservationPickUp(rs.getInt("id"), pickUp, rs.getInt("idUser"), rs.getDate("date"), rs.getInt("etat")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      
        return listRPU;
	}

void sendmail(ReservationPickUp p) {
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
            message.setSubject("Reservation Pickup Notification");
            message.setText(" Reservation pickup ajoute: \n "+ p);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
   }
}
