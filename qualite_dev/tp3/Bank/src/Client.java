import java.util.ArrayList;
import java.util.List;

public class Client {
  private String nom;

  private List<Compte> listeComptes;

  /**
   *  constructeur
   */
  public Client(String nom) {
    this.nom=nom;
    this.listeComptes = new ArrayList<Compte>();
  }

  /**
   *  getters/setters
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getNom() {
    return this.nom;
  }

  public void addCompte(Compte compte) {
    this.listeComptes.add(compte);
  }

  public void removeCompte(Compte compte) {
    this.listeComptes.remove(compte);
  }

  public List<Compte> getListeComptes() {
    return this.listeComptes;
  }

  /**
   *  opérations métier
   */
  public Compte getCompte(String numero) {
    for(Compte compte: this.listeComptes){
      if(numero.equals(compte.getNumero())) return compte;
    }
    return null;
  }

}
