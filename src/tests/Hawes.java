
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
        
        
        MaConnexion mc = MaConnexion.getInstance();
        
        //id_hbg;nom;city;date_ajout;adress;nom_hotel;nb_chambres;nb_suites;piscine;image;prix;
        hebergement h = new hebergement(1,"hotel","tunis",date1,"swi9a","becha",300,5,1,"tunis",300);
        hebergement h1 = new hebergement(2,"maison_dhote","tunis",date1,"hamamet","",3,0,1,"tunis",150);
        hebergementService ps = new hebergementService();
        
         //ps.ajouterhebergement(h,3);
         //ps.ajouterhebergement(h1, 5);
        ps.updatehebergement(h1,1,5);
       // ps.deletehebergement(h);
       /*Map<hebergement,Integer> result = new HashMap<hebergement,Integer>();
       result=ps.afficherHebergement();
       for (Map.Entry<hebergement,Integer> entry : result.entrySet())
            System.out.println("Key = " + entry.getKey() +
                             ", Value = " + entry.getValue());*/
       // System.out.println(ps.afficherHebergement());
        
       
    
    
    }
        
    
}
    

