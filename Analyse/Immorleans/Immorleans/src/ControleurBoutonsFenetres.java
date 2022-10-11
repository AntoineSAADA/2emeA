import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.event.ActionEvent ;

/**
 * Controleur des boutons du menu principal permettant le changement de fenêtres
 */
public class ControleurBoutonsFenetres implements EventHandler<ActionEvent>{
    
    /**
     * Vue de l'application
     */
    private AppliRepertoire appli;

    /**
     * Modèle de l'application
     */
    private Repertoire modele;
    

    /**
     * @param appli vue de l'application
     * @param modele modèle de l'application
     */
    public ControleurBoutonsFenetres(AppliRepertoire appli, Repertoire modele){
        this.appli = appli;
        this.modele = modele;
    }
    
    @Override
    public void handle(ActionEvent event){
        Button bouton = (Button) event.getSource();
        if(bouton.getText().contains("Retour")){
            appli.afficheFenetreMenu();
        }
        else if(bouton.getText().contains("Employés")){
            modele.init();
            this.appli.afficheFenetreRepertoire();
        }
        else if(bouton.getText().contains("Sauvegarder")){
            this.appli.sauvegarderRepertoire();
            this.appli.majBoutonSauvegarde(false);
        }
        else if(bouton.getText().contains("Quitter")){
            this.appli.sauvegarderRepertoire();
            Platform.exit();
        }
    }
}
