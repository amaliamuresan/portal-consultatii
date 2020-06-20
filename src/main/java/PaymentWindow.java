import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class PaymentWindow {

    private static Button saveB;
    private static Alert alert;

    public static void startPayment(Stage window){

        window.setTitle("Plata Serviciilor");

        saveB = new Button("Plateste");
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Cel putin un camp a fost completat incorect!");

        GridPane gridLayout=new GridPane();
        gridLayout.setPadding(new Insets(15, 15, 15, 15));
        gridLayout.setVgap(7);
        gridLayout.setHgap(1);
        gridLayout.setAlignment(Pos.BASELINE_CENTER);

        Label numarCardLabel = new Label("Numar Card:");
        Label cvvLabel = new Label("CVV:");
        Label dataExpLabel = new Label("Data Expirarii:");
        Label anuntLabel = new Label("Aveti de achitat contravaloarea serviciilor.");
        Label anuntbLabel = new Label("Inchiderea ferestrei reprezinta efectuarea cu succes a platii");
        TextField numarCardTF = new TextField();
        TextField cvvTF = new TextField();
        TextField dataExpTF = new TextField();

        gridLayout.setConstraints(numarCardLabel, 0, 1 );
        gridLayout.setConstraints(cvvLabel, 0, 2);
        gridLayout.setConstraints(dataExpLabel,0,3);
        gridLayout.setConstraints(anuntLabel,0,0);
        gridLayout.setConstraints(anuntbLabel,0,4);
        gridLayout.setConstraints(numarCardTF, 1 , 1);
        gridLayout.setConstraints(cvvTF, 1, 2 );
        gridLayout.setConstraints(dataExpTF,1,3);
        gridLayout.setConstraints(saveB,1,4);

        VBox layout=new VBox(20);
        gridLayout.getChildren().addAll(numarCardLabel,cvvLabel,dataExpLabel,anuntLabel,anuntbLabel,saveB,numarCardTF,cvvTF,dataExpTF);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(gridLayout,750,350);

        window.setScene(scene);
        window.show();

        saveB.setOnAction(e-> {

            try{
                Integer.parseInt(numarCardTF.getText());
                Integer.parseInt(cvvTF.getText());
                Integer.parseInt(dataExpTF.getText());

               window.close();

            }catch(NumberFormatException exep){
                alert.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

    }
}
