package Modele;

import java.io.*;

public class SauvegardeGestion {

    public Partie charger(int niveau){
        Partie partie = null;

                try {
                    FileInputStream file = new FileInputStream(new File("partie"+niveau+".ser"));
                    ObjectInputStream in = new ObjectInputStream(file);
                    partie = (Partie) in.readObject();
                    int[] param = lectureParametreNiveau(niveau);

                    partie.setTpsPartie(param[0]);

                    in.close();
                    file.close();

                } catch (Exception e) {
                    System.out.println("La deserialisation a foiré ");
                    e.printStackTrace();
                    int[] param = lectureParametreNiveau(niveau);
                    partie = new Partie(param[0],niveau);
                }

        return partie;
    }

    private int[] lectureParametreNiveau(int niveau){
        int[] param = new int[10];
        try{
            FileReader fr = new FileReader("ressources/Niveaux/niveau"+niveau+".txt");
            BufferedReader br = new BufferedReader(fr);
            while(br.ready()){
                param[0] = Integer.parseInt(br.readLine());
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }

    public void sauvegarder(Partie partie){
        try {

            FileOutputStream file = new FileOutputStream
                    (new File("partie"+partie.getNiveau()+".ser"));
            ObjectOutputStream out = new ObjectOutputStream
                    (file);

            out.writeObject(partie);

            out.close();
            file.close();

        } catch (Exception e) {
            System.out.println("Erreur serialisation ");
            e.printStackTrace();
        }
    }
}
