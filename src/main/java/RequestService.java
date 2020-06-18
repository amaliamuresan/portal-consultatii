import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;

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

        consult.setOnAction(e-> RequestService.consultScene(window));
    }

    private static void consultScene(Stage window){
        Label label=new Label("Descrieti simptomele dumneavoastra in caseta text de mai jos");
        TextArea text=new TextArea();
        Button submit=new Button("Submit");

        text.setMinHeight(100);
        text.setMaxWidth(300);

        VBox vb=new VBox(20);
        vb.getChildren().addAll(label,text,submit);
        vb.setAlignment(Pos.CENTER);

        Scene scene=new Scene(vb,600,250);
        window.setScene(scene);

        submit.setOnAction(e-> {
            try {
                RequestService.cereConsultatie();
                window.close();
            }catch (IOException exception){
                exception.printStackTrace();
            }
        });
    }

    private static void cereConsultatie() throws IOException {
        Main.updateUsers();

    }
}
