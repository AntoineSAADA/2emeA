class Client
!!!128130.java!!!	Client(in nom : String)
    this.nom=nom;
    this.listeComptes = new ArrayList<Compte>();
!!!128258.java!!!	setNom(in nom : String) : void
    this.nom = nom;
!!!128386.java!!!	getNom() : String
    return this.nom;
!!!128514.java!!!	addCompte(inout compte : Compte) : void
    this.listeComptes.add(compte);
!!!128642.java!!!	removeCompte(inout compte : Compte) : void
    this.listeComptes.remove(compte);
!!!128770.java!!!	getListeComptes() : Compte
    return this.listeComptes;
!!!128898.java!!!	getCompte(in numero : String) : Compte
    for(Compte compte: this.listeComptes){
      if(numero.equals(compte.getNumero())) return compte;
    }
    return null;
