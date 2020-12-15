package Controller;

import Modele.*;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class JeuController implements Initializable {


    @FXML
    ImageView biereBlonde;

    @FXML
    ImageView biereBrune;

    @FXML
    ImageView soda;

    @FXML
    Label score;

    @FXML
    Label tpsPartie;

    @FXML
    ListView<String> listCommande;

    @FXML
    ListView<Boisson> commandeEnCours;

    ObservableList observableList = FXCollections.observableArrayList();
    ObservableList listEnCours = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        score.textProperty().bind(Bindings.convert(Main.p.scoreProperty()));
        tpsPartie.textProperty().bind(Bindings.convert(Main.p.tpsPartieProperty()));
        try {
            chrono();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setListCommande();
    }

    public void setListCommande(){
        observableList.clear();
        listEnCours.clear();
        listCommande.setOrientation(Orientation.HORIZONTAL);
        commandeEnCours.setOrientation(Orientation.HORIZONTAL);
        for (Boisson boisson: Main.p.getCommande().getCommande()
             ) {
            observableList.add(boisson.toString());
        }

    listCommande.setItems(observableList);

    }


    public void servir(Boisson b){
        for (Boisson  boisson :  Main.p.getCommande().getCommande()) {
                if (boisson.equals(b) && !boisson.isPrepare()){
                    boisson.setPrepare(true);
                    Main.p.setScore(Main.p.getScore() + boisson.getScore());
                    listEnCours.add(boisson);
                    if (Main.p.getCommande().verifCommande()){
                        Main.p.genererCommandes();
                        listCommande.getItems().clear();
                        setListCommande();
                    }
                    break;
                }
        }
        commandeEnCours.setItems(listEnCours);
    }

    public void clicVerre(MouseEvent event){
        ImageView selectVerre = (ImageView)event.getSource();
        switch(selectVerre.getId()) {
            case "verreBiere" :
                Main.p.getCommandeEnCours().addBoisson(new Biere(new Verre(Verre.typeVerre.CYLINDRE)));
                break;
            case "verreCocktail" :
                Main.p.getCommandeEnCours().addBoisson(new Cocktail(new Verre(Verre.typeVerre.ENV)));
                break;
        }
    }



    public void setOnCliked(MouseEvent event){
        ImageView selectBoisson = (ImageView)event.getSource();
        switch(selectBoisson.getId()){
            case "biereBlonde":
                for (Boisson b : Main.p.getCommandeEnCours().getCommande()
                     ) {
                    if (b.equals(new Biere(new Verre(Verre.typeVerre.CYLINDRE)))){
                        ((Biere)b).setTypeBiere(Biere.choixBiere.BLONDE);
                        servir(b);
                        break;
                    }
                }
                break;
            case "biereBrune":
                for (Boisson b : Main.p.getCommandeEnCours().getCommande()
                ) {
                    if (b.equals(new Biere(new Verre(Verre.typeVerre.CYLINDRE)))){
                        ((Biere)b).setTypeBiere(Biere.choixBiere.BRUNE);
                        servir(b);
                        break;
                    }
                }
                break;
            case "soda":
                servir(new Soda());
                break;
            case"cocktail":
                for (Boisson b : Main.p.getCommandeEnCours().getCommande()
                ) {
                    if (b.equals(new Cocktail(new Verre(Verre.typeVerre.ENV)))){
                        servir(b);
                    }
                }
                break;
        }
    }


    public void chrono() throws IOException {
        Timeline temps = new Timeline();
        Main.p.setTpsPartie(60);
        temps.setCycleCount(Timeline.INDEFINITE);
        if (temps!=null){
            temps.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.p.setTpsPartie(Main.p.getTpsPartie() - 1 );
                    if (Main.p.getTpsPartie() == 0){

                            temps.stop();


                }
            }
        });
        temps.getKeyFrames().add(frame);
        temps.playFromStart();

    }


}
