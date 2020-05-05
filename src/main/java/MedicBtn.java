import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MedicBtn extends Button {

    ArrayList<String> DoctorData = new ArrayList<>();
    JSONObject obj = new JSONObject();


    public MedicBtn(String value)
    {
        super(value);
    }

    public void writeUserDataMedic(TextField userTF, TextField passwordTF) {

        String Val = this.getText();
        this.setOnAction(e -> {
            JSONObject userJson = new JSONObject();
            MedicPromptedWindow.window();
            MedicPromptedWindow.c = 0;// apeleaza getPatientData(adresa, stage -> ce trebuie inchis)
            userJson.put("username:", userTF.getText());
            userJson.put("password:", passwordTF.getText());
            userJson.put("role:", Val);
            if(MedicPromptedWindow.doctorData.size() != 0 )
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

            }

            obj.put(userTF.getText(), userJson);

            System.out.println("--------Date:----------");
            System.out.println(obj.toString(4));

            try (FileWriter file = new FileWriter("users.json")) {

                file.write(userJson.toString(4));
                file.flush();

            } catch (IOException err) {
                err.printStackTrace();
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

}
