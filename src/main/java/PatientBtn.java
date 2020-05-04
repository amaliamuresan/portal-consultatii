import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import org.json.*;

import java.io.FileWriter;
import java.io.IOException;

public class PatientBtn extends Button{

    static String adress;
    JSONObject obj = new JSONObject();



    public PatientBtn(String value)
    {
        super(value);
    }


    public void writeUserDataPatient(TextField userTF, TextField passwordTF) {

        String Val = this.getText();
        this.setOnAction(e -> {
            JSONObject userJson = new JSONObject();

            PatientPromptedWindow.window();// apeleaza getPatientData(adresa, stage -> ce trebuie inchis)

            userJson.put("username:", userTF.getText());
            userJson.put("password:", passwordTF.getText());
            userJson.put("role:", Val);
            userJson.put("adresa:", adress);

            obj.put(userTF.getText(), userJson);

            System.out.println(obj.toString(4));

           try (FileWriter file = new FileWriter("users.json")) {

                file.write(userJson.toString(4));
                file.flush();

            } catch (IOException err) {
                err.printStackTrace();
            }
        });
    }

    public void  getPatientData(TextField adresaTF, Stage window)
    {

        this.setOnAction(e -> {
            //JSONObject userJson = new JSONObject();
            //JSON.put("adresa:", adresaTF.getText();
            adress = adresaTF.getText();
            window.close();

        });

    }

}
