import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main extends Application{

    private Button login, signup;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("users.json");
        SignUp.obj = objectMapper.readValue(file, new TypeReference<List<JsonUser>>() {});

        primaryStage.setTitle("Portal consultatii");
        login=new Button("Log In");
        signup=new Button("Sign Up");
        Label label=new Label("Daca aveti deja un cont dati click pe 'Log In', daca nu dati pe 'Sign Up' pentru a crea unul");

        login.setOnAction(e -> {
            try{
                LogIn.startLogIn(primaryStage);
            }catch (Exception exception){
                exception.printStackTrace();
            }
        });
        signup.setOnAction(e->{
            SignUp.setStage(primaryStage);
            try {
                SignUp.initSignUp();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        VBox layout=new VBox(20);
        layout.getChildren().addAll(label,login,signup);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(layout,650,250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
