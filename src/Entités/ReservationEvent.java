/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;
/**
 *
 * @author Houssem
 */
public class ReservationEvent {
    private int idRes,idEvent;
    
    public ReservationEvent() {
    }

    public ReservationEvent(int idRes, int idEvent) {
        this.idRes = idRes;
        this.idEvent = idEvent;
    }

    public int getIdRes() {
        return idRes;
    }

    public void setIdRes(int idRes) {
        this.idRes = idRes;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    @Override
    public String toString() {
        return "Reservation Event{ idRes=" + idRes + ", idEvent=" + idEvent + "}";
    }
}
