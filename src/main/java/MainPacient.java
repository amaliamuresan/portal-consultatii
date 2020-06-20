import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class MainPacient {

    private static Object String;
    private Stage window;
    static ListView<String> listView=new ListView<>();
    static GridPane gridLayout = new GridPane();
    public static String selectedDoctor;

    /*public static void main(String[] args) throws IOException {

        launch(args);

    }*/

    //@Override
    public static void init(Stage window) throws Exception{

        //window=w;

        window.setTitle("Pagina Principala");

        Button mainpg,cereri,solicita, filtreazaBtn, raspunsuriBtn;
        mainpg=new Button("Pagina Principala");
        cereri=new Button("Cererile mele");
        solicita=new Button("Cere Serviciu");
        filtreazaBtn = new Button("Filtreaza");
        Button okBtn = new Button("OK");
        TextField specialtyTF = new TextField();

        specialtyTF.setPromptText("Alegeti specializarea");

        raspunsuriBtn = new Button("Raspunsuri");

        mainpg.setPrefWidth(130);
        cereri.setPrefWidth(130);
        solicita.setPrefWidth(130);
        raspunsuriBtn.setPrefWidth(130);
        filtreazaBtn.setPrefWidth(130);

        Label medici=new Label("Medici disponibili:");

        gridLayout.setPadding(new Insets(15, 15, 15, 15));
        gridLayout.setVgap(7);
        gridLayout.setHgap(1);

        gridLayout.setAlignment(Pos.CENTER_LEFT);

        MainPacient.addNumeMedici(listView);

        gridLayout.getChildren().addAll(mainpg,cereri, medici,listView,solicita, filtreazaBtn, raspunsuriBtn);

        gridLayout.setConstraints(mainpg, 0, 0);
        gridLayout.setConstraints(cereri, 0, 1);
        gridLayout.setConstraints(medici, 1, 0);
        gridLayout.setConstraints(listView,1,1);
        gridLayout.setConstraints(solicita,1,2);
        gridLayout.setConstraints(filtreazaBtn, 0, 2);
        gridLayout.setConstraints(raspunsuriBtn, 0, 3);

        gridLayout.setValignment(cereri,VPos.TOP);

        gridLayout.setHgap(10);



        Scene sceneMain = new Scene(gridLayout, 500, 350);
        window.setScene(sceneMain);

        window.show();
        //MainPacient.filterMedici("s2");

        raspunsuriBtn.setOnAction((e->window.setScene(MainPacient.cereriWindow(sceneMain,window))));
        mainpg.setOnAction(e->window.setScene(sceneMain));
        cereri.setOnAction((e->window.setScene(MainPacient.makeScenaCereri(sceneMain,window))));
        solicita.setOnAction(e->{
            if(listView.getSelectionModel().isEmpty()) {
                Label label = new Label("Selectati un medic, apoi faceti o cerere!!!");
                gridLayout.add(label, 1, 3);
            }
            else {
                selectedDoctor=listView.getSelectionModel().getSelectedItem();
                RequestService.promptChoice();
            }
        });



        filtreazaBtn.setOnAction( e -> {
            HBox hb = new HBox(10);
            hb.getChildren().addAll(specialtyTF, okBtn);
            hb.setAlignment(Pos.BASELINE_CENTER);
            hb.setPadding(new Insets(5, 5,5,5));
            Stage wi= new Stage();
            wi.setTitle("Filtreaza");

            Scene sc = new Scene(hb, 300, 50);
            wi.setScene(sc);
            wi.show();

            okBtn.setOnAction( r -> {
                try {
                    MainPacient.filterMedici(specialtyTF.getText());


                    wi.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

        });
    }

    private static void addNumeMedici(ListView<String> list){
        Main.updateUsers();
        for (JsonUser user: SignUp.obj)
            if (user.getRole().equals("Medic")) {
                String filename = "Users/" + user.getUsername() + ".json";
                try {
                    JSONObject jsonObject = MainPacient.parseJSONFile(filename);
                    if((int)jsonObject.get("Activitate") == 1)
                        list.getItems().add(user.getUsername());
                }catch(IOException exp){
                    exp.printStackTrace();
                }

            }
    }

    private static void filterMedici(String spec) throws IOException {
        //ListView<String> filtredList = new ListView<>();
        if(spec.equals("") || spec == null)
        {
            listView.getItems().clear();
            MainPacient.addNumeMedici(listView);
            return;
        }
        listView.getItems().clear();
        for (JsonUser user: SignUp.obj)
            if (user.getRole().equals("Medic")) {
                String filename = "Users/" + user.getUsername() + ".json";
                ObjectMapper objectMapper = new ObjectMapper();
                File file = new File(filename);
                JSONObject jsonObject = parseJSONFile(filename);
                String speciality = (java.lang.String) jsonObject.get("specialitate");
                if(speciality.equals(spec))
                    listView.getItems().add(user.getUsername());


            }



    }

    public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }

    private static Scene makeScenaCereri(Scene sceneMain,Stage w){
        Button mainpg,anuleaza, raspunsuriBtn;
        mainpg=new Button("Pagina Principala");
        anuleaza= new Button("Anuleaza cerere");

        mainpg.setPrefWidth(140);
        anuleaza.setPrefWidth(140);

        Label cere=new Label("Cereri in curs:");

        GridPane gridLayout = new GridPane();
        gridLayout.setPadding(new Insets(15, 15, 15, 15));
        gridLayout.setVgap(7);
        gridLayout.setHgap(1);

        ListView<String> listView = new ListView<>();
        MainPacient.addCererileMele(listView);

        gridLayout.getChildren().addAll(mainpg, cere,listView,anuleaza);

        gridLayout.setConstraints(mainpg, 0, 0);
        gridLayout.setConstraints(cere, 1, 0);
        gridLayout.setConstraints(listView,1,1);
        gridLayout.setConstraints(anuleaza,1,2);

        gridLayout.setHgap(10);

        mainpg.setOnAction(e->w.setScene(sceneMain));

        Scene scenaCereri = new Scene(gridLayout, 500, 350);

        anuleaza.setOnAction(e ->
        {
            if(listView.getSelectionModel().isEmpty()) {
                Label label = new Label("Selectati o cerere de anulat!");
                gridLayout.add(label, 1, 3);
            }
            else {

                    int nr_cerere = listView.getSelectionModel().getSelectedIndex();




                String str_cerere[] = listView.getSelectionModel().getSelectedItem().split(" -> ");
                listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
                listView.refresh();

                //System.out.println(str_cerere[0] + str_cerere[1]);
                String filename = "Users/" + LogIn.loggedUser.getUsername()  + ".json";
                String filenameDoctor = "Users/" + str_cerere[0].trim()  + ".json";
                System.out.println(str_cerere[0]);

                JSONObject jsonObject = null;
                JSONObject jsonObjectDoctor = null;
                try {
                    jsonObject = MainPacient.parseJSONFile(filename);
                    jsonObjectDoctor = MainPacient.parseJSONFile(filenameDoctor);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                JSONArray cereri = jsonObject.getJSONArray("Cereri");
                JSONArray cereriDoctor = jsonObjectDoctor.getJSONArray("Cereri");
                if(cereri == null ||cereriDoctor == null)
                    return;
                //System.out.println(cereri.getJSONObject(nr_cerere).toString() + "pacient");
                //cereri.getJSONObject(nr_cerere).remove(str_cerere[0]);
                //cereri.remove(nr_cerere);

                for(int i = 0; i < cereriDoctor.length(); i++)
                {
                    //System.out.println(LogIn.loggedUser.getUsername() + "1");
                    //System.out.println(cereriDoctor.getJSONObject(i).get(LogIn.loggedUser.getUsername()) + "  -- " + cereri.getJSONObject(nr_cerere).get(str_cerere[0]));
                    //cereriDoctor.getJSONObject(i).keys();
                    //System.out.println(cereriDoctor.getJSONObject(i).names().getString(0));
                    if((cereriDoctor.getJSONObject(i).names().getString(0)).equals(LogIn.loggedUser.getUsername()));
                    {

                        System.out.println(cereriDoctor.getJSONObject(i).toString() + "doctor");
                        //cereriDoctor.getJSONObject(i).remove(LogIn.loggedUser.getUsername());
                        cereriDoctor.remove(i);

                    }
                }

                cereri.remove(nr_cerere);



                //jsonObject.get("Cereri");
                jsonObjectDoctor.remove("Cereri");
                jsonObject.remove("Cereri");
                if(cereri.length() > 0)
                {
                    jsonObject.put("Cereri", cereri);
                }
                else
                {
                    JSONArray ar = new JSONArray();
                    jsonObject.put("Cereri", ar);
                }

                if(cereri.length() > 0)
                {
                    jsonObjectDoctor.put("Cereri", cereriDoctor);
                }
                else
                {
                    JSONArray ar = new JSONArray();
                    jsonObjectDoctor.put("Cereri", ar);
                }
                //jsonObject.put("Cereri", cereri);


                FileWriter filDoc = null;
                FileWriter fil = null;
                try {
                    fil = new FileWriter("Users/" + LogIn.loggedUser.getUsername() + ".json");
                    filDoc = new FileWriter("Users/" + str_cerere[0].trim() + ".json");

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    fil.write(jsonObject.toString());
                    filDoc.write(jsonObjectDoctor.toString());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    fil.flush();
                    filDoc.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }



            }

            //MainPacient.addCererileMele(listView);
            gridLayout.getChildren().remove(listView);
            gridLayout.getChildren().add(listView);
            gridLayout.setConstraints(listView, 1, 1);

            listView.refresh();

        });
        return scenaCereri;
    }

    private static void addCererileMele(ListView<String> list){
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

    public static Scene cereriWindow(Scene sceneMain, Stage window)
    {
        //Stage window = new Stage();
        //window.initModality(Modality.APPLICATION_MODAL);

        VBox vb = new VBox(15);

        Button mainpgBtn =new Button("Pagina Principala");
        Label raspunsLabel = new Label("Raspunsuri");
        Button stergeRaspBtn = new Button("Sterge raspuns");
        ListView lvRasp = new ListView();
        MainPacient.addRasp(lvRasp);

        stergeRaspBtn.setPrefWidth(130);
        mainpgBtn.setPrefWidth(130);


        vb.getChildren().addAll(raspunsLabel,lvRasp, stergeRaspBtn, mainpgBtn);
        vb.setPadding(new Insets(10, 10, 10, 10));
        vb.setAlignment(Pos.CENTER);
        mainpgBtn.setOnAction(e -> window.setScene(sceneMain));

        Scene scenaCereri = new Scene(vb, 500, 350);
        window.setScene(sceneMain);
        //window.show();

        return scenaCereri;
    }

    public static void addRasp(ListView<String> lv)
    {
        Main.updateUsers();
        for (JsonUser user: SignUp.obj)
            if (user.getUsername().equals(LogIn.loggedUser.getUsername())) {
                String filename = "Users/" + user.getUsername() + ".json";
                try {
                    JSONObject jsonObject = MainPacient.parseJSONFile(filename);
                    JSONArray raspunsuri = jsonObject.getJSONArray("Raspunsuri");
                    for(int i=0;i<raspunsuri.length();i++) {
                        String string=raspunsuri.getJSONObject(i).toString(),rasp=new String("");
                        for(int j=0;j<string.length();j++){
                            char c=string.charAt(j);
                            if(c==':')
                                rasp+=" -> ";
                            else
                            if(c != '{' && c!='}' && c!='"')
                                rasp+=c;
                        }
                        lv.getItems().add(rasp);
                    }
                }catch (IOException exception){
                    exception.printStackTrace();
                }
            }

    }

}
