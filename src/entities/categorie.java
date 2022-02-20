
package entities;


public class categorie {
    
    private int id_hbg;
    private int etoile;
    

    public int getId_cat() {
        return id_hbg;
    }

    public void setId_cat(int id_cat) {
        this.id_hbg = id_cat;
    }

    public int getEtoile() {
        return etoile;
    }

    public void setEtoile(int etoile) {
        this.etoile = etoile;
    }

    public categorie() {
    }

    public categorie(int id_cat, int etoile) {
        this.id_hbg = id_cat;
        this.etoile = etoile;
    }

    @Override
    public String toString() {
        return "categorie{" + "id_cat=" + id_hbg + ", etoile=" + etoile + '}';
    }

   
   
    
    
    
    
}
