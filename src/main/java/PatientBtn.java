import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import org.json.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class PatientBtn extends Button{

    static String adress;
    //List<JsonUser> obj;


    public PatientBtn(String value)
    {
        super(value);
    }


    public void writeUserDataPatient(TextField userTF, TextField passwordTF) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("users.json");
        SignUp.obj = objectMapper.readValue(file, new TypeReference<List<JsonUser>>() {});
        String Val = this.getText();
        this.setOnAction(e -> {
            //JSONObject userJson = new JSONObject();
            JsonUser userJson = new JsonUser();



            PatientPromptedWindow.window();// apeleaza getPatientData(adresa, stage -> ce trebuie inchis)
            //AdminService parola = new AdminService();
            //userJson.put("username", userTF.getText());
            if(userTF.getText() == null|| userTF.getText().length() == 0 || passwordTF.getText() == null || adress == null||passwordTF.getText().length() ==  0 )
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Completati toate campurile!");
                alert.show();

            }
            else {

                if (AdminService.userAlraedyExists(userTF.getText()) == true) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Acest username deja exista!");
                    alert.show();
                    //AdminService.userAlraedyExists(userTF.getText());

                } else {

                    userJson.setUsername(userTF.getText());


                    try {
                        userJson.setPassword(AdminService.encrypt(passwordTF.getText()));
                    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                        noSuchAlgorithmException.printStackTrace();
                    }

                    userJson.setRole(Val);
                    //userJson.put("adresa:", adress);

                    SignUp.obj.add(userJson);

                    //System.out.println(obj.toString());

                    try {
                        objectMapper.writeValue(new File("users.json"), SignUp.obj);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
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
