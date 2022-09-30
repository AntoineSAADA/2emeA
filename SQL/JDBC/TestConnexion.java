import java . sql .*;
public class TestConnexion {
    public static void main ( String [] args ) {
        ConnexionMySQL co ;
        String loginMySQL = "saada" ;
        String mdpMySQL = "saada" ;
        String nomServeur = "servinfo-mariadb" ;
        String nomBase = "DB" + loginMySQL ;

        co = new ConnexionMySQL ( nomServeur , nomBase , loginMySQL , mdpMySQL ) ;

        if ( co . getConnecte () ) {
            System . out . println ( " La connexion c ’ est bien pass  ́e e " ) ;
        Connection connex = co . getConnexion () ;
        }
        else{
        System . out . println ( " La connexion `a votre BD ne n ’ est pas faite " ) ;
    }
}
}