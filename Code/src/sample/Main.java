package sample;

import Modele.Partie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Partie p;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            p = new Partie(300);
            p.genererCommandes();
            Parent root = FXMLLoader.load(getClass().getResource("../Vue/jeu.fxml"));
            primaryStage.setTitle("L'Esc Ô Bar");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}