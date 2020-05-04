import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application{

    Button login, signup;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Portal consultatii");
        login=new Button("Log In");
        signup=new Button("Sign Up");
        Label label=new Label("Daca aveti deja un cont dati click pe 'Log In', daca nu dati pe 'Sign Up' pentru a crea unul");

        VBox layout=new VBox(20);
        layout.getChildren().addAll(label,login,signup);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(layout,650,250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
