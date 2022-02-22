/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entités.utilisateurs;
import Tools.MyConnexion;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hassen
 */
public class utilisateurService {

    Connection cnx;

    public utilisateurService() {
        cnx = MyConnexion.getInstance().getCnx();
    }

    public void ajouterUtilisateur(utilisateurs U) {//autoincrement
        String sql = "insert into utilisateurs (cin,nomUser,prenomUser,telUser,adresseUser,mdpUser,role,emailUser) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, U.getCinUser());
            ste.setString(2, U.getNomUser());
            ste.setString(3, U.getPrenomUser());
            ste.setString(4, U.getTelUser());
            ste.setString(5, U.getAdresseUser());
            ste.setString(6, U.getMdpUser());
            ste.setString(7, U.getRole());
            ste.setString(8, U.getEmailUser());
            ste.executeUpdate();
            System.out.println("utilisateur ajouté avec succées");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerUtilisateur(utilisateurs U) {
        try {
            String sql = "DELETE FROM utilisateurs WHERE idUser=" + U.getIdUser();
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("User Supprimée ");
        } catch (SQLException ex) {
            Logger.getLogger(utilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierUtilisateur(utilisateurs U) {//autoincrement
        String sql = "UPDATE utilisateurs SET cin = ?,nomUser = ?, prenomUser= ?, telUser= ?, adresseUser= ?, mdpUser= ?, role= ?, emailUser= ? WHERE idUser = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, U.getCinUser());
            ste.setString(2, U.getNomUser());
            ste.setString(3, U.getPrenomUser());
            ste.setString(4, U.getTelUser());
            ste.setString(5, U.getAdresseUser());
            ste.setString(6, U.getMdpUser());
            ste.setString(7, U.getRole());
            ste.setString(8, U.getEmailUser());
            ste.setInt(9, U.getIdUser());
            ste.executeUpdate();
            System.out.println("utilisateur modifié avec succées");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public boolean getUtilisateurByEmail(String email) {
        boolean exist = false;

        try {
            String sql = "SELECT * FROM utilisateurs where emailUser=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, email);

            ResultSet rs = ste.executeQuery();//resultat requete sql
            if (rs.first()) {
                exist = true;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return exist;

    }

    public boolean getUtilisateurByCin(String cin) {

        boolean exist = false;
        try {
            String sql = "SELECT * FROM utilisateurs where cin=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, cin);
            ResultSet rs = ste.executeQuery();//resultat requete sql
            if (rs.first()) {
                exist = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return exist;

    }

    //Fonction de hachage mot de passe md5 
    public String mdpconvert(String p) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(p.getBytes());
            BigInteger pwd = new BigInteger(1, messageDigest);
            String hashpwd = pwd.toString(16);
            return hashpwd;

        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
 //Fonction de modification de mot de passe md5 
    public void modifierMotdepasse(int cin, String mdp) {
        String sql = "update utilisateurs set motdepasse=? where  cinUser=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, mdp);
            ste.setInt(2, cin);
            ste.executeUpdate();
            System.out.println("Mot de passe modifier avec succées");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}