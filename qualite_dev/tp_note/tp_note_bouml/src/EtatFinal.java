import java.util.ArrayList;

class EtatFinal extends Etat {
  public EtatFinal(ArrayList<Transition> transitions) {
    super(transitions);
  }

  public boolean traiterMot(String mot) {
    if (mot.length() == 0) {
      return true;
    }
    return false;
  }

}
