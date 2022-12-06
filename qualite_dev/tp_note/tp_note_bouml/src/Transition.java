
class Transition {
  private char symbole;
  private Etat arrivee;
  private Etat depart;

  public Transition(char symbole, Etat arrivee, Etat depart) {
    this.symbole = symbole;
    this.arrivee = arrivee;
    this.depart = depart;
  }

  public char getSymbole() {
    return symbole;
  }

  public Etat getArrivee() {
    return arrivee;
  }

  public Etat getDepart() {
    return depart;
  }


}
