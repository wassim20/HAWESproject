
package Entités;


public class categorie {
    
    private int id_hbg;
    private int etoile;

    public int getId_hbg() {
        return id_hbg;
    }

    public void setId_hbg(int id_hbg) {
        this.id_hbg = id_hbg;
    }

    public int getEtoile() {
        return etoile;
    }

    public void setEtoile(int etoile) {
        this.etoile = etoile;
    }

    public categorie(int id_hbg, int etoile) {
        this.id_hbg = id_hbg;
        this.etoile = etoile;
    }

    @Override
    public String toString() {
        return "categorie{" + "id_hbg=" + id_hbg + ", etoile=" + etoile + '}';
    }
    

    

    public categorie() {
    }

    

   
   
    
    
    
    
}
