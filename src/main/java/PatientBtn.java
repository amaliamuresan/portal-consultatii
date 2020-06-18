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
    static int valid = 0;

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
            int ok = 0;
            valid = 0;

            //JSONObject userJson = new JSONObject();
            JsonUser userJson = new JsonUser();



            //PatientPromptedWindow.window();// apeleaza getPatientData(adresa, stage -> ce trebuie inchis)
            //AdminService parola = new AdminService();
            //userJson.put("username", userTF.getText());
            if(userTF.getText() == null|| userTF.getText().length() == 0 || passwordTF.getText() == null  || passwordTF.getText().length() ==  0 )
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
                    valid = 1;
                    PatientPromptedWindow.window();



                    ok =1;

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
                        JSONObject JsnObj = new JSONObject();
                        JsnObj.put("adresa", adress);
                        FileWriter fil  = new FileWriter("Users/" + userTF.getText() + ".json");
                        fil.write(JsnObj.toString());
                        fil.flush();

                        objectMapper.writeValue(new File("users.json"), SignUp.obj);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

                /*try {
                    MainPacient.init(Main.window);
                    SignUp.window.close();

                } catch (Exception exception) {
                    exception.printStackTrace();
                }*/




            }



            });
    }




    public void  getPatientData(TextField adresaTF, Stage window)
    {

        this.setOnAction(e -> {

            adress = adresaTF.getText();

            if (adress == null || adress.equals(""))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Completati toate campurile!");
                alert.show();
            }
            else {

                if (valid == 1) {
                    try {
                        MainPacient.init(window);
                        SignUp.window.close();

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }

        });

    }

}
