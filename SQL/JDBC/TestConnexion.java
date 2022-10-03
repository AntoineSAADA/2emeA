import java . sql .*;
public class TestConnexion {
    public static void main ( String [] args ) {
        ConnexionMySQL co ;
        String loginMySQL = "saada" ;
        String mdpMySQL = "saada" ;
        String nomServeur = "localhost" ;
        String nomBase = "DB" + loginMySQL ;

        co = new ConnexionMySQL ( nomServeur , nomBase , loginMySQL , mdpMySQL ) ;

        if ( co.getConnecte () ) {
            System.out . println ( " La connexion c ’ est bien pass  ́e e " ) ;
        Connection connex = co.getConnexion () ;
        EntrepotBD entrepotBD = new EntrepotBD(co);
        System.out.println(entrepotBD.getNumMaxArticle());
        System.out.println(entrepotBD.getArticle(1));
        System.out.println(entrepotBD.maxArticle());
        System.out.println(entrepotBD.getListArticles());
        entrepotBD.entrepotsTriés();
        
        }
        else{
        System.out . println ( " La connexion `a votre BD ne n ’ est pas faite " ) ;
    }
}
}