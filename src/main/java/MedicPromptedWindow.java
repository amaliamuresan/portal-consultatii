import com.sun.org.apache.xerces.internal.xs.StringList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.util.ArrayList;

public class MedicPromptedWindow
{

    public static ArrayList<String> doctorData;

    // public - > doctorDAta: 0 - public, 1 - specialty, 2 - codParafa, 3 - adresaSpital
    // privat - > doctorDAta: 0 - public, 1 - specialty, 2 - codParafa, 3 - adresaClinica, 4 - tarifConsult, 5 - tarifInterpretare


    static VBox layout;
    static int c = 0;



        public static void window() {

            Stage window = new Stage();
            window.setTitle("Sign Up");

            window.initModality(Modality.APPLICATION_MODAL);

            VBox layout = new VBox(10);
            layout.minWidth(450);
            layout.setPadding(new Insets(10, 10, 10, 10));
            layout.setAlignment(Pos.BASELINE_CENTER);

            Label specialtyLabel = new Label("Specializare:");
            TextField specialtyTF = new TextField();
            specialtyTF.setPromptText("Alegeti specializarea");

            Label codParafaLabel = new Label("Cod de parafa");
            TextField codParafaTF = new TextField();
            codParafaTF.setPromptText("Scrieti codul de parafa");

            TextField  AdresaSpitalTF = new TextField();
            AdresaSpitalTF.setPromptText("Scrieti adresa spitalului");

            Label serviceType = new Label("Tip serviciu:");
            CheckBox publicCB = new CheckBox("Public");
            CheckBox privatCB = new CheckBox("Privat");
            layout.getChildren().addAll(specialtyLabel, specialtyTF, codParafaLabel, codParafaTF, serviceType, publicCB, privatCB);



            publicCB.setOnAction(e ->
            {
                doctorData = new ArrayList<>();

                Label AdresaLabel = new Label("Adresa spitalului:");
                MedicBtn btn = new MedicBtn("Inregistreaza");


                if(publicCB.isSelected()) {
                    if(c == 0) {


                        layout.getChildren().addAll(AdresaLabel, AdresaSpitalTF, btn);

                        btn.setOnAction(v -> {
                            doctorData.add("public");//0
                            doctorData.add(specialtyTF.getText());//1
                            doctorData.add(codParafaTF.getText());//2
                            //System.out.println(doctorData);
                            doctorData.add(AdresaSpitalTF.getText());//3
                            window.close();

                        });

                        //System.out.println(doctorData);


                        c = 1;
                    }

                }

            });

            privatCB.setOnAction(e->
            {
                doctorData = new ArrayList<>();

                Label AdresaClinicaLabel = new Label("Adresa clinicii:");

                TextField  AdresaClinicaTF = new TextField();
                AdresaClinicaTF.setPromptText("Scrieti adresa clinicii");

                MedicBtn btn = new MedicBtn("Inregistreaza");

                Label tarifeLabel  = new Label("Seteaza tarife:");

                TextField consulatatieTF = new TextField();
                consulatatieTF.setPromptText("Tarif pentru consultatie");

                TextField interpretareTF = new TextField();
                interpretareTF.setPromptText("Tarif pentru interpretare");



                if(privatCB.isSelected()) {
                    if(c == 0)
                    {
                        layout.getChildren().addAll(AdresaClinicaLabel, AdresaClinicaTF, tarifeLabel, consulatatieTF, interpretareTF, btn);
                        btn.setOnAction( r -> {
                            doctorData.add("privat");//0
                            doctorData.add(specialtyTF.getText());//1
                            doctorData.add(codParafaTF.getText());//2
                            doctorData.add(AdresaClinicaTF.getText());//3
                            doctorData.add(consulatatieTF.getText());//4
                            doctorData.add(interpretareTF.getText());//5


                            window.close();
                            //System.out.println(doctorData);

                        });


                        c = 1;
                    }

                }

            });





            Scene scene = new Scene(layout, 350, 450);
            window.setScene(scene);
            window.showAndWait();

        }
}
