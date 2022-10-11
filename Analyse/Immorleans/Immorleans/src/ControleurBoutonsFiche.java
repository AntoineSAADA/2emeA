import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;
import javafx.event.ActionEvent ;


/**
 * Controleur des boutons (Enregistrer/Annuler) d'une fiche du répertoire
 */
public class ControleurBoutonsFiche implements EventHandler<ActionEvent>{
    /**
     * Vue principale de l'application
     */
    private AppliRepertoire appli;
    /**
     * Vue correspondant à la fiche
     */
    private FenetreFiche vue;
    /**
     * Modèle de l'application
     */
    private Repertoire modele;
    
    /**
     * @param vue vue correspondant à la fiche
     * @param modèle de l'application
     */
    public ControleurBoutonsFiche(FenetreFiche vue, Repertoire modele){
        this.appli = vue.getAppli();
        this.modele = modele;
        this.vue = vue;
    }
    
    @Override
    public void handle(ActionEvent event){
        Button bouton = (Button) event.getSource();
        if(bouton.getText().contains("Enregistrer")){
            if(this.vue.getModeAjout()){
                Employe employe = new Employe(vue.getTfvalNom().getText(), vue.getTfvalPrenom().getText(), vue.getTfvalEmail().getText(), vue.getTfvalDateRecrutement().getText());
                this.modele.add(employe);
                this.appli.majBoutonSauvegarde(true);
            }
            else{
                String info = this.modele.getEmployeCourant().getNom()+" "+this.modele.getEmployeCourant().getPrenom();
                Optional<ButtonType> reponse = this.vue.popUpConfirmerModifications(info).showAndWait();
                if (reponse.isPresent() && reponse.get().equals(ButtonType.YES)){
                    Employe employe = this.modele.getEmployeCourant();
                    employe.setNom(vue.getTfvalNom().getText());
                    employe.setPrenom(vue.getTfvalPrenom().getText());
                    employe.setEmail(vue.getTfvalEmail().getText());
                    employe.setDateRecrutement(vue.getTfvalDateRecrutement().getText());
                    this.appli.majBoutonSauvegarde(true);
                }
            }
            modele.init();
            appli.afficheFenetreRepertoire();
        }
        else if(bouton.getText().contains("Annuler")){
            modele.init();
            appli.afficheFenetreRepertoire();
        }

    }
}
