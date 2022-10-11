import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * En-tête commune à plusieurs fenêtres
 */
public class Titre extends HBox{
    
    /**
     * 
     * @param titre le titre de l'application
     */
    public Titre(String titre){
        super();
        Text tTitre = new Text(titre);
        Font font = Font.loadFont("file:resources/CatalishHuntera.ttf", 50);
        tTitre.setFont(font);
        this.getChildren().addAll(tTitre);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(10));
    }


}
