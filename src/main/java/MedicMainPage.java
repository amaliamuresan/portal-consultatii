


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class MedicMainPage{


    public static Stage window;


    //@Override
    public static void Init(Stage window) {



        Label requestLabel = new Label("Lista cereri:");
        Button myPatientsBtn = new Button("Pacienti internati");
        Button updatePriceBtn = new Button("Actualizeaza preturi");
        Button suspendActivityB = new Button();



        window.setTitle("Main Page");

        updatePriceBtn.setPrefWidth(130);
        myPatientsBtn.setPrefWidth(130);

        GridPane gridLayout = new GridPane();
        gridLayout.setPadding(new Insets(15, 15, 15, 15));
        gridLayout.setVgap(7);
        gridLayout.setHgap(1);

        VBox vb = new VBox();
        VBox vb2 = new VBox();

        vb2.setPrefWidth(180);

        vb.setAlignment(Pos.BASELINE_CENTER);
        vb.setSpacing(7);

        vb2.setAlignment(Pos.BASELINE_CENTER);
        vb2.setSpacing(7);
        vb.setSpacing(7);

        vb2.setPadding(new Insets(10, 10, 10, 10));
        vb.setPadding(new Insets(10, 10, 10, 10));

        String filename = "Users/" + LogIn.loggedUser.getUsername() + ".json";
        try {
            JSONObject jsonObject = MainPacient.parseJSONFile(filename);
            if((int)jsonObject.get("Activitate") == 1) {
                suspendActivityB.setText("Suspenda Activitatea");
            }else {
                suspendActivityB.setText("Reia Activitatea");
            }

        }catch(IOException exp){
            exp.printStackTrace();
        }
        vb.getChildren().addAll(suspendActivityB, updatePriceBtn, myPatientsBtn);
        vb2.getChildren().addAll(requestLabel);

        gridLayout.setConstraints(vb, 0, 0);
        gridLayout.setConstraints(vb2, 1, 0);

        gridLayout.getChildren().addAll(vb2, vb);

        Scene sceneMain = new Scene(gridLayout, 450, 300);
        window.setScene(sceneMain);

        suspendActivityB.setOnAction(e-> {

            String fname = "Users/" + LogIn.loggedUser.getUsername() + ".json";
             try {
                 JSONObject jsonObject = MainPacient.parseJSONFile(fname);
                 if(suspendActivityB.getText().equals("Suspenda Activitatea")){
                 jsonObject.remove("Activitate");
                 jsonObject.put("Activitate", 0);
                 suspendActivityB.setText("Reia Activitatea");
                 }
                 else {
                     jsonObject.remove("Activitate");
                     jsonObject.put("Activitate", 1);
                     suspendActivityB.setText("Suspenda Activitatea");
                 }
                 FileWriter file = new FileWriter(fname);
                 file.write(jsonObject.toString());
                 file.flush();
             }catch(IOException exep){
                 exep.printStackTrace();
             }
        });

        updatePriceBtn.setOnAction(e-> UpdatePrice.startUpdatePrice(window));


        //window.show();


    }

    public static void setStage(Stage stage)
    {
        window = stage;
    }



}
