class Automate {
  private Etat etatInitial;

  public Automate(Etat etatInitial) {
    this.etatInitial = etatInitial;
  }

  public boolean analyseMot(String mot) {
    System.out.println(etatInitial.traiterMot(mot));
    return etatInitial.traiterMot(mot);
  }


}
