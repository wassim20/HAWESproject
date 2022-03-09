/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

import java.sql.Date;


public class Avis1 {
    private int id_avis,etoile,id_hbg,idUser;
    private String desc_avis;
    private Date dateAjoutavis;
    

    public Avis1() {
    }
    
    
    public Avis1(int id_avis, String desc_avis, int etoile, Date dateAjoutavis, int id_hbg, int idUser) {
        this.id_avis = id_avis;
        this.etoile = etoile;
        this.id_hbg = id_hbg;
        this.idUser = idUser;
        this.desc_avis = desc_avis;
        this.dateAjoutavis = dateAjoutavis;
    }

    public int getId_avis() {
        return id_avis;
    }

    public void setId_avis(int id_avis) {
        this.id_avis = id_avis;
    }

    public int getEtoile() {
        return etoile;
    }

    public void setEtoile(int etoile) {
        this.etoile = etoile;
    }

    public int getId_hbg() {
        return id_hbg;
    }

    public void setId_hbg(int id_hbg) {
        this.id_hbg = id_hbg;
    }

    public int getId_user() {
        return idUser;
    }

    public void setId_user(int idUser) {
        this.idUser = idUser;
    }

    public String getDesc_avis() {
        return desc_avis;
    }

    public void setDesc_avis(String desc_avis) {
        this.desc_avis = desc_avis;
    }

    public Date getDateAjoutavis() {
        return dateAjoutavis;
    }

    public void setDateAjoutavis(Date dateAjoutavis) {
        this.dateAjoutavis = dateAjoutavis;
    }
   
   
  /*
    @Override
    public String toString() {
        return " description=" + desc_avis + ", etoile=" + etoile + ", Date Ajout=" + dateAjoutavis + "\n \n \n"; 
    }
*/

    @Override
    public String toString() {
        return "Avis1{" + "id_avis=" + id_avis + ", etoile=" + etoile + ", id_hbg=" + id_hbg + ", idUser=" + idUser + ", desc_avis=" + desc_avis + ", dateAjoutavis=" + dateAjoutavis + '}';
    }

    
    
}
