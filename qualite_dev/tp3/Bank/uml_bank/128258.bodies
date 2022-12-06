class Compte
!!!129026.java!!!	Compte(in numero : String, in solde : float)
        this.numero=numero;
        this.solde=solde;
        this.historique = new ArrayList<Operation>();
!!!129154.java!!!	setSolde(in val : float) : void
        this.solde = val;
!!!129282.java!!!	getSolde() : float
        return this.solde;
!!!129410.java!!!	setNumero(in num : String) : void
        this.numero = num;
!!!129538.java!!!	getNumero() : String
        return this.numero;
!!!129794.java!!!	crediter(in montant : float, in info : String) : boolean
        if(montant>0){
            this.solde += montant;
            this.historique.add(0, new Operation(new Date(), montant));
            return true;
        }
        return false;
!!!129922.java!!!	afficherHistorique() : void
      System.out.println("----- Historique -----\n");
      for(Operation op:this.historique) System.out.println(op);
