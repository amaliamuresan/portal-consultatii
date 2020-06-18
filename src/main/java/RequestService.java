import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class RequestService {
    public static void promptChoice(){
        Stage window=new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Request");
        window.setMinWidth(250);

        Label label=new Label("Alegeti tipul de serviciu");
        Button consult,interpretare;
        consult=new Button("Consultatie");
        interpretare=new Button("Interpretare");

        VBox vbox=new VBox(20);
        vbox.getChildren().addAll(label,consult,interpretare);
        vbox.setAlignment(Pos.CENTER);

        Scene scene=new Scene(vbox,650,250);
        window.setScene(scene);
        window.show();
    }
}
