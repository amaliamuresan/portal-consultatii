import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.geometry.Insets;

import java.io.IOException;
import java.util.List;


public class SignUp {

    public static List<JsonUser> obj;

    //public static void main(String[] args) {
       // launch(args);
   // }

    public static Stage window;
    


    //@Override
    public static void initSignUp() throws IOException {


        //window = primaryStage;
        window.setTitle("Sign Up");
        PatientBtn pacientBtn = new PatientBtn("Pacient");
        MedicBtn medicBtn = new MedicBtn("Medic");
        Label roleLabel = new Label("Pick a role:");
        HBox rolesHBox = new HBox();
        rolesHBox.setSpacing(5);


        GridPane gridLayout = new GridPane();
        gridLayout.setPadding(new Insets(15, 15, 15, 15));
        gridLayout.setVgap(7);
        gridLayout.setHgap(1);
        gridLayout.setAlignment(Pos.BASELINE_CENTER);

        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");

        TextField usernameTF = new TextField();
        TextField passwordTF = new TextField();

        usernameTF.setPromptText("Create an username");
        passwordTF.setPromptText("Create a password");

        gridLayout.setConstraints(usernameLabel, 0, 1 );
        gridLayout.setConstraints(passwordLabel, 0, 2);
        gridLayout.setConstraints(usernameTF, 1 , 1);
        gridLayout.setConstraints(passwordTF, 1, 2 );
        gridLayout.setConstraints(roleLabel, 1, 3);
        gridLayout.setConstraints(rolesHBox, 1, 4);

        rolesHBox.getChildren().addAll(medicBtn, pacientBtn);

        //AdminService.writeDataInJsonFile();
        Parafa.setCoduri();


        pacientBtn.writeUserDataPatient(usernameTF, passwordTF);
        medicBtn.writeUserDataMedic(usernameTF, passwordTF);

        gridLayout.getChildren().addAll(usernameLabel, usernameTF, passwordTF, passwordLabel,  roleLabel, rolesHBox);

        Scene sceneSignUp = new Scene(gridLayout, 350, 250);
        window.setScene(sceneSignUp);

        window.show();



    }

    public static void setStage(Stage stage)
    {
        window = stage;
    }
}
