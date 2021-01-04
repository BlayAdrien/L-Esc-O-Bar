package Modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SauvegardeGestion {

    private SauvegardeGestion(){}

    private static SauvegardeGestion save =null;

    /**
     * retourne l'instance unique de la classe SauvegardeGestion
     * @return
     */
    public static SauvegardeGestion getSave()
    {
        if (save == null)
        {   save = new SauvegardeGestion();
        }
        return save;
    }

    /**
     * Charge une partie selon le niveau choisie
     * @param niveau
     * @return une partie avec les bons paramètres (Meilleur Score, Temps, Score a atteindre)
     */
    public Partie charger(int niveau){
        Partie partie = null;

                try {
                    FileInputStream file = new FileInputStream(new File("partie"+niveau+".ser"));
                    ObjectInputStream in = new ObjectInputStream(file);
                    partie = (Partie) in.readObject();
                    int[] param = lectureParametreNiveau(niveau);

                    partie.setTpsPartie(param[0]);
                    partie.setScoreAAtteindre(param[1]);
                    partie.getCommande().setNbBoissonMax(param[2]);
                    in.close();
                    file.close();

                } catch (Exception e) {
                    System.out.println("La deserialisation a foiré ");
                    e.printStackTrace();
                    int[] param = lectureParametreNiveau(niveau);
                    partie = new Partie(param[0],niveau);
                    partie.setScoreAAtteindre(param[1]);
                    partie.getCommande().setNbBoissonMax(param[2]);

                }

        return partie;
    }

    /**
     * Lit les parametres dans un fichier txt du niveau choisi
     * @param niveau
     * @return Tableau avec les parametres d'une partie
     */
    private int[] lectureParametreNiveau(int niveau){
        int[] param = new int[5];
        try{
            FileReader fr = new FileReader("ressources/Niveaux/niveau"+niveau+".txt");
            BufferedReader br = new BufferedReader(fr);
            int i = 0;
            while(br.ready()){
                param[i] = Integer.parseInt(br.readLine());
                i++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }

    /**
     * Sauvegrade la partie en cours
     * @param partie
     */
    public void sauvegarder(Partie partie){
        try {

            FileOutputStream file = new FileOutputStream
                    (new File("partie"+partie.getNiveau()+".ser"));
            ObjectOutputStream outFile = new ObjectOutputStream
                    (file);

            outFile.writeObject(partie);

            outFile.close();
            file.close();

        } catch (Exception e) {
            System.out.println("Erreur serialisation ");
            e.printStackTrace();
        }
    }
}
