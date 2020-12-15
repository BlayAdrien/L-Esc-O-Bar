package sample;

import Modele.Partie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Partie p;
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            p = new Partie(300);
            p.genererCommandes();
            Parent root = FXMLLoader.load(getClass().getResource("../Vue/Accueil.fxml"));
            primaryStage.setTitle("L'Esc O Bar");
            primaryStage.setScene(new Scene(root));

            primaryStage.setResizable(false);
            primaryStage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}