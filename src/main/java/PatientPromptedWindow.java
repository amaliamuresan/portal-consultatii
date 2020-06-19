import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.json.JSONObject;

public class PatientPromptedWindow {

    public static String adress;



    public static String window(TextField userTF, TextField passwordTF)
    {
        Stage window = new Stage();
        window.setTitle("Sign Up");

        //window.initModality(Modality.APPLICATION_MODAL);

        VBox layout = new VBox(10);
        layout.minWidth(250);
        layout.setPadding( new Insets(10, 10, 10, 10));
        layout.setAlignment(Pos.CENTER);

        PatientBtn registerBtn = new PatientBtn("Inregistreaza");

        TextField adressTF = new TextField();
        adressTF.setPromptText("Scrie adresa");
        Label adressLabel = new Label("Adresa");

        layout.getChildren().addAll(adressLabel, adressTF, registerBtn);

        registerBtn.getPatientData(adressTF, window, userTF, passwordTF);//trimite adresa si window catre PatientPromptedWindow

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();


        return adress;


    }

}
