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
            ResultSet rs = stmt.executeQuery("SELECT MAX(num) FROM article");
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
            PreparedStatement stmt = connexion.getConnexion().prepareStatement("SELECT * FROM article WHERE num = ?");
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM article");
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
}
