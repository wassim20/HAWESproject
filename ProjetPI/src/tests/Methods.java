/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tests;
import entities.Reservation;
import services.ReservationService;
import entities.ReservationEvent;
import services.ReservationEventService;
import entities.Paiement;
import services.PaiementService;
import entities.Ticket;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import services.TicketService;
import tools.MaConnexion;

/**
 *
 * @author Houssem
 */

    public class Methods{
        //Services//
        ReservationService rs = new ReservationService();
        ReservationEventService res = new ReservationEventService();
        TicketService ts = new TicketService();
        PaiementService ps = new PaiementService();
        // Variables Necessaires //
        MaConnexion mc = MaConnexion.getInstance();
        Scanner scan = new Scanner(System.in);
        // Variables Secondaires //
        int idTicket;
        int idPaiement;
        int cp;
        Ticket t = new Ticket();
        Reservation r = new Reservation();
        Paiement p = new Paiement();
        int forfait = 0;
        Date d = new Date(100,0,1);  //// commence de 1900/1/1
        SimpleDateFormat formater = null;
        //System.out.println(formater.format(d));        
        //System.out.println(d);            
        

///////////////////////////////////////         AJOUT DES EVENTS A LA RESERVATION       /////////////////////////////////////        
            public ReservationEvent addReservationEvent(Reservation reservation){     //// A Changer 
                ReservationEvent re = new ReservationEvent();
                String ce;
                Scanner scanner = new Scanner(System.in);
                Scanner scan = new Scanner(System.in);
                // UNE FONCTION qui affiche les events
                System.out.println("Souhaitez vous ajouter des events a votre reservation ? ");
                System.out.print("o - oui   |||||  n - non ");
                ce = scanner.nextLine();
                //Scanner reader = new Scanner(System.in);
                //char ce = reader.next().trim().charAt(0);
                do{
                if(ce.equals("n")){
                    System.out.println("Alors il n'y a pas des events ");
                }else if (ce.equals("o")){
                        System.out.print("Entrez l'id de L'evenement\t");
                        re.setIdEvent(scan.nextInt());
                        re.setIdRes(reservation.getIdRes());
                        System.out.println("Souhaitez vous ajouter des events a votre reservation ? ");
                        System.out.print("o - oui   |||||  n - non \t");
                        ce = scanner.nextLine();
                        res.ajouterReservationEvent(re);
                }else{
                    System.out.println("Erreur ---> Choix Invalide");
                }}while("o".equals(ce));
                return re;
            }    
        
////////////////////////////////////////////////////// AJOUT D'UN PAIEMENT /////////////////////////////////////////////////        
            public Paiement addPaiement(Reservation reservation) {
                //Paiement p = new Paiement(0,10,d,"virement",830.900);
                p.setIdPmt(0);
                p.setIdRes(reservation.getIdRes());
                p.setDatePmt(d);
                System.out.print("Entrez la methode de paiement : ");
                int cp=0;
                do{
                System.out.println("1- pour especes ||||| 2 pour virement |||| 3 pour cheque");
                cp = scan.nextInt();
                }while((cp > 3)||(cp < 1));
                if (cp==1){
                    p.setMethode("Especes");
                }else if (cp==2){
                    p.setMethode("Virement");
                }else if (cp==3){
                    p.setMethode("Cheque");
                }
                System.out.println("Entrez le montant payée : ");
                p.setMontant(scan.nextDouble());
                p.setCanceled(0);
                ps.ajoutertPaiement(p);
                return p;
                 //ps.annulerPaiement(1);
                //System.out.println(ps.afficherPaiement());
            }
            
            
/////////////////////////////////////////       AJOUT D'UNE TICKET              //////////////////////////////////////////
            public Ticket addTicket(Reservation r){
                Paiement np = ps.getPaiementByReservation(r);
                t.setIdTicket(0);
                t.setIdPmt(np.getIdPmt());
                t.setIdRes(r.getIdRes());
                t.setDeleted(0);
                ts.ajouterTicket(t);
                return t;
            }
            
           
////////////////////////////////////////////     MODIFICATION D'UNE TICKET      ///////////////////////////////////
            public void editTicket(){
                ts.afficherTicketValable();
                System.out.print("Entrez l'id du Ticket à modifier : ");
                idTicket = scan.nextInt();
                System.out.print("Entrez le nouveau id du Reservation : ");
                t.setIdRes(scan.nextInt());
                System.out.print("Entrez le nouveau id du paiement : ");
                t.setIdPmt(scan.nextInt());
                ts.modifierTicket(idTicket,t);
                ts.afficherTicketValable();
            }
            
            
//////////////////////////////              SUPPRESSION D'UNE TICKET            ////////////////////////////////
            public void cancelTicket(){
                ts.afficherTicketValable();
                System.out.print("Entrez l'id du Ticket à supprimer : ");
                idTicket = scan.nextInt();
                ts.supprimerTicket(idTicket);
                ts.afficherTicketSupprime();
            }    

            
//////////////////////////////              AJOUT RESERVATION               ///////////////////////
            public Reservation addReservation(){
                Reservation r = new Reservation();
                int nbPr=0;
                int nbCh=1;
                int nbSt=1;
                r.setIdRes(0);
                r.setIdUser(1);
                r.setIdHebr(1);
                r.setIdVol(1);
                r.setValide(0);
                r.setDateArr(d);
                r.setDateDep(d);
                r.setDateRes(d);
                r.setDeadlineAnnulation(d);
                do{
                //fonction qui affiche l'hebergement et les prix
                System.out.println("Pour combien de personne(s) vous souhaitez reservez ? ");
                nbPr = scan.nextInt();
                System.out.println("combien de chambre(s) vous souhaitez reservez ? ");
                nbCh = scan.nextInt();
                System.out.println("combien de suite(s) vous souhaitez reservez ? ");
                nbSt = scan.nextInt();
                if(nbPr/2 < nbCh+nbSt){
                    System.out.println("Une ou Deux Personne(s) par chambre ou suite");
                }
                }while((nbPr/2 > nbCh+nbSt));
                System.out.println("Quelle forfait vous choisissez ? ");
                do{
                System.out.println("tapez ---> (1) pour All in ---> (2) pour Pension Complete ---> (3) pour Demi Pension");
                int forfait = scan.nextInt();
                if ( forfait == 1) {
                    r.setForfait("All-in");
                }else if ( forfait == 2 ) {
                    r.setForfait("Pension Complete");
                }else if ( forfait == 3 ) {
                    r.setForfait("Demi Pension");
                }}while((forfait < 1) && ( forfait > 3));
                r.setNbPersonne(nbPr);
                r.setNbChambre(nbCh);
                r.setNbSuite(nbSt);
                if(rs.getIdByReservation(r).getIdRes()==0){
                    rs.ajouterReservation(r);
                    Reservation r1 = new Reservation();
                    r1 = rs.getIdByReservation(r);
                    return r1;
                }else {
                    System.out.println("Réservation déjà existante");
                    return null;
                }
            }
            
            
            
////////////////////////////////////////////     MODIFICATION D'UN PAIEMENT      ///////////////////////////////////
            public void editPaiement(){
                System.out.println(ps.afficherPaiement());
                System.out.print("Entrez l'id du Ticket à modifier : ");
                idPaiement = scan.nextInt();
                Paiement op= ps.getPaiementById(idPaiement);
                do{
                System.out.println("1- pour especes ||||| 2 pour virement |||| 3 pour cheque");
                cp = scan.nextInt();
                }while((cp > 3)||(cp < 1));
                if (cp==1){
                    p.setMethode("Especes");
                }else if (cp==2){
                    p.setMethode("Virement");
                }else if (cp==3){
                    p.setMethode("Cheque");
                }
                System.out.print("Entrez le nouveau montant : ");
                p.setMontant(scan.nextDouble());
                ps.modifierPaiement(op,p);
                ts.afficherTicketValable();
            }
            
////////////////////////////////////////////     MODIFICATION D'UNE RESERVATION       ///////////////////////////////////
            public void editReservation(){
                int nbPr=0;
                int nbCh=1;
                int nbSt=1;
                Reservation nr = new Reservation();
                System.out.println(rs.afficherReservation());
                System.out.print("Entrez l'id du Reservation à modifier : ");
                int idReservation = scan.nextInt();
                Reservation or= rs.getReservationById(idReservation);
                nr.setIdRes(or.getIdRes());
                nr.setIdUser(1);
                nr.setIdHebr(1);
                nr.setIdVol(1);
                nr.setValide(0);
                nr.setDateArr(d);
                nr.setDateDep(d);
                nr.setDateRes(d);
                nr.setDeadlineAnnulation(d);
                do{
                //fonction qui affiche l'hebergement et les prix
                System.out.println("Pour combien de personne(s) vous souhaitez reservez ? ");
                nbPr = scan.nextInt();
                System.out.println("combien de chambre(s) vous souhaitez reservez ? ");
                nbCh = scan.nextInt();
                System.out.println("combien de suite(s) vous souhaitez reservez ? ");
                nbSt = scan.nextInt();
                if(nbPr/2 < nbCh+nbSt){
                    System.out.println("Une ou Deux Personne(s) par chambre ou suite");
                }
                }while((nbPr/2 > nbCh+nbSt));
                System.out.println("Quelle forfait vous choisissez ? ");
                do{
                System.out.println("tapez ---> (1) pour All in ---> (2) pour Pension Complete ---> (3) pour Demi Pension");
                int forfait = scan.nextInt();
                if ( forfait == 1) {
                    nr.setForfait("All-in");
                }else if ( forfait == 2 ) {
                    nr.setForfait("Pension Complete");
                }else if ( forfait == 3 ) {
                    nr.setForfait("Demi Pension");
                }}while((forfait < 1) && ( forfait > 3));
                nr.setNbPersonne(nbPr);
                nr.setNbChambre(nbCh);
                nr.setNbSuite(nbSt);
                rs.modifierReservation(or, nr);
            }
                        
////////////////////////////////////////////    VALIDER RESERVATION  ////////////////////////////////////////////       
            public void validateReservation(){
                int idRes;
                System.out.println(rs.afficherReservation());
                do{
                System.out.println("Entrez l'id de resérvations à valider");
                idRes= scan.nextInt();
                }while(rs.getReservationById(idRes).getIdRes()==0);
                r=rs.getReservationById(idRes);
                rs.traiterReservation(r, 1);
            }
            
            
////////////////////////////////////////////    ANNULER RESERVATION  ////////////////////////////////////////////       
            public void cancelReservation(){
                int idRes;
                System.out.println(rs.afficherReservation());
                do{
                System.out.println("Entrez l'id de resérvations à annuler");
                idRes= scan.nextInt();
                }while(rs.getIdByReservation(r).getIdRes()==0);
                r=rs.getReservationById(idRes);
                rs.traiterReservation(r, -1);
            }          
            
            
            
            
          ////////////////////// AFFICHAGE DE LA BASE //////////////////////////////
            public void showReservations(){
                System.out.println(rs.afficherReservation());
            }
            
            public void showPaiement(){
                System.out.println(ps.afficherPaiement());
            }
            
            public void showTickets(){
                System.out.println(ts.afficherTicketValable());
            }
            
            public void showArchivedTickets(){
                System.out.println(ts.afficherTicketSupprime());
            }
            
    }