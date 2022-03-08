/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author achre
 */
public class participants {
    
    private int idPart;
    private int idUser;
    private int idEvent;

    public participants(int idPart, int idUser , int idEvent) {
        this.idPart = idPart;
        this.idUser = idUser;
        this.idEvent = idEvent;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }
    
    

    public participants() {
    }

    public int getIdPart() {
        return idPart;
    }

    public void setIdPart(int idPart) {
        this.idPart = idPart;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "participants{" + "idPart=" + idPart + ", idUser=" + idUser + "idEvent=" + idEvent +'}';
    }
    
    
     
}
