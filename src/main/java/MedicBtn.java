import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MedicBtn extends Button {

    ArrayList<String> DoctorData = new ArrayList<>();
    //List<JsonUser> obj;


    public MedicBtn(String value)
    {
        super(value);
    }

    public void writeUserDataMedic(TextField userTF, TextField passwordTF) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("users.json");
        SignUp.obj = objectMapper.readValue(file, new TypeReference<List<JsonUser>>() {});

        String Val = this.getText();



        this.setOnAction(e -> {
            JsonUser userJson = new JsonUser();
//(DoctorData.size() < 3 && DoctorData.get(0).equals("Public")) || (DoctorData.size() <6 && DoctorData.get(0).equals("Privat"))
            if(userTF.getText().length() == 0 || passwordTF.getText().length() == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Completati toate campurile!");
                alert.show();

            }else {
                MedicPromptedWindow.window();
                MedicPromptedWindow.c = 0;


                // apeleaza getPatientData(adresa, stage -> ce trebuie inchis)
                userJson.setUsername(userTF.getText());
                try {
                    userJson.setPassword(AdminService.encrypt(passwordTF.getText()));
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    noSuchAlgorithmException.printStackTrace();
                }
                userJson.setRole(Val);
                if (MedicPromptedWindow.doctorData.size() > 0)
                    if ((MedicPromptedWindow.doctorData.size() < 4 && MedicPromptedWindow.doctorData.get(0).equals("Public")) || (MedicPromptedWindow.doctorData.size() < 6 && MedicPromptedWindow.doctorData.get(0).equals("Privat")) || MedicPromptedWindow.doctorData.contains(null) || MedicPromptedWindow.doctorData.contains("")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setContentText("Completati toate campurile!");
                        alert.show();

                    } else {
                        if (AdminService.verificareParafa(MedicPromptedWindow.doctorData.get(2)) == false) {
                            //System.out.println(MedicPromptedWindow.doctorData.get(2));
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Dialog");
                            alert.setContentText("Cod Parafa invalid!");
                            alert.show();

                        } else {
            /*if(MedicPromptedWindow.doctorData.size() != 0 )
            {
                userJson.put("tip_serviciu:", MedicPromptedWindow.doctorData.get(0));
                userJson.put("specialitate:", MedicPromptedWindow.doctorData.get(1));
                userJson.put("cod parafa:", MedicPromptedWindow.doctorData.get(2));
            }
            if(MedicPromptedWindow.doctorData.size() == 4)
            {
                userJson.put("adresa Spital:", MedicPromptedWindow.doctorData.get(3));
            }

            if(MedicPromptedWindow.doctorData.size()  == 6)
            {
                userJson.put("adresa Clinica:", MedicPromptedWindow.doctorData.get(3));
                JSONObject priceJson = new JSONObject();
                priceJson.put("pret Consult:", MedicPromptedWindow.doctorData.get(4));
                priceJson.put("pret Interpretare:", MedicPromptedWindow.doctorData.get(5));

                userJson.put("preturi:", priceJson );

            }*/

                            SignUp.obj.add(userJson);

                            //System.out.println("--------Date:----------");
                            //System.out.println(obj.toString());

                            try {
                                objectMapper.writeValue(new File("users.json"), SignUp.obj);

                                //file.write(obj.toString());
                                //file.flush();

                            } catch (IOException err) {
                                err.printStackTrace();
                            }
                        }
                    }
            }

        });



    }


    public void getDoctorData(ArrayList<String> data, Stage window)
    {
        this.setOnAction(e ->
        {
        for( String i : data)
        {
            DoctorData.add(i);
            window.close();
        }
        });

    }

    public static void verifica()
    {

    }

}
