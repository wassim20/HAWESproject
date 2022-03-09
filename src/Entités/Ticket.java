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
public class Ticket {
    private int idTicket,idPmt,idRes,deleted;

    public Ticket() {
    }

    public Ticket(int idTicket, int idPmt, int idRes) {
        this.idTicket = idTicket;
        this.idPmt = idPmt;
        this.idRes = idRes;
    }
    
    public Ticket(int idTicket, int idPmt, int idRes, int deleted) {
        this.idTicket = idTicket;
        this.idPmt = idPmt;
        this.idRes = idRes;
        this.deleted = deleted;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdPmt() {
        return idPmt;
    }

    public void setIdPmt(int idPmt) {
        this.idPmt = idPmt;
    }

    public int getIdRes() {
        return idRes;
    }

    public void setIdRes(int idRes) {
        this.idRes = idRes;
    }

    
    
    @Override
    public String toString() {
        return "Ticket{" + "idTicket=" + idTicket + ", idPmt=" + idPmt + ", idRes=" + idRes + ", Deleted=" + deleted + '}';
    }
    
    
}
