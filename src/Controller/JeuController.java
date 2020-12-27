package Controller;

import Modele.*;
import Vue.Toast;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

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
    Label meilleurScore;

    @FXML
    ListView<String> listCommande;

    @FXML
    ListView<String> listIngredients;

    ObservableList observableIngredients = FXCollections.observableArrayList();

    ObservableList observableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        score.textProperty().bind(Bindings.convert(Main.p.scoreProperty()));
        tpsPartie.textProperty().bind(Bindings.convert(Main.p.tpsPartieProperty()));
        meilleurScore.textProperty().bind(Bindings.convert(Main.p.meilleurScoreProperty()));

        try {
            chrono();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.p.genererCommandes();
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
            case "verreMojito" :
                if (Main.p.getCommandeEnCours().searchCocktail().verifOrdreIngredient()){
                    servir(Main.p.getCommandeEnCours().searchCocktail(Cocktail.TypeCocktail.MOJITO));
                    Main.p.getCommandeEnCours().getCommande().remove(Main.p.getCommandeEnCours().searchCocktail(Cocktail.TypeCocktail.MOJITO));
                    Main.p.setEnPreparation(false);
                    setListIngredients();
                }
                else{
                    System.out.println("pas bon");
                    Stage stage = (Stage)((ImageView) event.getSource()).getScene().getWindow();
                    Toast.makeText(stage,"Rappel Ordre Mojito : Menthe - Citron - Glace - Rhum - Shaker",5000,500,500);
                    Main.p.setScore(Main.p.getScore() - 20);
                }
                break;
            case "verreMargarita" :
                if (Main.p.getCommandeEnCours().searchCocktail().verifOrdreIngredient()){
                    servir(Main.p.getCommandeEnCours().searchCocktail(Cocktail.TypeCocktail.MARGARITA));
                    Main.p.getCommandeEnCours().getCommande().remove(Main.p.getCommandeEnCours().searchCocktail(Cocktail.TypeCocktail.MARGARITA));
                    Main.p.setEnPreparation(false);
                    setListIngredients();
                }
                else{
                    Main.p.setScore(Main.p.getScore() - 20);
                    Stage stage = (Stage)((ImageView) event.getSource()).getScene().getWindow();
                    Toast.makeText(stage,"Rappel Ordre Margarita : Citron - Glace - Tequila - Shaker",5000,500,500);
                }
                break;
        }
    }



    public void clicIngredient(MouseEvent event) throws InterruptedException, IOException {
        ImageView selectIngredient = (ImageView)event.getSource();
        Cocktail cocktail;
        switch (selectIngredient.getId()){
            case "citron":
                if (!Main.p.isEnPreparation()){
                    cocktail = new Cocktail();
                    cocktail.addIngredient(new Ingredient(Ingredient.TypeIngredient.CITRON));
                    Main.p.setEnPreparation(true);
                    Main.p.getCommandeEnCours().getCommande().add(cocktail);
                }
                else{
                    cocktail = Main.p.getCommandeEnCours().searchCocktail();
                    if (cocktail !=null){
                       cocktail.addIngredient(new Ingredient(Ingredient.TypeIngredient.CITRON));
                    }
                    else{
                        System.out.println("Pas de cocktail");
                    }
                }
                break;
            case "menthe":

                if (!Main.p.isEnPreparation()){
                    cocktail = new Cocktail();
                    cocktail.addIngredient(new Ingredient(Ingredient.TypeIngredient.MENTHE));
                    Main.p.setEnPreparation(true);
                    Main.p.getCommandeEnCours().getCommande().add(cocktail);
                }
                else{
                    cocktail = Main.p.getCommandeEnCours().searchCocktail();
                    if (cocktail !=null){
                        cocktail.addIngredient(new Ingredient(Ingredient.TypeIngredient.MENTHE));
                    }
                    else{
                        System.out.println("Pas de cocktail");
                    }
                }

                break;
            case "glace" :
                if (Main.p.isEnPreparation()){
                    cocktail = Main.p.getCommandeEnCours().searchCocktail();
                    if (cocktail != null ){
                        cocktail.addIngredient(new Ingredient(Ingredient.TypeIngredient.GLACE));
                    }
                    else{
                        System.out.println("Pas de cocktail");
                    }

                }
                else{
                    cocktail = new Cocktail();
                    cocktail.addIngredient(new Ingredient(Ingredient.TypeIngredient.GLACE));
                    Main.p.setEnPreparation(true);
                    Main.p.getCommandeEnCours().getCommande().add(cocktail);
                }
                break;
            case "rhum" :
                if (Main.p.isEnPreparation()){
                    cocktail = Main.p.getCommandeEnCours().searchCocktail();
                    if (cocktail != null ){
                        cocktail.addIngredient(new Ingredient(Ingredient.TypeIngredient.RHUM));
                    }
                    else{
                        System.out.println("Pas de cocktail");
                    }
                }
                else{
                    cocktail = new Cocktail();
                    cocktail.addIngredient(new Ingredient(Ingredient.TypeIngredient.RHUM));
                    Main.p.setEnPreparation(true);
                    Main.p.getCommandeEnCours().getCommande().add(cocktail);
                }
                break;
            case "tequila" :
                if (Main.p.isEnPreparation()){
                    cocktail = Main.p.getCommandeEnCours().searchCocktail();
                    if (cocktail != null ){
                        cocktail.addIngredient(new Ingredient(Ingredient.TypeIngredient.TEQUILA));
                    }
                    else{
                        System.out.println("Pas de cocktail");
                    }

                }
                else{
                    cocktail = new Cocktail();
                    cocktail.addIngredient(new Ingredient(Ingredient.TypeIngredient.TEQUILA));
                    Main.p.setEnPreparation(true);
                    Main.p.getCommandeEnCours().getCommande().add(cocktail);
                }
                break;
            case "shaker" :
                if (Main.p.isEnPreparation()){
                    cocktail = Main.p.getCommandeEnCours().searchCocktail();
                    Scene Scene = new Scene(FXMLLoader.load(getClass().getResource("/Vue/Shaker.fxml")));
                    Stage newFenetre = new Stage();
                    newFenetre.setScene(Scene);
                    newFenetre.initOwner(Main.getPrimaryStage());
                    newFenetre.setResizable(false);
                    newFenetre.show();
                    PauseTransition pause = new PauseTransition(Duration.seconds(5));
                    pause.setOnFinished(e -> ((Stage)(newFenetre.getScene().getWindow())).close());
                    pause.play();
                    cocktail.setShake(true);

                }
                break;
            case "poubelle":
                Main.p.getCommandeEnCours().getCommande().remove(Main.p.getCommandeEnCours().searchCocktail());
                Main.p.setEnPreparation(false);
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
        Main.p.setTpsPartie(Main.p.getTpsPartie());
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
                            Scene Scene;
							try {
								Scene = new Scene(FXMLLoader.load(getClass().getResource("/Vue/Fin.fxml")));
								Stage newFenetre = new Stage();
	                            newFenetre.setScene(Scene);
	                            newFenetre.initOwner(Main.getPrimaryStage());
	                            newFenetre.setResizable(false);
	                            newFenetre.show();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                }
            }
        });
        temps.getKeyFrames().add(frame);
        temps.playFromStart();
    }


}
