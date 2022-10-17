import java . sql .*;
public class TestConnexion {
    public static void main ( String [] args ) {
        ConnexionMySQL co ;
        String loginMySQL = "saada" ;
        String mdpMySQL = "saada" ;
        String nomServeur = "localhost" ; // localhost
        String nomBase = "DB" + loginMySQL ;

        co = new ConnexionMySQL ( nomServeur , nomBase , loginMySQL , mdpMySQL ) ;

        if ( co.getConnecte () ) {
            System.out . println ( " La connexion c ’ est bien pass  ́e e " ) ;
        EleveBD eleve = new EleveBD ( co ) ;
        System.out.println(eleve.getNombreMatiere());
        
        }
        else{
        System.out . println ( " La connexion `a votre BD ne n ’ est pas faite " ) ;
    }
}
}