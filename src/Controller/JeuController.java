package Controller;

import Modele.Biere;
import Modele.Boisson;
import Modele.Cocktail;
import Modele.Soda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import sample.Main;

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
    Button newCommande;

    @FXML
    ListView<String> listCommande;

    ObservableList observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListCommande();
    }

    public void setListCommande(){
        observableList.removeAll(observableList);
        for (Boisson boisson: Main.p.getCommande().getCommande()
             ) {
            observableList.add(boisson.toString());
        }
    listCommande.getItems().addAll(observableList);

    }

    public void setButtonClick(MouseEvent event){
        Main.p.genererCommandes();
        listCommande.getItems().clear();
        setListCommande();
    }

    public void servir(Boisson b){
        for (Boisson  boisson :  Main.p.getCommande().getCommande()) {
                if (boisson.equals(b) && !boisson.isPrepare()){
                    System.out.println("test");
                    boisson.setPrepare(true);
                    break;
                }
        }
    }

    public void setOnCliked(MouseEvent event){
        ImageView selectBoisson = (ImageView)event.getSource();
        switch(selectBoisson.getId()){
            case "biereBlonde":
                servir(new Biere(Biere.choixBiere.BLONDE));
                break;
            case "biereBrune":
                servir(new Biere(Biere.choixBiere.BRUNE));
                break;
            case "soda":
                servir(new Soda());
                break;
            case"cocktail":
                servir(new Cocktail());
                break;
        }
    }


}
