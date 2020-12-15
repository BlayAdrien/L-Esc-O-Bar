package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinController implements Initializable {

    @FXML
    private Button quitter;

    @FXML
    private Label score;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void rejouer(ActionEvent event) {

    }

    @FXML
    void quitter(ActionEvent event) throws IOException {
        Scene Scene = new Scene(FXMLLoader.load(getClass().getResource("/Vue/Accueil.fxml")));

        Stage stage  = (Stage) quitter.getScene().getWindow();
        stage.setScene(Scene);
        stage.show();

    }


    }
