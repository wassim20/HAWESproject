/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpi;

import Entités.utilisateurs;
import Service.utilisateurService;
import Tools.MyConnexion;
import static com.oracle.jrockit.jfr.DataType.U1;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hassen
 */
public class ProjetPI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyConnexion mc = MyConnexion.getInstance();
        utilisateurService Us = new utilisateurService();
        utilisateurs U1 = new utilisateurs(20, "13017475", "Chouadah", "Hassen", "hassen.chouadah@esprit.tnn", Us.mdpconvert("azerty123"), "Beb el khadhra tunis", "55817976", "Chef d'agence");
        utilisateurs U2 = new utilisateurs(3, "13044987", "Foulen", "Ben FOulen", "foulen.chouadah@yahoo.fr", Us.mdpconvert("qwerty321"), "Ezzahra tunis", "53559481", "Admin");
        //Controle de saisie
        if (U1.getNomUser().isEmpty()) {
            System.out.println("Veuillez entrer votre nom");
        } else if (U1.getPrenomUser().isEmpty()) {
            System.out.println("Veuillez entrer votre Prenom");
        } else if (U1.getCinUser().length() != 8) {
            System.out.println("Votre CIN est incorrect");
        } else if (Us.getUtilisateurByCin(U1.getCinUser()) == true) {//test cin exist
            System.out.println("Utilisateur existe deja (cin existe)");
        } else if (Us.getUtilisateurByEmail(U1.getEmailUser()) == true) {//test email exist
            System.out.println("Utilisateur existe deja (email existe)");
        } else if (U1.getTelUser().isEmpty()) {
            System.out.println("Veuillez entrer votre numéro de téléphone");
        } else {

            Us.ajouterUtilisateur(U1);

        }
           System.out.println(Us.getUtilisateurByEmail("hassen.chouadah@esprit.tn"));
            
        //Us.modifierUtilisateur(U1);
    }
       
    // TODO code application logic here
}