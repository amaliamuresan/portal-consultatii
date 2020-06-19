import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdatePrice {

     static Button Salveaza;
    public static void start(Stage window) throws Exception {

        window.setTitle("Lista de Preturi");

        GridPane gridLayout=new GridPane();
        gridLayout.setPadding(new Insets(15, 15, 15, 15));
        gridLayout.setVgap(7);
        gridLayout.setHgap(1);
        gridLayout.setAlignment(Pos.BASELINE_CENTER);

        Label consultLabel = new Label("Pret Consultatie");
        Label interpetLabel = new Label("Pret Interpretare");
        TextField consultTF = new TextField();
        TextField interpretTF = new TextField();
        consultTF.setPromptText("Seteaza noul pret.");
        interpretTF.setPromptText("Seteaza noul pret.");

        gridLayout.setConstraints(consultLabel, 0, 1 );
        gridLayout.setConstraints(interpetLabel, 0, 2);
        gridLayout.setConstraints(consultTF, 1 , 1);
        gridLayout.setConstraints(interpretTF, 1, 2 );
        gridLayout.setConstraints(Salveaza,0,3);

        VBox layout=new VBox(20);
        gridLayout.getChildren().addAll(consultLabel,interpetLabel,Salveaza,consultTF,interpretTF);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(gridLayout,650,250);

        window.setScene(scene);
        window.show();


    }
}
