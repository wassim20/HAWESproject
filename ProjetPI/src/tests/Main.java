/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;
import tests.Methods;
import tools.MaConnexion;
import entities.Reservation;
import services.ReservationService;
import entities.ReservationEvent;
import services.ReservationEventService;
import entities.Paiement;
import services.PaiementService;
import entities.Ticket;
import services.TicketService;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.List;
import java.sql.Date;
/**
 *
 * @author Houssem
 */

public class Main {
    public static void main(String[] args) {
        ReservationService rs = new ReservationService();
        Methods mt= new Methods();
/*        Reservation nr = mt.addReservation();
        Reservation reservation = rs.getIdByReservation(nr);
        System.out.println(reservation);
        Paiement np = mt.addPaiement(reservation);
        mt.addTicket(nr);
        mt.addReservationEvent(reservation);*/
        mt.validateReservation();
        //
        //mt.showPaiement();
        //mt.editPaiement();
        //mt.showTickets();
        //mt.showArchivedTickets();
        //mt.cancelTicket();
        mt.showReservations();        
//////////      FFFFIIIINNNN         //////////                        
    }
    

}