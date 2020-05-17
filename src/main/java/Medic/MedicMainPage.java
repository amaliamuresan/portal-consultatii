package Medic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class MedicMainPage{


    public static Stage window;


    //@Override
    public static void Init(Stage window) {



        Label requestLabel = new Label("Lista cereri:");
        Button myPatientsBtn = new Button("Pacienti internati");
        Button updatePriceBtn = new Button("Actualizeaza preturi");
        CheckBox suspendActivityCB = new CheckBox("Suspenda Activitatea");


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

        vb.getChildren().addAll(suspendActivityCB, updatePriceBtn, myPatientsBtn);
        vb2.getChildren().addAll(requestLabel);

        gridLayout.setConstraints(vb, 0, 0);
        gridLayout.setConstraints(vb2, 1, 0);

        gridLayout.getChildren().addAll(vb2, vb);

        Scene sceneMain = new Scene(gridLayout, 450, 300);
        window.setScene(sceneMain);


        //window.show();


    }

    public static void setStage(Stage stage)
    {
        window = stage;
    }



}
