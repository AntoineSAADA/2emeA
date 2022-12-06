import java.sql.Date;

class CompteCourant extends Compte {
  public CompteCourant(String numero, float solde) {
            super(numero, solde);
  }

  public float getDecouvertAutorise() {
        return this.decouvertAutorise;
  }

  public void setDecouvertAutorise(float val) {
        this.decouvertAutorise = val;
  }

  private float decouvertAutorise =    100;

  public boolean debiter(float montant, String info) {
      if(montant>0){
          float res = this.solde-montant;
          if(res>=-this.decouvertAutorise){ // vérification solde après opératioin
              this.solde = res;
              this.historique.add(0, new Operation(new Date(), -montant));
              return true;
          }
      }
      return false;
  }

  public String toString() {
            String res = "\tCompte courant n.  : " + this.numero + "\n";
                   res +="\tDécouvert autorisé : " + this.decouvertAutorise + "\n"; 
                   res+= "\tSolde du compte    : " + this.solde + "\n";
            return  res;
  }

}
