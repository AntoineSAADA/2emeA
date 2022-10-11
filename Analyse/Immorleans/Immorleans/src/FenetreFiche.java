
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Vue d'une fiche Employé 
 */
public class FenetreFiche extends BorderPane {
    /**
     * Mode d'ouverture de la fiche (mode Ajout = champs vides)
     */
    private boolean modeAjout;
    /**
     * Vue du menu principal de l'application
     */
    private AppliRepertoire appli;
    /**
     * Modèle de l'application
     */
    private Repertoire modele;

    private TextField tfvalNom;
    
    private TextField tfvalPrenom;
    
    private TextField tfvalEmail;
    
    private TextField tfvalDateRecrutement;

    /**
     * 
     * @param appli vue du menu principal de l'application
     * @param modele modèle de l'application
     * @param modeAjout mode d'ouverture de la fiche
     */
    public FenetreFiche(AppliRepertoire appli, Repertoire modele, boolean modeAjout){
        super();
        this.appli = appli;
        this.modele = modele;
        this.modeAjout = modeAjout;
    
        // fiche employé
        BorderPane bpCenter = new BorderPane();

        // fiche employé (top)
        HBox hbTop = new HBox();
        Label lFiche = new Label("Création de fiche  :");
        hbTop.setAlignment(Pos.CENTER);
        hbTop.getChildren().addAll(lFiche);

        // fiche employé (center)
        GridPane gpFiche = new GridPane();
        gpFiche.setVgap(5);gpFiche.setHgap(5);gpFiche.setPadding(new Insets(10));
        Label lNom = new Label("Nom");this.tfvalNom = new TextField("");
        Label lPrenom = new Label("Prénom");this.tfvalPrenom = new TextField("");
        Label lEmail = new Label("Email");this.tfvalEmail = new TextField("");
        Label lDateRecrutement = new Label("Date recrut.");this.tfvalDateRecrutement = new TextField("");
        gpFiche.add(lNom,0,2);gpFiche.add(tfvalNom,1,2);
        gpFiche.add(lPrenom,0,3);gpFiche.add(tfvalPrenom,1,3);
        gpFiche.add(lEmail,0,4);gpFiche.add(tfvalEmail,1,4);
        gpFiche.add(lDateRecrutement,0,5);gpFiche.add(tfvalDateRecrutement,1,5);
        gpFiche.setAlignment(Pos.CENTER);

        // adaptation aux données
        int index = this.modele.getIndex();
        if(!modeAjout && index>-1){
            lFiche.setText("Modificaton de fiche ("+index+")");
            this.tfvalNom.setText(this.modele.getEmployeCourant().getNom());
            this.tfvalPrenom.setText(this.modele.getEmployeCourant().getPrenom());
            this.tfvalEmail.setText(this.modele.getEmployeCourant().getEmail());
            this.tfvalDateRecrutement.setText(this.modele.getEmployeCourant().getDateRecrutement());
        }

        // fiche employé (bottom)
        HBox hbBoutonsGestion = new HBox();
        hbBoutonsGestion.setId("petitsboutons");
        Button bEnregistrer = new Button("Enregistrer");
        Button bAnnuler = new Button("Annuler");
        ControleurBoutonsFiche controleurFiche = new ControleurBoutonsFiche(this, this.modele);
        bEnregistrer.setOnAction(controleurFiche);
        bAnnuler.setOnAction(controleurFiche);
        hbBoutonsGestion.getChildren().addAll(bEnregistrer,bAnnuler);
        hbBoutonsGestion.setAlignment(Pos.BASELINE_CENTER);

        bpCenter.setTop(hbTop);
        bpCenter.setCenter(gpFiche);
        bpCenter.setBottom(hbBoutonsGestion);
        bpCenter.setPadding(new Insets(10));
        
        // composition du formulaire
        this.setTop(new Titre("Gestion ImmO'rléans"));
        this.setCenter(bpCenter);
    }

    /**
     * @return le mode d'ouverture
     */
    public boolean getModeAjout(){
        return this.modeAjout;
    }

    /**
     * 
     * @return la vue du menu principal de l'application
     */
    public AppliRepertoire getAppli(){
        return this.appli;
    }

    /**
     * 
     * @return le contenu du champ nom
     */
    public TextField getTfvalNom(){
        return this.tfvalNom;
    }

    /**
     * 
     * @return le contenu du champ prenom
     */
    public TextField getTfvalPrenom(){
        return this.tfvalPrenom;
    }

    /**
     * 
     * @return le contenu du champ email
     */
    public TextField getTfvalEmail(){
        return this.tfvalEmail;
    }

    /**
     * 
     * @return le contenu du champ dateRecrutement
     */
    public TextField getTfvalDateRecrutement(){
        return this.tfvalDateRecrutement;
    }

    /**
     * 
     * @param info une chaîne de caractère contenant le nom de l'employé concerné
     * @return une fenêtre popUp de confirmation de modification
     */
    public Alert popUpConfirmerModifications(String info){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Vous allez modifier la fiche '"+info+"'. Voulez-vous confirmer?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Attention");
        return alert;
    }
}
