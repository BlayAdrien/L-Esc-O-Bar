package Controller;

import Modele.*;
import Vue.Toast;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Duration;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

import java.awt.Graphics2D;

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
    Label scoreAtteindre;


    @FXML
    ListView<String> listCommande;

    @FXML
    ListView<String> listIngredients;

    ObservableList observableIngredients = FXCollections.observableArrayList();

    ObservableList observableList = FXCollections.observableArrayList();

    /**
     * Met en place les données de l'interface
     * Lie les propriétés de score, de temps, de meilleur score, et d'objectif de score.
     * Lance le chrono de la partie
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        score.textProperty().bind(Bindings.convert(Main.p.scoreProperty()));
        tpsPartie.textProperty().bind(Bindings.convert(Main.p.tpsPartieProperty()));
        meilleurScore.textProperty().bind(Bindings.convert(Main.p.meilleurScoreProperty()));
        scoreAtteindre.textProperty().bind(Bindings.convert(Main.p.scoreAAtteindreProperty()));



        try {
            chrono();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.p.genererCommandes();
        setListCommande();
    }


    /**
     * Met dans la ListView la commande en cours, les boissons qu'il reste à servir
     */
    public void setListCommande(){
        observableList.clear();
        listCommande.setOrientation(Orientation.HORIZONTAL);
        listCommande.setFocusTraversable(false);
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

    /**
     * Met dans la ListView les ingrédients pour un cocktail
     */
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

    /**
     * Sert la boisson en paramètre à la commande en cours si elle est présente
     * Regarde si la commande est entièrement préparé pour passer à une nouvelle commande
     * @param b
     */
    public void servir(Boisson b){
        boolean inCommande = false;
        for (Boisson  boisson :  Main.p.getCommande().getCommande()) {
                if (boisson.equals(b) && !boisson.isPrepare()){

                    switch (b.getName()){
                        case"BLONDE" :
                            if (((Biere)b).verifVerre()){
                                boisson.setPrepare(true);
                                Main.p.setScore(Main.p.getScore() + boisson.getScore());
                                inCommande = true;

                        }
                            else{
                                Main.p.setScore(Main.p.getScore() - Cocktail.VALEUR);
                                Stage stage = (Stage) biereBlonde.getScene().getWindow();
                                Toast.makeText(stage,"Mauvais Verre",3000,500,500);
                            }

                            break;
                        case"BRUNE" :
                            if (((Biere)b).verifVerre()){
                                boisson.setPrepare(true);
                                Main.p.setScore(Main.p.getScore() + boisson.getScore());
                                inCommande = true;

                            }
                            else{
                                Main.p.setScore(Main.p.getScore() - Cocktail.VALEUR);
                                Stage stage = (Stage) biereBlonde.getScene().getWindow();
                                Toast.makeText(stage,"Mauvais Verre",3000,500,500);
                            }

                            break;
                        case"MOJITO":
                            if (((Cocktail)b).verifVerre()){
                                boisson.setPrepare(true);
                                Main.p.setScore(Main.p.getScore() + boisson.getScore());
                                inCommande = true;
                            }
                            else{
                                Main.p.setScore(Main.p.getScore() - Cocktail.VALEUR);
                                Stage stage = (Stage) biereBlonde.getScene().getWindow();
                                Toast.makeText(stage,"Mauvais Verre",3000,500,500);
                            }
                            break;

                        case"MARGARITA":
                            if (((Cocktail)b).verifVerre()){
                                boisson.setPrepare(true);
                                Main.p.setScore(Main.p.getScore() + boisson.getScore());
                                inCommande = true;
                            }
                            else{
                                Main.p.setScore(Main.p.getScore() - Cocktail.VALEUR);
                                Stage stage = (Stage) biereBlonde.getScene().getWindow();
                                Toast.makeText(stage,"Mauvais Verre",3000,500,500);
                            }
                            break;
                        case"Soda":
                            boisson.setPrepare(true);
                            Main.p.setScore(Main.p.getScore() + boisson.getScore());
                            inCommande = true;
                            break;
                    }




                    if (Main.p.getCommande().verifCommande()){
                        Main.p.setScore(Main.p.getScore() + 50);
                        Main.p.genererCommandes();
                    }
                    break;
                }
        }
        if (!inCommande){
            Main.p.setScore(Main.p.getScore() - b.getScore());

        }
        listCommande.getItems().clear();
        setListCommande();
    }

    /**
     * Fonction appelé lors du clic sur un verre
     * Ajoute une biere dans la commande en cours avec le bon verre pour un clic sur un verre à bière
     * Pour un clic sur un verre à cocktail, si bon ordre dans les ingredients sert le cocktail sinon affiche un message de rappel
     * @param event
     */
    public void clicVerre(MouseEvent event){
        ImageView selectVerre = (ImageView)event.getSource();
        switch(selectVerre.getId()) {
            case "verreBiere" :
                Main.p.getCommandeEnCours().addBoisson(new Biere(new Verre(Verre.typeVerre.CYLINDRE)));
                break;
            case "verreMojito" :
                Cocktail mojito = Main.p.getCommandeEnCours().searchCocktail();

                mojito.getVerre().setType(Verre.typeVerre.ENU);

                    if (mojito.verifOrdreIngredient()){
                        servir(Main.p.getCommandeEnCours().searchCocktail());
                        Main.p.getCommandeEnCours().getCommande().remove(Main.p.getCommandeEnCours().searchCocktail(Cocktail.TypeCocktail.MOJITO));
                        Main.p.setEnPreparation(false);
                        setListIngredients();
                    }
                    else{
                        System.out.println("pas bon");
                        Stage stage = (Stage)((ImageView) event.getSource()).getScene().getWindow();
                        Toast.makeText(stage,"Rappel Ordre Mojito : Menthe - Citron - Glace - Rhum - Shaker",5000,500,500);
                        Main.p.setScore(Main.p.getScore() - Cocktail.VALEUR);
                    }


                break;
            case "verreMargarita" :
                Cocktail margarita = Main.p.getCommandeEnCours().searchCocktail();
                margarita.getVerre().setType(Verre.typeVerre.ENV);

                    if (margarita.verifOrdreIngredient()){
                        servir(Main.p.getCommandeEnCours().searchCocktail());
                        Main.p.getCommandeEnCours().getCommande().remove(Main.p.getCommandeEnCours().searchCocktail(Cocktail.TypeCocktail.MARGARITA));
                        Main.p.setEnPreparation(false);
                        setListIngredients();
                    }
                    else{
                        Main.p.setScore(Main.p.getScore() - Cocktail.VALEUR);
                        Stage stage = (Stage)((ImageView) event.getSource()).getScene().getWindow();
                        Toast.makeText(stage,"Rappel Ordre Margarita : Citron - Glace - Tequila - Shaker",5000,500,500);
                    }


                break;
        }
    }

    /**
     * Fonction appelé lors du clic sur un ingrédient
     * Ajoute a liste d'ingredient d'un cocktail de la commande en cours
     * Supprime le contenue de la liste d'ingredient lors du clic sur la poubelle
     * @param event
     * @throws InterruptedException
     * @throws IOException
     */
    public void clicIngredient(MouseEvent event) throws InterruptedException, IOException {
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
                    cocktail = Main.p.getCommandeEnCours().searchCocktail();
                    if (cocktail != null){
                       cocktail.addIngredient(new Ingredient(Ingredient.TypeIngredient.CITRON));
                    }
                    else{
                        System.out.println("Pas de cocktail");
                    }
                }
                break;
            case "menthe":

                if (!Main.p.isEnPreparation()){
                    cocktail = new Cocktail(Cocktail.TypeCocktail.MOJITO);
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
                    PauseTransition pause = new PauseTransition(Duration.seconds(3));
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


    /**
     * Fonction appelé ors du clic sur la tireuse ou le friigo
     * Elle sert alors une biere ou un soda selon le clic
     * @param event
     */
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
        }
    }

    /**
     * Gestion du temps de la partie, affiche une nouvelle fenêtre lorsque le temps est fini.
     * @throws IOException
     */
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
                            if (Main.p.getScore() < Main.p.getScoreAAtteindre()){
                                try {
                                    Scene = new Scene(FXMLLoader.load(getClass().getResource("/Vue/FinDefaite.fxml")));
                                    Stage stage = (Stage) biereBlonde.getScene().getWindow();
                                    stage.setScene(Scene);
                                    stage.show();

                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                            }
                            else{
                                try {
                                    Scene = new Scene(FXMLLoader.load(getClass().getResource("/Vue/FinVictoire.fxml")));
                                    Stage stage = (Stage) biereBlonde.getScene().getWindow();
                                    stage.setScene(Scene);

                                    stage.show();

                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }

                }
            }
        });
        temps.getKeyFrames().add(frame);
        temps.playFromStart();
    }


}
