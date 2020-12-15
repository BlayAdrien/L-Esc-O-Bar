package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Main;


public class Accueil {

	@FXML
	private Label rules;
	@FXML
	private Label play;
	@FXML
	private Label quit;


	private ResourceBundle bundle;
	private Locale locale;
	public Button closeButton;

	@FXML
	private void btnEn(ActionEvent event){
		loadLang("en");
	}

	@FXML
	private void btnFr(ActionEvent event){
		loadLang("fr");
	}

	@FXML
	private void loadLang(String lang){
		locale = new Locale(lang);
		bundle = ResourceBundle.getBundle("Vue.labelText", locale);
		play.setText(bundle.getString("play"));
		rules.setText(bundle.getString("rules"));
		quit.setText(bundle.getString("quit"));
	}

	@FXML
    private void quittez() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

	@FXML
	private void jouer(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene Scene = new Scene(FXMLLoader.load(getClass().getResource("/Vue/jeu.fxml")));
       	stage.setScene(Scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent windowEvent) {

			}
		});
    }

	@FXML
    private void regleDuJeu() throws IOException {
        Scene Scene = new Scene(FXMLLoader.load(getClass().getResource("/Vue/RegleDuJeu.fxml")));
        Stage newFenetre = new Stage();
        newFenetre.setScene(Scene);
        newFenetre.initOwner(Main.getPrimaryStage());
        newFenetre.setResizable(false);
        newFenetre.show();
    }
}
