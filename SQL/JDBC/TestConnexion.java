import java . sql .*;
public class TestConnexion {
    public static void main ( String [] args ) {
        ConnexionMySQL co ;
        String loginMySQL = "saada" ;
        String mdpMySQL = "saada" ;
        String nomServeur = "servinfo-mariadb" ; // localhost
        String nomBase = "DB" + loginMySQL ;

        co = new ConnexionMySQL ( nomServeur , nomBase , loginMySQL , mdpMySQL ) ;

        if ( co.getConnecte () ) {
            System.out . println ( " La connexion c ’ est bien pass  ́e e " ) ;
        Connection connex = co.getConnexion () ;
        EntrepotBD entrepotBD = new EntrepotBD(co);
        // 1
        // System.out.println(entrepotBD.getNumMaxArticle());
        // // 2
        // System.out.println(entrepotBD.getArticle(1));
        // // 3
        // System.out.println(entrepotBD.maxArticle());
        // // 4
        // System.out.println(entrepotBD.getListArticles());
        // // 5
        // entrepotBD.entrepotsTriés();
        // // 6
        // entrepotBD.listeEntrepotArticle(123);
        entrepotBD.listeArticleEntrepot(1);    
        System.out.println(entrepotBD.valeurEntrepot(1));  
        }
        else{
        System.out . println ( " La connexion `a votre BD ne n ’ est pas faite " ) ;
    }
}
}