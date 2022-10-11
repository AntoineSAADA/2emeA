import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

/**
 * Controleur pour la fermeture de la fenêtre de l'application
 */
public class ControleurFermeture implements EventHandler<WindowEvent>{
    /**
     * Vue de l'application
     */
    private AppliRepertoire appli;

    /**
     * 
     * @param appli vue de l'application
     */
    public ControleurFermeture(AppliRepertoire appli){
        this.appli = appli;
    }
    
    @Override
    public void handle(WindowEvent event){
        this.appli.sauvegarderRepertoire();
    }
}
