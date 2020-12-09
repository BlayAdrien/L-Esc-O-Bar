package Controller;

import java.io.IOException;
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

public class RegleDuJeu {


	@FXML
	private Label rules;
	@FXML
	private Label description;

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
		rules.setText(bundle.getString("rules"));
		description.setText(bundle.getString("description"));
	}

	@FXML
    private void retourAccueil() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
