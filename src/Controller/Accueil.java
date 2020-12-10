package Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Main;


public class Accueil {

	public Button closeButton;

	@FXML
    private void quittez() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

	@FXML
	private void jouer() throws IOException {
        Scene Scene = new Scene(FXMLLoader.load(getClass().getResource("/Vue/jeu.fxml")));
        Stage newFenetre = new Stage();
        newFenetre.setScene(Scene);
        newFenetre.initOwner(Main.getPrimaryStage());
        newFenetre.show();
    }

	@FXML
    private void regleDuJeu() throws IOException {
        Scene Scene = new Scene(FXMLLoader.load(getClass().getResource("/Vue/RegleDuJeu.fxml")));
        Stage newFenetre = new Stage();
        newFenetre.setScene(Scene);
        newFenetre.initOwner(Main.getPrimaryStage());
        newFenetre.show();
    }
}
