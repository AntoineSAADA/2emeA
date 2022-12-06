import java.util.*;

class Operations {
  private Date date;
  private float montant;

  public Operations(float montant, Date date) {
    this.date = date;
    this.montant = montant;
  }
  

  public final Date getDate() {
    return date;
  }

  public void setDate(Date value) {
    date = value;
  }


  public final float getMontant() {
    return montant;
  }

  public void setMontant(float value) {
    montant = value;
  }

  @Override
  public String toString() {
    return "Operations{" + "date=" + date + ", montant=" + montant + '}';
  }


}
