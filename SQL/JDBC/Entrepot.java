public class Entrepot {
    private int code;
    private String nom;
    private String departement;

    public Entrepot(int code, String nom, String departement) {
        this.code = code;
        this.nom = nom;
        this.departement = departement;
    }


    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDepartement() {
        return this.departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }


    @Override
    public String toString() {
        return "{" +
            " code='" + getCode() + "'" +
            ", nom='" + getNom() + "'" +
            ", departement='" + getDepartement() + "'" +
            "}";
    }

    
}
