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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
}
