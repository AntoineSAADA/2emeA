import java.util.*;

public class Compte {
  private float solde;

  private String numero;

  private float decouvertAutorise =   100;

  private List<Operations> historique;


  /**
   *  constructeur    
   */
  public Compte(String numero, float solde) {
        this.numero=numero;
        this.solde=solde;
        this.historique = new ArrayList<Operations>();
  }

  /**
   *  getters/setters
   */
  public void setSolde(float val) {
        this.solde = val;
  }

  public float getSolde() {
        return this.solde;
  }

  public void setNumero(String num) {
        this.numero = num;
  }

  public String getNumero() {
        return this.numero;
  }

  public void setDecouvertAutorise(float val) {
        this.decouvertAutorise = val;
  }

  public float getDecouvertAutorise() {
        return this.decouvertAutorise;
  }

  /**
   *  opérations métiers
   */
  public boolean debiter(float montant, String info) {
        if(montant>0){
            float res = this.solde-montant;
            if(res>=this.decouvertAutorise){ // vérification solde après opératioin
                this.solde = res;
                this.historique.add(new Operations(montant, new Date()));
                return true;

            }
        }
        return false;
  }

  public boolean crediter(float montant, String info) {
        if(montant>0){
            this.solde += montant;
            this.historique.add(new Operations(montant, new Date()));
            return true;
        }
        return false;
  }

  public String toString() {

        return "Compte n°"+this.numero+" : "+this.solde+" €";
  }


      public final List<Operations> getHistorique() {
            return historique;
      }






}
