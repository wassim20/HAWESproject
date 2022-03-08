/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pidevv.entity;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class ReservationPickUp {
    int id;
    PickUp pickUp;
    int idUser;
    Date date;
    int etat;

    public ReservationPickUp() {
    }

    public ReservationPickUp(PickUp pickUp, int idUser, Date date, int etat) {
        this.pickUp = pickUp;
        this.idUser = idUser;
        this.date = date;
        this.etat = etat;
    }

    public ReservationPickUp(int id, PickUp pickUp, int idUser, Date date, int etat) {
        this.id = id;
        this.pickUp = pickUp;
        this.idUser = idUser;
        this.date = date;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PickUp getPickUp() {
        return pickUp;
    }

    public void setPickUp(PickUp pickUp) {
        this.pickUp = pickUp;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }



}   
