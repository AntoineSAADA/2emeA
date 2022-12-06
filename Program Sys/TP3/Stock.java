package TP3;

public class Stock {
    private int nbRoues;
    private int nbMoteur;
    private int nbCarroserie;

    public Stock() {
        this.nbRoues = 0;
        this.nbMoteur = 0;
        this.nbCarroserie = 0;
    }

    public int getNbRoues() {
        return nbRoues;
    }

    public void setNbRoues(int nbRoues) {
        this.nbRoues = nbRoues;
    }

    public int getNbMoteur() {
        return nbMoteur;
    }

    public void setNbMoteur(int nbMoteur) {
        this.nbMoteur = nbMoteur;
    }
    public int getNbCarroserie() {
        return nbCarroserie;
    }
    public void setNbCarroserie(int nbCarroserie) {
        this.nbCarroserie = nbCarroserie;
    }
    
}
