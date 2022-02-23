
package tests;

import java.time.LocalDate;
import entities.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.*;
import java.util.Map;
import java.util.function.Consumer;
import services.hebergementService;
import tools.MaConnexion;


public class Hawes {

    
   
    public static void main(String[] args) throws ParseException {
    
        
        String sDate1="31/12/1998";
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        Date date1=formatter1.parse(sDate1);
        
        String a="o";
        int b=100;
        MaConnexion mc = MaConnexion.getInstance();
        
        //id_hbg;nom;city;date_ajout;adress;nom_hotel;nb_chambres;nb_suites;piscine;image;prix;
        hebergement h = new hebergement(1,"hotel","tunis",date1,"swi9a","becha",300,5,1,"tunis",300);
        hebergement h1 = new hebergement(2,"hotel","tunis",date1,"hamamet","mouradi",500,20,1,"tunis",150);
        hebergement h2 = new hebergement(3,"maison_dhote","sousse",date1,"","",3,0,1,"tunis",150);
        hebergement h3 = new hebergement(4,"maison_dhote","tunis",date1,"diyar hamamet","",3,0,1,"tunis",150);
        hebergementService ps = new hebergementService();
        
         //ps.ajouterhebergement(h,3);
         //ps.ajouterhebergement(h1, 2);
         //ps.ajouterhebergement(h2, 5);
         //ps.ajouterhebergement(h3, 2);
         
        //ps.updatehebergement(h1,1,5);
        //ps.rechercheHebergement(a);
       // ps.deletehebergement(h);
       HashMap<hebergement,Integer> result = new HashMap<hebergement,Integer>();
       result=(HashMap<hebergement, Integer>) ps.rechercheHebergement(a);
        for (Map.Entry mapentry : result.entrySet()) {
            System.out.println(" "+mapentry.getKey()
                    + " | etoile: " + mapentry.getValue());
         // System.out.println(ps.afficherHebergement());
        //System.out.println(ps.afficherHebergement());
       
    
    
    }
        
    }
}
        
    

    

