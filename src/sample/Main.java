package sample;

import Modele.Partie;
import Modele.SauvegardeGestion;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    public static Partie p;
    public static SauvegardeGestion save = new SauvegardeGestion();
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            Parent root = FXMLLoader.load(getClass().getResource("../Vue/Accueil.fxml"));
            primaryStage.setTitle("L'Esc O Bar");
            primaryStage.setScene(new Scene(root));

            primaryStage.setResizable(false);
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    save.sauvegarder(p);
                }
            });
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