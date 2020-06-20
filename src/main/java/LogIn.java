
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class LogIn {

    static Button logIn;
    static Alert alert;

    public static JsonUser loggedUser;
    /*public static void main(String[] args) {

        launch(args);
    }*/

    //@Override
    public static void startLogIn(Stage window) throws Exception {

        window.setTitle("Log In Tab");

        logIn=new Button("Log In");
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Username sau password gresit!");




        GridPane gridLayout=new GridPane();
        gridLayout.setPadding(new Insets(15, 15, 15, 15));
        gridLayout.setVgap(7);
        gridLayout.setHgap(1);
        gridLayout.setAlignment(Pos.BASELINE_CENTER);

        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");
        TextField usernameTF = new TextField();
        PasswordField passwordTF = new PasswordField();
        usernameTF.setPromptText("Enter your username");
        passwordTF.setPromptText("Enter your password");

        gridLayout.setConstraints(usernameLabel, 0, 1 );
        gridLayout.setConstraints(passwordLabel, 0, 2);
        gridLayout.setConstraints(usernameTF, 1 , 1);
        gridLayout.setConstraints(passwordTF, 1, 2 );
        gridLayout.setConstraints(logIn,0,3);


        VBox layout=new VBox(20);
        gridLayout.getChildren().addAll(usernameLabel,passwordLabel,logIn,usernameTF,passwordTF);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(gridLayout,650,250);

        window.setScene(scene);
        window.show();

        logIn.setOnAction(e -> {
                    try {
                        if (!AdminService.verifyUser(usernameTF.getText(), passwordTF.getText()))
                            alert.show();
                        else {
                            try {
                                    if(AdminService.returnRole(usernameTF.getText()).equals("Medic"))
                                    {
                                        loggedUser=new JsonUser(usernameTF.getText(),passwordTF.getText(),"Medic");
                                        MedicMainPage.Init(Main.window);
                                    }
                                    else
                                        {
                                            loggedUser=new JsonUser(usernameTF.getText(),passwordTF.getText(),"Pacient");
                                            MainPacient.init(Main.window);
                                    }
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }

                        }

                    }



                    catch (NoSuchAlgorithmException e1){
                        e1.printStackTrace();

                    }
                    catch (IOException e2){
                        e2.printStackTrace();
                    }

                }
        );



    }

    }

