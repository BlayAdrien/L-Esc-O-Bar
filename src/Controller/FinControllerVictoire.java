package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinControllerVictoire implements Initializable {

    @FXML
    private Button quitter;

    @FXML
    private Label niveauSuivant;

    public Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Main.p.getNiveau() >= 3){
            niveauSuivant.setText("Niveau Précédent");
        }

    }

    @FXML
    void rejouer(ActionEvent event) throws IOException {
        Main.save.sauvegarder(Main.p);
        Main.p = Main.save.charger(Main.p.getNiveau());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene Scene = new Scene(FXMLLoader.load(getClass().getResource("/Vue/InterfaceJeu.fxml")));
        stage.setScene(Scene);
        stage.show();
    }

    @FXML
    void niveauSuivant(ActionEvent event) throws IOException {
        Main.save.sauvegarder(Main.p);
        if (Main.p.getNiveau() >= 3){
            Main.p = Main.save.charger(Main.p.getNiveau() - 1);
        }
        else{
            Main.p = Main.save.charger(Main.p.getNiveau() +1);
        }
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene Scene = new Scene(FXMLLoader.load(getClass().getResource("/Vue/InterfaceJeu.fxml")));
        stage.setScene(Scene);
        stage.show();
    }

    @FXML
    private void accueil(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene Scene = new Scene(FXMLLoader.load(getClass().getResource("/Vue/Accueil.fxml")));
        stage.setScene(Scene);
        stage.show();
    }

    @FXML
    private void quitter(ActionEvent event) {
        Main.save.sauvegarder(Main.p);
        Platform.exit();
    }
}
