


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class MedicMainPage{


    public static Stage window;
    public static String numePacient;
    public static String cererePacient;


    //@Override
    public static void Init(Stage window) throws IOException {



        Label requestLabel = new Label("Lista cereri:");
        Button myPatientsBtn = new Button("Pacienti\ninternati");
        Button updatePriceBtn = new Button("Actualizeaza preturi");
        Button suspendActivityB = new Button();
        Button raspundeBtn = new Button("Raspunde");

        ListView lvCereri = new ListView();



        window.setTitle("Main Page");

        updatePriceBtn.setPrefWidth(130);
        myPatientsBtn.setPrefWidth(130);
        myPatientsBtn.setMinHeight(50);

        GridPane gridLayout = new GridPane();
        gridLayout.setPadding(new Insets(15, 15, 15, 15));
        gridLayout.setVgap(7);
        gridLayout.setHgap(1);

       /* VBox vb = new VBox();
        VBox vb2 = new VBox();

        vb2.setPrefWidth(180);

        vb.setAlignment(Pos.BASELINE_CENTER);
        vb.setSpacing(7);

        vb2.setAlignment(Pos.BASELINE_CENTER);
        vb2.setSpacing(7);
        vb.setSpacing(7);

        vb2.setPadding(new Insets(10, 10, 10, 10));
        vb.setPadding(new Insets(10, 10, 10, 10));*/



        String filename = "target/" + LogIn.loggedUser.getUsername() + ".json";
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

        ExecuteService.addCereriLista(lvCereri);

        myPatientsBtn.setOnAction(e->MyPatients.myPatientsScene(window));
        raspundeBtn.setOnAction(e -> {
            if(lvCereri.getSelectionModel().isEmpty())
            {
                Label label = new Label("Selectati o cerere apoi raspundeti!");
                gridLayout.add(label, 1, 4);
            }
            else
            {
                String nume_str[]  = lvCereri.getSelectionModel().getSelectedItem().toString().split(" -> ");
                numePacient = nume_str[0];
                cererePacient = nume_str[1];

                try {
                    ExecuteService.init();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }


        });



        gridLayout.setConstraints(suspendActivityB, 0, 0);
        gridLayout.setConstraints(updatePriceBtn, 0, 1);
        gridLayout.setConstraints(myPatientsBtn, 0, 3);
        gridLayout.setConstraints(requestLabel, 1, 0);
        gridLayout.setConstraints(lvCereri, 1, 1);
        gridLayout.setConstraints(raspundeBtn, 1, 3);

        gridLayout.setPadding(new Insets(15, 15, 15, 15));



        gridLayout.setHgap(10);
        String filenameType = "target/" + LogIn.loggedUser.getUsername() + ".json";
        JSONObject jsonObjectType = MainPacient.parseJSONFile(filenameType);
        if(jsonObjectType.get("tip_serviciu").toString().equals("privat"))
        {
            gridLayout.getChildren().addAll(suspendActivityB, updatePriceBtn, myPatientsBtn,requestLabel, lvCereri, raspundeBtn);
        }
        else
        {
            gridLayout.getChildren().addAll(suspendActivityB, myPatientsBtn,requestLabel, lvCereri, raspundeBtn);
        }

        //gridLayout.getChildren().addAll(suspendActivityB, updatePriceBtn, myPatientsBtn,requestLabel, lvCereri, raspundeBtn);
        gridLayout.setAlignment(Pos.CENTER_LEFT);
        gridLayout.setValignment(lvCereri, VPos.TOP);
        gridLayout.setValignment(updatePriceBtn, VPos.TOP);




        Scene sceneMain = new Scene(gridLayout, 450, 300);
        window.setScene(sceneMain);

        suspendActivityB.setOnAction(e-> {

            String fname = "target/" + LogIn.loggedUser.getUsername() + ".json";
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
