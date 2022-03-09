/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.utilisateurs;
import tools.MaConnexion;
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
        cnx = MaConnexion.getInstance().getCnx();

    }
    public static utilisateurs currentUser = new utilisateurs();

    public int ajouterUtilisateur(utilisateurs U) {//autoincrement
        int resultat = 0;
        String sql = "insert into utilisateurs (cin,nomUser,prenomUser,telUser,adresseUser,mdpUser,role,emailUser,voiture,image) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, U.getCinUser());
            ste.setString(2, U.getNomUser());
            ste.setString(3, U.getPrenomUser());
            ste.setString(4, U.getTelUser());
            ste.setString(5, U.getAdresseUser());
            ste.setString(6, mdpconvert(U.getMdpUser()));
            ste.setString(7, U.getRole());
            ste.setString(8, U.getEmailUser());
            ste.setString(9, U.getVoiture());
            ste.setString(10, U.getImage());
            ste.executeUpdate();
            System.out.println("utilisateur ajouté avec succées");
            resultat = 1;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return resultat;
    }

    public void supprimerUtilisateur(int id) {
        try {
            String sql = "DELETE FROM utilisateurs WHERE idUser=" + id;
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

    public void ModifierUtilisateurByRole(String role, String cin) {//autoincrement
        String sql = "update utilisateurs set role=? where  cin=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, cin);
            ste.setString(2, role);
            int value = ste.executeUpdate();
            if (value > 0) {

                System.out.println("role modifié avec succées");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void verifierUtililsateur(String cin) {//autoincrement
        String sql = "UPDATE utilisateurs SET isVerfied = ? WHERE cin = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setBoolean(1, true);
            ste.setString(2, cin);

            ste.executeUpdate();
            System.out.println("utilisateur modifié avec succées");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void ChangePasswordWithEmail(String email, String newPassword) {//autoincrement
        String sql = "UPDATE utilisateurs SET mdpUser = ? WHERE emailUser = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            String hash = this.mdpconvert(newPassword);
            System.out.println(hash);
            ste.setString(1, hash);
            ste.setString(2, email);

            ste.executeUpdate();
            System.out.println("mot de passe modifié avec succées");

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
    
        public utilisateurs getUserByCin(String cin) {
        utilisateurs u = new utilisateurs();
        try {
            String sql = "SELECT * FROM utilisateurs where cin=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, cin);
            ResultSet rs = ste.executeQuery();//resultat requete sql
            if (rs.first()) {
                u.setIdUser(rs.getInt("idUser"));
                u.setCinUser(rs.getString("cin"));
                u.setNomUser(rs.getString("nomUser"));
                u.setPrenomUser(rs.getString("prenomUser"));
                u.setTelUser(rs.getString("telUser"));
                u.setAdresseUser(rs.getString("adresseUser"));
                u.setMdpUser(rs.getString("mdpUser"));
                u.setRole(rs.getString("role"));
                u.setEmailUser(rs.getString("emailUser"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;

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

    public String Seconnecter(String email, String motdepasse) {
        String sql = "Select * from utilisateurs where emailUser =?";
        String result = "failed";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);

            ste.setString(1, email);
            ResultSet rs = ste.executeQuery();
            if (rs.next()) {
                String pwd = rs.getString("mdpUser");
                if (mdpconvert(motdepasse).equals(pwd)) {
                    result = "success";
                    currentUser.setIdUser(rs.getInt("idUser"));
                    currentUser.setCinUser(rs.getString("cin"));
                    currentUser.setNomUser(rs.getString("nomUser"));
                    currentUser.setPrenomUser(rs.getString("prenomUser"));
                    currentUser.setTelUser(rs.getString("telUser"));
                    currentUser.setAdresseUser(rs.getString("adresseUser"));
                    currentUser.setMdpUser(rs.getString("mdpUser"));
                    currentUser.setRole(rs.getString("role"));
                    currentUser.setEmailUser(rs.getString("emailUser"));
                    currentUser.setVoiture(rs.getString("voiture"));
                    currentUser.setIsVerified(rs.getBoolean("isVerfied"));

                } else {
                    System.out.println("mdp incorrecte");
                }

            } else {

                System.out.println("email introuvable");

            }

        } catch (SQLException ex) {
            Logger.getLogger(utilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    public String getRoleByCin(String cin) throws SQLException {

        String role = null;
        try {
            String sql = "SELECT `role` FROM `utilisateurs` WHERE `cin` ='" + cin + "'";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();//resultat requete sql

            while (rs.next()) {
                role = rs.getString("role");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return role;
    }
    
    public utilisateurs getUserById(int id) throws SQLException {
        utilisateurs u = new utilisateurs();
        String role = null;
        try {
            String sql = "SELECT * FROM `utilisateurs` WHERE `idUser` ='" + id + "'";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();//resultat requete sql

            while (rs.next()) {
                    u.setIdUser(rs.getInt("idUser"));
                    u.setCinUser(rs.getString("cin"));
                    u.setNomUser(rs.getString("nomUser"));
                    u.setPrenomUser(rs.getString("prenomUser"));
                    u.setTelUser(rs.getString("telUser"));
                    u.setAdresseUser(rs.getString("adresseUser"));
                    u.setMdpUser(rs.getString("mdpUser"));
                    u.setRole(rs.getString("role"));
                    u.setEmailUser(rs.getString("emailUser"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(u);
        return u;
    }    

    public boolean getRoleUtilisateur(String cin) {
        boolean exist = false;

        try {
            String sql = "SELECT * FROM utilisateurs where cinUser=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, cin);

            ResultSet rs = ste.executeQuery();//resultat requete sql
            if (rs.first()) {
                exist = true;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return exist;

    }

    public boolean logOut() {
        currentUser.setIdUser(0);
        currentUser.setNomUser("");
        currentUser.setEmailUser("");
        currentUser.setCinUser("");
        return true;
    }
}
