
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Vue d'une menu principal
 */
public class FenetreMenu extends BorderPane {
    /**
     * Les trois boutons du menu principal
     */
    private Button bEmployes;       
    private Button bSauvegarder;       
    private Button bQuitter;

    /**
     * @param appli vue de l'application
     */
    public FenetreMenu(AppliRepertoire appli){
        super();
        this.bEmployes = appli.getBEmployes();
        this.bSauvegarder = appli.getBSauvegarder();
        this.bQuitter = appli.getBQuitter();

        VBox vbCenter = new VBox();
        vbCenter.setPrefWidth(300);
        vbCenter.setSpacing(22);
        vbCenter.setPadding(new Insets(22));
        this.bEmployes.setMinWidth(vbCenter.getPrefWidth());
        this.bSauvegarder.setMinWidth(vbCenter.getPrefWidth());
        this.bQuitter.setMinWidth(vbCenter.getPrefWidth());
        vbCenter.getChildren().addAll(this.bEmployes, this.bSauvegarder, this.bQuitter);
        vbCenter.setAlignment(Pos.CENTER);

        this.setTop(new Titre("Gestion ImmO'rléans"));
        this.setCenter(vbCenter);
    }

}
