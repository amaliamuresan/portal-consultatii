import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class MyPatients {
    public static void myPatientsScene(Stage window){
        Label label=new Label("Urmatoarele persoane sunt internate la dumneavoastra:");
        ListView<String> listView=new ListView<>();
        Button mainpg=new Button("Pagina principala");
        Button extern=new Button("Externeaza");

        listView.setMaxWidth(250);

        HBox hBox=new HBox();
        hBox.getChildren().addAll(mainpg,extern);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        hBox.setMinHeight(50);

        VBox vBox=new VBox();
        vBox.getChildren().addAll(label,listView,hBox);
        vBox.setSpacing(10);
        vBox.setMinWidth(400);
        vBox.setAlignment(Pos.CENTER);

        Scene scene=new Scene(vBox);
        window.setScene(scene);

        MyPatients.updatePatientsList(listView);

        mainpg.setOnAction(e -> {
                try {
                    MedicMainPage.Init(window);
                }catch (IOException ioException) {
                    ioException.printStackTrace();
                }
        });
    }

    private static void updatePatientsList(ListView<String> listView){
        String filename = "Users/" + LogIn.loggedUser.getUsername() + ".json";
        try {
            JSONObject jsonObject= MainPacient.parseJSONFile(filename);
            JSONArray pacienti=jsonObject.getJSONArray("Pacienti");
            for (int i=0;i<pacienti.length();i++)
                listView.getItems().add(pacienti.getString(i));
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
