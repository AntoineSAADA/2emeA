import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * Controleur des événements de la souris sur la liste des employés dans le fenêtre du répertoire
 */
public class ControleurListe implements EventHandler<MouseEvent>{
    
    /**
     * Vue correspondant au répertoire
     */
    private FenetreRepertoire fenetre;
    /**
     * Modèle de l'application
     */
    private Repertoire modele;
    
    /**
     * 
     * @param fenetre vue correspondant au répertoire
     * @param modele modèle de l'application
     */
    public ControleurListe(FenetreRepertoire fenetre, Repertoire modele){
        this.fenetre = fenetre;
        this.modele = modele;
    }
    
    @Override
    public void handle(MouseEvent event){
        @SuppressWarnings("unchecked")
        ListView<Employe> liste = (ListView<Employe>) event.getSource();
        this.modele.setIndex(liste.getSelectionModel().getSelectedIndex());
        this.fenetre.majFiche();
        
    }
}
