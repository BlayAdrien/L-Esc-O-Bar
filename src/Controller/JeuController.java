package Controller;

import Modele.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    ListView<String> listIngredients;

    ObservableList observableIngredients = FXCollections.observableArrayList();

    ObservableList observableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //score.textProperty().bind(Bindings.convert(Main.p.scoreProperty()));
       // tpsPartie.textProperty().bind(Bindings.convert(Main.p.tpsPartieProperty()));
        try {
            chrono();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setListCommande();
    }

    public void setListCommande(){
        observableList.clear();
        listCommande.setOrientation(Orientation.HORIZONTAL);
        //listCommande.setMouseTransparent(true);
        listCommande.setFocusTraversable(false);
        //commandeEnCours.setOrientation(Orientation.HORIZONTAL);
        for (Boisson boisson: Main.p.getCommande().getCommande()
             ) {
            if (!boisson.isPrepare()){
                observableList.add(boisson.getName());
            }
        }

        listCommande.setItems(observableList);
        listCommande.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();

            private Image image;
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name,empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                }
                else{
                    switch (name){
                        case "BLONDE" :
                            image = new Image(getClass().getResource("img/biere_blonde.jpg").toExternalForm());
                            imageView.setImage(image);
                            break;
                        case "BRUNE" :
                            image = new Image(getClass().getResource("img/biere_brune.jpg").toExternalForm());
                            imageView.setImage(image);
                            break;
                        case "Soda" :
                            image = new Image(getClass().getResource("img/soda.jpg").toExternalForm());
                            imageView.setImage(image);
                            break;
                        case "MOJITO" :
                            image = new Image(getClass().getResource("img/mojito.jpg").toExternalForm());
                            imageView.setImage(image);
                            break;
                        case"MARGARITA" :
                            image = new Image(getClass().getResource("img/margarita.jpg").toExternalForm());
                            imageView.setImage(image);
                            break;

                    }
                    imageView.setFitHeight(100);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
            }
        });
    }

    public void setListIngredients(){
        observableIngredients.clear();
        for (Boisson b: Main.p.getCommandeEnCours().getCommande()
             ) {
            if (b instanceof Cocktail){
                for (Ingredient i : ((Cocktail)b).getIngredients()
                     ) {
                    observableIngredients.add(i.toString());
                }
            }
        }
        listIngredients.setItems(observableIngredients);
    }


    public void servir(Boisson b){
        for (Boisson  boisson :  Main.p.getCommande().getCommande()) {
                if (boisson.equals(b) && !boisson.isPrepare()){
                    boisson.setPrepare(true);
                    Main.p.setScore(Main.p.getScore() + boisson.getScore());
                    if (Main.p.getCommande().verifCommande()){
                        Main.p.genererCommandes();
                    }
                    break;
                }
        }
        listCommande.getItems().clear();
        setListCommande();
    }

    public void clicVerre(MouseEvent event){
        ImageView selectVerre = (ImageView)event.getSource();
        switch(selectVerre.getId()) {
            case "verreBiere" :
                Main.p.getCommandeEnCours().addBoisson(new Biere(new Verre(Verre.typeVerre.CYLINDRE)));
                break;
            case "verreCocktail" :
                servir(Main.p.getCommandeEnCours().searchCocktail(Cocktail.TypeCocktail.MOJITO));
                break;
        }
    }



    public void clicIngredient(MouseEvent event){
        ImageView selectIngredient = (ImageView)event.getSource();
        Cocktail cocktail;
        switch (selectIngredient.getId()){
            case "citron":
                if (!Main.p.isEnPreparation()){
                    cocktail = new Cocktail(Cocktail.TypeCocktail.MARGARITA);
                    cocktail.addIngredient(new Ingredient(Ingredient.TypeIngredient.CITRON));
                    Main.p.setEnPreparation(true);
                    Main.p.getCommandeEnCours().getCommande().add(cocktail);
                }
                else{
                    cocktail = Main.p.getCommandeEnCours().searchCocktail(Cocktail.TypeCocktail.MOJITO);
                    if (cocktail !=null){
                        if (cocktail.verifOrdreIngredient(new Ingredient(Ingredient.TypeIngredient.CITRON))){
                            break;
                        }
                    }
                }

               // .addIngredient(new Ingredient(Ingredient.TypeIngredient.CITRON))
                break;
            case "menthe":

                if (!Main.p.isEnPreparation()){
                    cocktail = new Cocktail(Cocktail.TypeCocktail.MOJITO);
                    cocktail.addIngredient(new Ingredient(Ingredient.TypeIngredient.MENTHE));
                    Main.p.setEnPreparation(true);
                    Main.p.getCommandeEnCours().getCommande().add(cocktail);
                }

                break;
            case "glace" :
                if (Main.p.isEnPreparation()){
                    cocktail = Main.p.getCommandeEnCours().searchCocktail();
                    if(cocktail.verifOrdreIngredient(new Ingredient(Ingredient.TypeIngredient.GLACE))){
                        break;
                    }

                }
                break;
            case "rhum" :
                if (Main.p.isEnPreparation()){
                    cocktail = Main.p.getCommandeEnCours().searchCocktail(Cocktail.TypeCocktail.MOJITO);
                    if(cocktail.verifOrdreIngredient(new Ingredient(Ingredient.TypeIngredient.RHUM))){
                        break;
                    }
                }
                break;
            case "tequila" :
                if (Main.p.isEnPreparation()){
                    cocktail = Main.p.getCommandeEnCours().searchCocktail(Cocktail.TypeCocktail.MARGARITA);
                    if(cocktail.verifOrdreIngredient(new Ingredient(Ingredient.TypeIngredient.TEQUILA))){
                        break;
                    }
                }
                break;
            case "shaker" :
                if (Main.p.isEnPreparation()){
                    cocktail = Main.p.getCommandeEnCours().searchCocktail();
                }
        }
        setListIngredients();
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
