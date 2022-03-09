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
public class categorieeve {
    
    private int idCat;
    private String nomCat;


    public categorieeve() {
    }

    public categorieeve(int idCat, String nomCat) {
        this.idCat = idCat;
        this.nomCat = nomCat;

    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }



    @Override
    public String toString() {
        return "categorieEve{" + "idCat=" + idCat + ", nomCat=" + nomCat + '}';
    }
    
    
    
    
}
