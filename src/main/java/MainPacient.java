import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class MainPacient extends Application {

    private Stage window;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage w) throws Exception{

        window=w;

        window.setTitle("Pagina Principala");

        Button mainpg,cereri,solicita;
        mainpg=new Button("Pagina Principala");
        cereri=new Button("Cererile mele");
        solicita=new Button("Cere Serviciu");

        mainpg.setPrefWidth(140);
        cereri.setPrefWidth(130);
        solicita.setPrefWidth(130);

        Label medici=new Label("Medici disponibili:");

        GridPane gridLayout = new GridPane();
        gridLayout.setPadding(new Insets(15, 15, 15, 15));
        gridLayout.setVgap(7);
        gridLayout.setHgap(1);

        ListView<String> listView=new ListView<>();
        MainPacient.addNumeMedici(listView);

        gridLayout.getChildren().addAll(mainpg,cereri, medici,listView,solicita);

        gridLayout.setConstraints(mainpg, 0, 0);
        gridLayout.setConstraints(cereri, 0, 1);
        gridLayout.setConstraints(medici, 1, 0);
        gridLayout.setConstraints(listView,1,1);
        gridLayout.setConstraints(solicita,1,2);

        gridLayout.setValignment(cereri,VPos.TOP);

        gridLayout.setHgap(10);

        Scene sceneMain = new Scene(gridLayout, 500, 350);
        window.setScene(sceneMain);

        window.show();
    }

    private static void addNumeMedici(ListView<String> list){
        Main.updateUsers();
        for (JsonUser user: SignUp.obj)
            if (user.getRole().equals("Medic")) {
                list.getItems().add(user.getUsername());
            }
    }
}
