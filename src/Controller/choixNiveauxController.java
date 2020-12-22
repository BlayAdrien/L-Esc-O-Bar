package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class choixNiveauxController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    public void clicNiveau(ActionEvent event) throws IOException {
        Button select = (Button) event.getSource();
        Scene Scene;
        Stage stage;
        switch (select.getId()){
            case "niveau1" :
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Main.p = Main.save.charger(1);
                Scene = new Scene(FXMLLoader.load(getClass().getResource("/Vue/InterfaceJeu.fxml")));
                stage.setScene(Scene);
                stage.show();
                break;
            case "niveau2" :
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Main.p = Main.save.charger(2);
                Scene = new Scene(FXMLLoader.load(getClass().getResource("/Vue/InterfaceJeu.fxml")));
                stage.setScene(Scene);
                stage.show();
                break;
            case "niveau3" :
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Main.p = Main.save.charger(3);
                Scene = new Scene(FXMLLoader.load(getClass().getResource("/Vue/InterfaceJeu.fxml")));
                stage.setScene(Scene);
                stage.show();
                break;
        }
    }
}
