package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChoixNiveauxController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Label lvl1;
    @FXML
    private Label lvl2;
    @FXML
    private Label lvl3;
    @FXML
	private Label retour;

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
		lvl1.setText(bundle.getString("lvl1"));
		lvl2.setText(bundle.getString("lvl2"));
		lvl3.setText(bundle.getString("lvl3"));
		retour.setText(bundle.getString("retour"));
	}

	@FXML
    private void retourAccueil(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		Scene Scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/Accueil.fxml")));
		stage.setScene(Scene);
		stage.show();
    }

    /**
     * Fonction appelé lors du clic sur un niveau, lance le niveau en question
     * @param event
     * @throws IOException
     */
    public void clicNiveau(ActionEvent event) throws IOException {
        Button select = (Button) event.getSource();
        Scene scene;
        Stage stage;
        switch (select.getId()){
            case "niveau1" :
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Main.p = Main.save.charger(1);
                scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/InterfaceJeu.fxml")));
                stage.setScene(scene);
                stage.show();
                break;
            case "niveau2" :
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Main.p = Main.save.charger(2);
                scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/InterfaceJeu.fxml")));
                stage.setScene(scene);
                stage.show();
                break;
            case "niveau3" :
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Main.p = Main.save.charger(3);
                scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/InterfaceJeu.fxml")));
                stage.setScene(scene);
                stage.show();
                break;
        }
    }
}
