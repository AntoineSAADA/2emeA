import java.util.*;

public class Bank {
  public static void main(String[] args)
  {
    // gestion des comptes d'un seul client (Dupont)
    Client client = new Client("Dupont");

    // Menu principal
    int rep=-1;
    String s;
    Scanner scanner = new Scanner(System.in);
    while(rep!=0){
        // affichage du menu
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        System.out.println("========================\n     APPLI MyBANK       \n========================\n");
        System.out.println("1. Consulter un compte");
        System.out.println("2. Débiter un compte");
        System.out.println("3. Créditer un compte");
        System.out.println("4. Ajouter un compte");
        System.out.println("5. Afficher l'historique d'un compte");
        System.out.println("0. Quitter");

        // saisie du choix utilisateur
        s = scanner.nextLine();
        rep = Integer.parseInt(s);
        //System.out.println(rep);

        // interprétation du choix
        switch(rep){
              case 1: {
                  System.out.print("Numéro du compte à Consulter : ");
                  String numero = scanner.nextLine();
                  Compte compte = client.getCompte(numero);
                  System.out.println(compte);
                  break;
              }
              case 2: {
                  System.out.print("Numéro du compte à Débiter : ");
                  String numero = scanner.nextLine();
                  Compte compte = client.getCompte(numero);
                  if(compte != null){
                    System.out.print("Montant du débit : ");
                    float montant = scanner.nextFloat();
                    scanner.nextLine();
                    compte.debiter(montant,"débit");
                  }
                  else System.out.println("Compte inconnu!");
                  break;
              }
              case 3: {
                  System.out.print("Numéro du compte à Créditer : ");
                  String numero = scanner.nextLine();
                  Compte compte = client.getCompte(numero);
                  if(compte != null){
                    System.out.print("Montant du crédit : ");
                    float montant = scanner.nextFloat();
                    scanner.nextLine();
                    compte.crediter(montant,"crédit");
                  }
                  else System.out.println("Compte inconnu!");
                  break;
              }
              case 4: {
                  System.out.print("Numéro du compte à créer : ");
                  String numero = scanner.nextLine();
                  Compte compte = new Compte(numero, 0);
                  client.addCompte(compte);
                  break;
              }
              case 5: {
                
                  System.out.print("Numéro du compte à consulter : ");
                  String numero = scanner.nextLine();
                  if (client.getCompte(numero) != null) {
                    Compte compte = client.getCompte(numero);
                    System.out.println(compte.getHistorique());
                  }
                  else{
                    System.out.println("Compte inconnu!");
                    System.out.println(client.getCompte(numero).getHistorique());
                  }
                  break;
              }
            }
            System.out.println("\n(ENTRER pour continuer)");
            s = scanner.nextLine();
      }
      scanner.close();
  }

}
