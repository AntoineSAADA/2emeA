import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;

public class EleveBD {
    private ConnexionMySQL connexion;
    
    public EleveBD(ConnexionMySQL connexion) {
        this.connexion = connexion;
    }  

    public int getNombreMatiere(){
        int nb = 0;
        try {
            Statement stmt = connexion.getConnexion().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(reference) FROM MATIERE");
            if (rs.next()) {
                nb = rs.getInt(1);
            }
            rs.close();
            stmt.close();
            return nb;
        } catch (SQLException e) {
            System.out.println("Erreur SQL: " + e.getMessage());
            return 0;
        }
    }
    
    
}
