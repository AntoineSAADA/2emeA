
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * Vue du répertoire
 */
public class FenetreRepertoire extends BorderPane {

    /**
     * 2 boutons de gestion du répertoire
     */
    private Button bRetour;
    private Button bSauvegarder;
    /**
     * Vue de l'application
     */
    private AppliRepertoire appli;
    /**
     * Modèle de l'application
     */
    private Repertoire modele;
    /** 
     * Etiquettes des champs d'Employé
     */  
    private Label lvalNom;
    private Label lvalPrenom;
    private Label lvalEmail;
    private Label lvalDateRecrutement;


    /**
     * 
     * @param appli vue de l'application
     * @param modele modèle de l'application
     */
    public FenetreRepertoire(AppliRepertoire appli, Repertoire modele){
        super();
        this.appli = appli;
        this.modele = modele;
        this.bRetour = appli.getBRetour();
        this.bSauvegarder = appli.getBSauvegarder();

        // liste des employés (à gauche)
        VBox vbListe = new VBox();
        ListView<Employe> listeEmployes = new ListView<>();
        listeEmployes.getItems().addAll(this.modele);
        vbListe.getChildren().addAll(listeEmployes);
        vbListe.setPadding(new Insets(10));
        listeEmployes.prefHeightProperty().bind(vbListe.heightProperty());
        listeEmployes.setOnMouseClicked(new ControleurListe(this, this.modele));
    

        // fiche employé
        BorderPane bpCenter = new BorderPane();

        // fiche employé (top)
        HBox hbTop = new HBox();
        Label lFiche = new Label("Fiche détaillée :");
        hbTop.getChildren().addAll(lFiche);

        // fiche employé (center)
        GridPane gpFiche = new GridPane();
        gpFiche.setVgap(5);gpFiche.setHgap(5);gpFiche.setPadding(new Insets(10));
        Label lNom = new Label("Nom");this.lvalNom = new Label("");
        Label lPrenom = new Label("Prénom");this.lvalPrenom = new Label("");
        Label lEmail = new Label("Email");this.lvalEmail = new Label("");
        Label lDateRecrutement = new Label("Date recrut.");this.lvalDateRecrutement = new Label("");
        gpFiche.add(lNom,0,2);gpFiche.add(lvalNom,1,2);
        gpFiche.add(lPrenom,0,3);gpFiche.add(lvalPrenom,1,3);
        gpFiche.add(lEmail,0,4);gpFiche.add(lvalEmail,1,4);
        gpFiche.add(lDateRecrutement,0,5);gpFiche.add(lvalDateRecrutement,1,5);

        // fiche employé (bottom)
        HBox hbBoutonsGestion = new HBox();
        hbBoutonsGestion.setId("petitsboutons");
        Button bAjouter = new Button("Ajouter");
        Button bModifier = new Button("Modifier");
        Button bSupprimer = new Button("Supprimer");
        ControleurBoutonsRepertoire controleurFiche = new ControleurBoutonsRepertoire(this, this.modele);
        bAjouter.setOnAction(controleurFiche);
        bModifier.setOnAction(controleurFiche);
        bSupprimer.setOnAction(controleurFiche);
        hbBoutonsGestion.getChildren().addAll(bAjouter,bModifier,bSupprimer);
        hbBoutonsGestion.setAlignment(Pos.BASELINE_RIGHT);

        bpCenter.setTop(hbTop);
        bpCenter.setCenter(gpFiche);
        bpCenter.setBottom(hbBoutonsGestion);
        bpCenter.setPadding(new Insets(10));


        // pied de formulaire
        HBox hbPied = new HBox();
        hbPied.getChildren().addAll(this.bSauvegarder, this.bRetour);
        hbPied.setAlignment(Pos.BOTTOM_RIGHT);
        hbPied.setPadding(new Insets(10));
        
        // composition du formulaire
        this.setTop(new Titre("Gestion ImmO'rléans"));
        this.setLeft(vbListe);
        this.setCenter(bpCenter);
        this.setBottom(hbPied);

        this.majFiche();
    }

    /**
     * 
     * @return la vue de l'application
     */
    public AppliRepertoire getAppli(){
        return this.appli;
    }

    /**
     * Met à jour la fiche Employé affichée
     */
    public void majFiche(){
        if(this.modele.getIndex()!=-1){
            Employe employe = this.modele.getEmployeCourant();
            this.lvalNom.setText(employe.getPrenom());
            this.lvalPrenom.setText(employe.getPrenom());
            this.lvalEmail.setText(employe.getEmail());
            this.lvalDateRecrutement.setText(employe.getDateRecrutement());
        }
    }

    /**
     * 
     * @param info une chaîne de caractère contenant le nom de l'employé concerné
     * @return une fenêtre popUp de confirmation de suppression
     */
    public Alert popUpConfirmerSuppression(String info){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Vous allez supprimer la fiche '"+info+"'. Voulez-vous confirmer?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Attention");
        return alert;
    }

}
