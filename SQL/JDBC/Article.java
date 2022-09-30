public class Article {
    private int reference;
    private String libelle;
    private float prix;

    public Article(int reference, String libelle, float prix) {
        this.reference = reference;
        this.libelle = libelle;
        this.prix = prix;
    }

    public int getReference() {
        return this.reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public float getPrix() {
        return this.prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }


    @Override
    public String toString() {
        return "{" +
            " reference='" + getReference() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", prix='" + getPrix() + "'" +
            "}";
    }


}
