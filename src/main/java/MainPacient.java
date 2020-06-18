import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
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
import javafx.stage.Stage;
import javafx.scene.control.Button;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainPacient extends Application {

    private static Object String;
    private Stage window;
    static ListView<String> listView=new ListView<>();
    static GridPane gridLayout = new GridPane();

    public static void main(String[] args) throws IOException {

        launch(args);

    }

    @Override
    public void start(Stage w) throws Exception{

        window=w;

        window.setTitle("Pagina Principala");

        Button mainpg,cereri,solicita, filtreazaBtn;
        mainpg=new Button("Pagina Principala");
        cereri=new Button("Cererile mele");
        solicita=new Button("Cere Serviciu");
        filtreazaBtn = new Button("Filtreaza");
        Button okBtn = new Button("OK");
        TextField specialtyTF = new TextField();
        specialtyTF.setPromptText("Alegeti specializarea");

        mainpg.setPrefWidth(140);
        cereri.setPrefWidth(130);
        solicita.setPrefWidth(130);

        Label medici=new Label("Medici disponibili:");

        gridLayout.setPadding(new Insets(15, 15, 15, 15));
        gridLayout.setVgap(7);
        gridLayout.setHgap(1);



        MainPacient.addNumeMedici(listView);

        gridLayout.getChildren().addAll(mainpg,cereri, medici,listView,solicita, filtreazaBtn);

        gridLayout.setConstraints(mainpg, 0, 0);
        gridLayout.setConstraints(cereri, 0, 1);
        gridLayout.setConstraints(medici, 1, 0);
        gridLayout.setConstraints(listView,1,1);
        gridLayout.setConstraints(solicita,1,2);
        gridLayout.setConstraints(filtreazaBtn, 0, 2);

        gridLayout.setValignment(cereri,VPos.TOP);

        gridLayout.setHgap(10);



        Scene sceneMain = new Scene(gridLayout, 500, 350);
        window.setScene(sceneMain);

        window.show();
        //MainPacient.filterMedici("s2");

        mainpg.setOnAction(e->window.setScene(sceneMain));
        cereri.setOnAction((e->window.setScene(MainPacient.makeScenaCereri(sceneMain,window))));

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
                list.getItems().add(user.getUsername());
            }
    }

    private static void filterMedici(String spec) throws IOException {
        //ListView<String> filtredList = new ListView<>();
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

        //listView = filtredList;
           // listView.refresh();
        //listView.getItems().clear();

        //gridLayout.setConstraints(filtredList,1,1);




    }

    public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }

    private static Scene makeScenaCereri(Scene sceneMain,Stage w){
        Button mainpg,anuleaza;
        mainpg=new Button("Pagina Principala");
        anuleaza= new Button("Anuleaza cerere");

        mainpg.setPrefWidth(140);
        anuleaza.setPrefWidth(140);

        Label cere=new Label("Cereri in curs:");

        GridPane gridLayout = new GridPane();
        gridLayout.setPadding(new Insets(15, 15, 15, 15));
        gridLayout.setVgap(7);
        gridLayout.setHgap(1);

        ListView<String> listView=new ListView<>();

        gridLayout.getChildren().addAll(mainpg, cere,listView,anuleaza);

        gridLayout.setConstraints(mainpg, 0, 0);
        gridLayout.setConstraints(cere, 1, 0);
        gridLayout.setConstraints(listView,1,1);
        gridLayout.setConstraints(anuleaza,1,2);

        gridLayout.setHgap(10);

        mainpg.setOnAction(e->w.setScene(sceneMain));

        Scene scenaCereri = new Scene(gridLayout, 500, 350);
        return scenaCereri;
    }



}
