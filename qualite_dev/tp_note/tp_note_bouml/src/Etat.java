import java.util.ArrayList;

class Etat {
  private ArrayList<Transition> transitions ;

  public Etat(ArrayList<Transition> transitions) {
    this.transitions = transitions;
  }

  public boolean traiterMot(String mot) {
    if (mot.length() == 0) {
      return false;
    }
    char symbole = mot.charAt(0);
    String reste = mot.substring(1);
    for (Transition t : transitions) {
      if (t.getSymbole() == symbole) {
        if (t.getArrivee().traiterMot(reste)) {
          return true;
        }
      }
    }
    return false;
  } 

}
