import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * Vue de l'application immorleans
 */
public class AppliRepertoire extends Application {
    
    private final String nomFichier = "data/RepertoireEmployes_ImmOrleans.csv";
    private Button bEmployes;
    private Button bRetour;
    private Button bSauvegarder;
    private Button bQuitter;
    private Scene scene;
    private Repertoire modele;
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
    
    /**
     * Renvoie la référence du bouton "Employés" du menu
     * @return le bouton "Employés"
     */
    public Button getBEmployes(){
        return this.bEmployes;
    }
    /**
    * Renvoie la référence au bouton "Retour" du menu
    * @return le bouton "Retour"
    */
    public Button getBRetour(){
        return this.bRetour;
    }

    /**
     * Renvoie la référence au bouton "Sauvegarder" du menu
     * @return le bouton "Sauvegarder"
     */
    public Button getBSauvegarder(){
        return this.bSauvegarder;
    }

    /**
     * Renvoie la référence au bouton "Quitter" du menu
     * @return le bouton "Quitter"
     */
    public Button getBQuitter(){
        return this.bQuitter;
    }

    /**
     * initialise les attributs (crée et charge les données du modèle, initialise les boutons)
     */
    @Override
    public void init(){
        this.modele = new Repertoire(this.nomFichier);
        ControleurBoutonsFenetres controleurFenetres = new ControleurBoutonsFenetres(this, this.modele);
        this.bEmployes = new Button("Employés");
        this.bEmployes.setOnAction(controleurFenetres);        
        this.bRetour = new Button("Retour");
        this.bRetour.setOnAction(controleurFenetres);
        this.bSauvegarder = new Button("Sauvegarder");
        this.majBoutonSauvegarde(false);
        this.bSauvegarder.setOnAction(controleurFenetres);
        this.bQuitter = new Button("Quitter");
        this.bQuitter.setOnAction(controleurFenetres);
    }
    
    /**
     * crée le graphe de scène, définit le controleur sur fermeture et lance l'application
     * @param stage la fenêtre principale
     */
    @Override
    public void start(Stage stage) {
        Pane root = new FenetreMenu(this);
        
        this.scene = new Scene(root, 900, 700);
        scene.getStylesheets().add("DarkTheme.css");
        //scene.getStylesheets().add(getClass().getResource("../resources/DarkTheme.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("ImmO'rléans");
        stage.setOnCloseRequest(new ControleurFermeture(this));
        stage.show();
    }
 
    /**
     * ferme l'application
     */
    public void quitter(){
        Platform.exit();
    }

    /**
     * affiche la fenêtre de Menu principal
     */
    public void afficheFenetreMenu(){
        Pane root = new FenetreMenu(this);
        this.scene.setRoot(root);
    }
    
    /**
     * affiche la fenêtre du répertoire
     */
    public void afficheFenetreRepertoire(){
        Pane root = new FenetreRepertoire(this, this.modele);    
        this.scene.setRoot(root);
    }

    /**
     * affiche une fiche
     * @param modeAjout le mode d'ouverture
     */
    public void afficheFenetreFiche(boolean modeAjout){
        Pane root = new FenetreFiche(this, this.modele, modeAjout);    
        this.scene.setRoot(root);
    }

    /**
     * sauvegarde les données du modèle (repertoire) dans un fichier
     */
    public void sauvegarderRepertoire(){
        this.modele.sauvegarderRepertoire(this.nomFichier);
    }

    /**
     * met à jour le bouton de sauvegarde (actif ou inactif)
     * @param actif le mode actif ou non du bouton
     */
    public void majBoutonSauvegarde(boolean actif){
        this.bSauvegarder.setDisable(!actif);
    }
}
