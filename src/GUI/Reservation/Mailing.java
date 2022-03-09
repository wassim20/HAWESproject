/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reservation;
import Entités.Ticket;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.InternetAddress;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Houssem
 */
public class Mailing {
    public static boolean sendMail(String recepient,Ticket t)  {
        try {
            System.out.println("Preparing to send email");
            Properties properties = new Properties();
            //Enable authentication
            properties.put("mail.smtp.auth", "true");
            //Set TLS encryption enabled
            properties.put("mail.smtp.starttls.enable", "true");
            //Set SMTP host
            properties.put("mail.smtp.host", "smtp.gmail.com");
            //Set smtp port
            properties.put("mail.smtp.port", "587");
            //Your gmail address
            String myAccountEmail = "hawesesprit22@gmail.com";
            //Your gmail password
            String password = "Azerty+123";
            //Create a session with account credentials
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail, password);
                }
            });
            
            //Prepare email message
            Message message=null;
            try {
                message = prepareMessage(session, myAccountEmail, recepient, t);
            } catch (IOException ex) {
                //message = null;
                FXMain.showAlertWithHeaderText(session, "Données mail Admin Invalide", "-");
            }
            //Send mail
            if(message==null){
                FXMain.showAlertWithHeaderText(session, "Mail non envoyée", "-");
                return false;
            }else{
                Transport.send(message);
                System.out.println("Message sent successfully");
                FXMain.showAlertWithHeaderText(session, "Mail envoyée avec succées", "-");
                return true;
            }
        } catch (MessagingException ex) {
            FXMain.showAlertWithHeaderText(0, "Erreur de connexion \nInvalid Credentials", recepient);
            return false;
        }
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, Ticket t) throws IOException {
        try {
            Message message = new MimeMessage(session);
            BodyPart messageBodyPart = new MimeBodyPart(); 
            MimeBodyPart attachmentPart = new MimeBodyPart(); 
            Multipart multipart = new MimeMultipart();
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Confirmation de Réservation: Plateforme Hawes");
            String htmlCode = "<h1> WE LOVE JAVA </h1> <br/> <h2><b>Next Line </b></h2>";
            String filename = System.getProperty("user.dir")+"/PDFFiles/R"+t.getIdTicket()+"VCHR.pdf";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            return message;
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
