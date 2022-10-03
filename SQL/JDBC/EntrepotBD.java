import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;

public class EntrepotBD {
    private ConnexionMySQL connexion;
    
    public EntrepotBD(ConnexionMySQL connexion) {
        this.connexion = connexion;
    }

    // Ecrire une fonction pour obtenir le plus grand num ́ero utilis ́e pour identifier un article
    public int getNumMaxArticle() {
        try {
            int max = 0;
            Statement stmt = connexion.getConnexion().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(reference) FROM ARTICLE");
            while (rs.next()) {
                max = rs.getInt(1);
            }
            rs.close();
            stmt.close();
            return max;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }            
    }
    public Article getArticle(int reference){
        try{
            Article article = null;
            PreparedStatement stmt = connexion.getConnexion().prepareStatement("SELECT * FROM ARTICLE WHERE reference = ?");
            stmt.setInt(1, reference);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                article = new Article(rs.getInt(1), rs.getString(2), rs.getFloat(3));
            }
            rs.close();
            stmt.close();
            return article;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public Article maxArticle(){
        return getArticle(getNumMaxArticle());
    }
    public ArrayList<Article> getListArticles(){
        try{
            ArrayList<Article> articles = new ArrayList<Article>();
            Statement stmt = connexion.getConnexion().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ARTICLE");
            while(rs.next()){
                articles.add(new Article(rs.getInt(1), rs.getString(2), rs.getFloat(3)));
            }
            rs.close();
            stmt.close();
            return articles;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    // Ecrire une procedure pour afficher tous les entrepots tries par departement avec pour chaque departement, le nombre d’entrepots qu’a le departement.
    
    public void entrepotsTriés(){
        try{
            Connection c = connexion.getConnexion();
            Statement s = c.createStatement();
            String departementActuelle = "";
            int nbEntrepots = 0;
            String res = "";
            ResultSet rs = s.executeQuery
            ("select code,nom,departement from ENTREPOT order by departement");
            while (rs.next()){
                if(!(departementActuelle.equals(rs.getString("departement")))){
                    if(departementActuelle != ""){
                        res += "Le département " + departementActuelle + " contient " + nbEntrepots + " " + "entrepots \n";}    

                    res += rs.getInt("code") + " " + rs.getString("nom") + " "
                    + rs.getString("departement") + " " + "\n"; 
                        nbEntrepots = 1;
                        departementActuelle = rs.getString("departement");
                }
                else{
                    res += rs.getInt("code") + " " + rs.getString("nom") + " "
                    + rs.getString("departement") + " " + "\n";
                    nbEntrepots ++;
                }
            }
            res += "Le département " + departementActuelle + " contient " + nbEntrepots + " " + "entrepots \n";
            System.out.println(res);
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
    }

    
}
