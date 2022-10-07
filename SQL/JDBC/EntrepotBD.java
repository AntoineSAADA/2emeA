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

    public void listeEntrepotArticle(int idArticle){
        try{
            Connection c = connexion.getConnexion();
            Statement s = c.createStatement();
            int codeEntrepotActuelle = 0;
            String nomVille = "";
            int nbArticles = 0;
            String res = "";
            PreparedStatement ps = c.prepareStatement("select code,nom, quantite from ENTREPOT natural join STOCKER natural join ARTICLE where reference = ?");
            ps.setInt(1, idArticle);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                codeEntrepotActuelle = rs.getInt("code");
                nomVille = rs.getString("nom");
                nbArticles = rs.getInt("quantite");
                res += "Il y a " + nbArticles + " " + "articles dans l'entrepot " + codeEntrepotActuelle + " " + "à " + nomVille + "\n";
            }
            System.out.println(res);


       }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    public void listeArticleEntrepot(int idEntrepot){
        try{
            Connection c = connexion.getConnexion();
            Statement s = c.createStatement();
            int codeEntrepot = 0;
            int quantite = 0;
            String libelle = "";
            String res = "";
            PreparedStatement ps = c.prepareStatement("SELECT code,libelle, quantite FROM ENTREPOT natural join STOCKER natural join ARTICLE where code = ?;");
            ps.setInt(1, idEntrepot);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                codeEntrepot = rs.getInt("code");
                quantite = rs.getInt("quantite");
                libelle = rs.getString("libelle");
                res += "Il y a " + quantite +" "+ libelle + " " + "dans l'entrepot " + codeEntrepot + "\n";
            }
            System.out.println(res);

        }
        catch(SQLException e){
            System.out.println(e);
        }
    }


    public String valeurEntrepot(int idEntrepot){
        try{
            Connection c = connexion.getConnexion();
            Statement s = c.createStatement();
            int codeEntrepot = 0;
            float valeur = 0;
            String res = "";
            PreparedStatement ps = c.prepareStatement("SELECT code, (quantite*prix) valeur FROM ENTREPOT natural join STOCKER natural join ARTICLE where code = ? group by code;");
            ps.setInt(1, idEntrepot);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                codeEntrepot = rs.getInt("code");
                valeur = rs.getFloat("valeur");
                res += "La valeur de l'entrepot " + codeEntrepot + " est de " + valeur + " euros" + "\n";
            }
            return res;
        }
        catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }


    
    
}
