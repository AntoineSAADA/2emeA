class CompteCourant
!!!130050.java!!!	CompteCourant(in numero : String, in solde : float)
            super(numero, solde);
!!!130178.java!!!	getDecouvertAutorise() : float
        return this.decouvertAutorise;
!!!130306.java!!!	setDecouvertAutorise(in val : float) : void
        this.decouvertAutorise = val;
!!!130434.java!!!	debiter(in montant : float, in info : String) : boolean
      if(montant>0){
          float res = this.solde-montant;
          if(res>=-this.decouvertAutorise){ // vérification solde après opératioin
              this.solde = res;
              this.historique.add(0, new Operation(new Date(), -montant));
              return true;
          }
      }
      return false;
!!!130562.java!!!	toString() : String
            String res = "\tCompte courant n.  : " + this.numero + "\n";
                   res +="\tDécouvert autorisé : " + this.decouvertAutorise + "\n"; 
                   res+= "\tSolde du compte    : " + this.solde + "\n";
            return  res;
