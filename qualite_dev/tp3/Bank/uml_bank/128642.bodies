class CompteDepot
!!!130690.java!!!	CompteDepot(in numero : String, in solde : float)
    super(numero, solde);
!!!130818.java!!!	debiter(in montant : float, in info : String) : boolean
    this.solde = this.solde - montant;
    this.historique.add(0, new Operation(new Date(), -montant));
    return true;
!!!130946.java!!!	getTauxInteret() : double
    return tauxInteret;
!!!131074.java!!!	setTauxInteret(in value : double) : void
    tauxInteret = value;
!!!131202.java!!!	toString() : String
    String res = "\tCompte de dépôt n. : " + this.numero + "\n";
           res +="\tTaux d'intérêt     : " + CompteDepot.tauxInteret + "\n"; 
           res+= "\tSolde du compte    : " + this.solde + "\n";
    return  res;
