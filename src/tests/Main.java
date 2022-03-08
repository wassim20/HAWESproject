/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.Avis;
import entities.Reclamation;
import java.sql.Date;
import services.AvisService;
import services.ReclamationService;
import tools.MaConnexion;

/**
 *
 * @author Fayechi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MaConnexion mc = MaConnexion.getInstance();
        
       // Date d = new Date(100,0,1);

        
       Reclamation r1 = new Reclamation(0,"test60",0,new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),1,21);
      
        ReclamationService rs = new ReclamationService();
       rs.ajouterReclamation(r1);
       //System.out.println(rs.afficherReclamation());
        
       //rs.supprimerReclamation(6);  ///testi 3la traitement sinnon mayfgasa5ch w mayamodifich .
      if (r1.getTraite()==0){ 
      //rs.modifierReclamation(r1, 7, "modifie une reclamation", 1, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 1, 1);
      
      }
   
    
      
      Avis a1 = new Avis(1, "test", 4, new Date(System.currentTimeMillis()), 1, 21);
      AvisService avs = new AvisService();
      
      //avs.ajouterAvis(a1);
       // System.out.println(avs.afficherAvis());
     // avs.supprimerAvis(1);
     //avs.modifierAvis(a1,1,"test2", 2, d, 1, 1);

      
      
      
        
        
        
        
    }
    
    
}

