import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

        VBox vb = new VBox();
        VBox vb2 = new VBox();

        vb2.setPrefWidth(180);

        vb.setAlignment(Pos.BASELINE_CENTER);
        vb.setSpacing(7);
        vb2.setAlignment(Pos.BASELINE_CENTER);
        vb2.setSpacing(7);

        vb2.setPadding(new Insets(10, 10, 10, 10));
        vb.setPadding(new Insets(10, 10, 10, 10));

        vb.getChildren().addAll(mainpg,cereri);
        vb2.getChildren().addAll(medici,listView,solicita);

        gridLayout.getChildren().addAll(vb, vb2);

        gridLayout.setConstraints(vb, 0, 0);
        gridLayout.setConstraints(vb2, 1, 0);

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
