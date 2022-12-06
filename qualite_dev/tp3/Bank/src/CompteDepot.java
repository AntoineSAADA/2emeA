import java.sql.Date;

class CompteDepot extends Compte {
  private static double tauxInteret =  0.005;

  public CompteDepot(String numero, float solde) {
    super(numero, solde);
  }

  public boolean debiter(float montant, String info) {
    this.solde = this.solde - montant;
    this.historique.add(0, new Operation(new Date(), -montant));
    return true;
  }

  public final static double getTauxInteret()
  {
    return tauxInteret;
  }

  public static void setTauxInteret(double value)
  {
    tauxInteret = value;
  }

  public String toString() {
    String res = "\tCompte de dépôt n. : " + this.numero + "\n";
           res +="\tTaux d'intérêt     : " + CompteDepot.tauxInteret + "\n"; 
           res+= "\tSolde du compte    : " + this.solde + "\n";
    return  res;
  }

}
