import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class ExecuteService {

    public static TextArea textArea = new TextArea();

    public static Stage window = new Stage();
    public static void init()
    {
        window.initModality(Modality.APPLICATION_MODAL);

        VBox vb = new VBox(15);


        Label raspunsLabel = new Label("Trimiteti un raspuns catre pacient");
        Button raspBtn = new Button("Trimite raspuns");
        window.setTitle("Executa serviciu");
        vb.setAlignment(Pos.BASELINE_CENTER);
        vb.getChildren().addAll(raspunsLabel, raspBtn);

        textArea.setPrefHeight(250);
        textArea.setPrefWidth(300);

        vb.getChildren().addAll(raspunsLabel, textArea, raspBtn);

        Scene scene=new Scene(vb,650,250);
        window.setScene(scene);
        window.showAndWait();


    }

    public static void executaCerere()
    {

    }
}
