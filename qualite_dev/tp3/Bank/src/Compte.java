import java.util.*;

public abstract class Compte extends Observable {
  protected float solde;

  protected String numero;

  protected List<Operation> historique;

  /**
   *  constructeur    
   */
  public Compte(String numero, float solde) {
        this.numero=numero;
        this.solde=solde;
        this.historique = new ArrayList<Operation>();
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

  /**
   *  opérations métiers
   */
  public abstract boolean debiter(float montant, String info) ;

  public boolean crediter(float montant, String info) {
        if(montant>0){
            this.solde += montant;
            this.historique.add(0, new Operation(new Date(), montant));
            return true;
        }
        return false;
  }

  public void afficherHistorique() {
      System.out.println("----- Historique -----\n");
      for(Operation op:this.historique) System.out.println(op);
  }

}
