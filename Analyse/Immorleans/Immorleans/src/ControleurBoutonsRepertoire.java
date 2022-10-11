import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

import javafx.event.ActionEvent ;


/**
 * Controleur des boutons (Ajouter/Modifier/Supprimer) du répertoire
 */
public class ControleurBoutonsRepertoire implements EventHandler<ActionEvent>{
    /**
     * Vue principale de l'application
     */
    private AppliRepertoire appli;
    /**
     * Vue correspondant au répertoire
     */
    private FenetreRepertoire vue;
    /**
     * Modèle de l'application
     */
    private Repertoire modele;

    /**
     * @param vue vue correspondant au répertoire
     * @param modele modèle de l'application
     */
    public ControleurBoutonsRepertoire(FenetreRepertoire vue, Repertoire modele){
        this.appli = vue.getAppli();
        this.vue = vue;
        this.modele = modele;
    }
    
    @Override
    public void handle(ActionEvent event){
        Button bouton = (Button) event.getSource();
        if(bouton.getText().contains("Ajouter")){
            appli.afficheFenetreFiche(true);
        }
        else if(bouton.getText().contains("Modifier") && this.modele.getIndex()!=-1){
            appli.afficheFenetreFiche(false);
        }
        else if(bouton.getText().contains("Supprimer") && this.modele.getIndex()!=-1){
            String info = this.modele.getEmployeCourant().getNom()+" "+this.modele.getEmployeCourant().getPrenom();
            Optional<ButtonType> reponse = this.vue.popUpConfirmerSuppression(info).showAndWait();
            if (reponse.isPresent() && reponse.get().equals(ButtonType.YES)){
                this.modele.remove(this.modele.getIndex());
                this.appli.majBoutonSauvegarde(true);
            }
            modele.init();
            appli.afficheFenetreRepertoire();
        }

    }
}
