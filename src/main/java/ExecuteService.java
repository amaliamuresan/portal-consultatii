import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;


public class ExecuteService {

    public static TextArea textArea = new TextArea();

    //public static Stage window = new Stage();

    public static void init() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        VBox vb = new VBox(15);


        Label raspunsLabel = new Label("Trimiteti un raspuns catre pacient");


        Button raspBtn = new Button("Trimite raspuns");
        CheckBox interneazaCB = new CheckBox("Interneaza");
        window.setTitle("Executa serviciu");
        vb.setAlignment(Pos.TOP_CENTER);



        textArea.setMinHeight(100);
        textArea.setMaxWidth(300);

        vb.getChildren().addAll(raspunsLabel, textArea, raspBtn, interneazaCB);
        raspBtn.setOnAction(e -> {
            if(textArea == null || textArea.getText().equals(""))
            {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Completati campul!");
            alert.show();
            }
            else
            {
                try {
                    ExecuteService.trimiteRaspuns(textArea, MedicMainPage.numePacient, MedicMainPage.cererePacient);
                } catch (IOException ioException) {
                ioException.printStackTrace();
                }
                window.close();
            }});





        Scene scene=new Scene(vb,500,310);
        window.setScene(scene);
        window.showAndWait();


    }

    public static void trimiteRaspuns(TextArea textArea, String namePatient, String cerere) throws IOException {
        String filename = "Users/" + namePatient + ".json";
        JSONObject jsonObject= MainPacient.parseJSONFile(filename);
        JSONObject rasp= new JSONObject();
        rasp.put(cerere + " -> "  + LogIn.loggedUser.getUsername(),textArea.getText());
        JSONArray raspunsuri = jsonObject.getJSONArray("Raspunsuri");
        raspunsuri.put(rasp);
        FileWriter file = new FileWriter(filename);
        file.write(jsonObject.toString());
        file.flush();


    }

    public static void addCereriLista(ListView list)
    {
        Main.updateUsers();
        for (JsonUser user: SignUp.obj)
            if (user.getUsername().equals(LogIn.loggedUser.getUsername())) {
                String filename = "Users/" + user.getUsername() + ".json";
                try {
                    JSONObject jsonObject = MainPacient.parseJSONFile(filename);
                    JSONArray cereri = jsonObject.getJSONArray("Cereri");
                    for(int i=0;i<cereri.length();i++) {
                        String string=cereri.getJSONObject(i).toString(),cerere=new String("");
                        for(int j=0;j<string.length();j++){
                            char c=string.charAt(j);
                            if(c==':')
                                cerere+=" -> ";
                            else
                            if(c != '{' && c!='}' && c!='"')
                                cerere+=c;
                        }
                        list.getItems().add(cerere);
                    }
                }catch (IOException exception){
                    exception.printStackTrace();
                }
            }
    }
        



}
