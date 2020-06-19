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
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class UpdatePrice {

     private static Button Salveaza;
     private static Alert alert;
    public static void startUpdatePrice(Stage window) {

        window.setTitle("Lista de Preturi");

        Salveaza = new Button("Salveaza");
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Introduceti un numar!");

        GridPane gridLayout=new GridPane();
        gridLayout.setPadding(new Insets(15, 15, 15, 15));
        gridLayout.setVgap(7);
        gridLayout.setHgap(1);
        gridLayout.setAlignment(Pos.BASELINE_CENTER);

        Label consultLabel = new Label("Pret Consultatie");
        Label interpetLabel = new Label("Pret Interpretare");
        TextField consultTF = new TextField();
        TextField interpretTF = new TextField();

        String filename = "Users/" + LogIn.loggedUser.getUsername() + ".json";
        try {
            JSONObject jsonObject = MainPacient.parseJSONFile(filename);
            JSONObject preturi = jsonObject.getJSONObject("preturi");
            consultTF.setText(preturi.getString("pret Consult"));
            interpretTF.setText(preturi.getString("pret Interpretare"));
        }catch(IOException exep){
            exep.printStackTrace();
        }

        gridLayout.setConstraints(consultLabel, 0, 1 );
        gridLayout.setConstraints(interpetLabel, 0, 2);
        gridLayout.setConstraints(consultTF, 1 , 1);
        gridLayout.setConstraints(interpretTF, 1, 2 );
        gridLayout.setConstraints(Salveaza,1,3);

        VBox layout=new VBox(20);
        gridLayout.getChildren().addAll(consultLabel,interpetLabel,Salveaza,consultTF,interpretTF);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(gridLayout,650,250);

        window.setScene(scene);
        window.show();

        Salveaza.setOnAction(e->{

            try{
                Integer.parseInt(consultTF.getText());
                JSONObject jsonObject = MainPacient.parseJSONFile(filename);
                JSONObject preturi = jsonObject.getJSONObject("preturi");

                preturi.put("pret Consult", consultTF.getText());
                preturi.put("pret Interpretare", interpretTF.getText());

                FileWriter file = new FileWriter(filename);
                file.write(jsonObject.toString());
                file.flush();

                MedicMainPage.Init(window);
            }catch(IOException exep){
                exep.printStackTrace();
            }catch(NumberFormatException e2){
                alert.show();
            }
        });

    }
}
