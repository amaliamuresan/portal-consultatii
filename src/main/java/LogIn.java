import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LogIn extends Application {


    Button logIn;
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Log In Tab");

        logIn=new Button("Log In");

        GridPane gridLayout=new GridPane();
        gridLayout.setPadding(new Insets(15, 15, 15, 15));
        gridLayout.setVgap(7);
        gridLayout.setHgap(1);
        gridLayout.setAlignment(Pos.BASELINE_CENTER);

        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");
        TextField usernameTF = new TextField();
        TextField passwordTF = new TextField();
        usernameTF.setPromptText("Create an username");
        passwordTF.setPromptText("Create a password");

        gridLayout.setConstraints(usernameLabel, 0, 1 );
        gridLayout.setConstraints(passwordLabel, 0, 2);
        gridLayout.setConstraints(usernameTF, 1 , 1);
        gridLayout.setConstraints(passwordTF, 1, 2 );
        gridLayout.setConstraints(logIn,0,3);

        VBox layout=new VBox(20);
        gridLayout.getChildren().addAll(usernameLabel,passwordLabel,logIn,usernameTF,passwordTF);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(gridLayout,650,250);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
