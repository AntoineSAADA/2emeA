public class Stocker {
    private int reference;
    private int code;
    private int quantite;

    public Stocker(int reference, int code, int quantite) {
        this.reference = reference;
        this.code = code;
        this.quantite = quantite;
    }

    public int getReference() {
        return this.reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getQuantite() {
        return this.quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "{" +
            " reference='" + getReference() + "'" +
            ", code='" + getCode() + "'" +
            ", quantite='" + getQuantite() + "'" +
            "}";
    }

    
}
