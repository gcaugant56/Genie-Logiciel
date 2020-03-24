package Timer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

public class ClientConnexion implements Runnable{

    private Socket connexion = null;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;

    //Notre liste de commandes. Le serveur nous répondra différemment selon la commande utilisée.
    private String[] listCommands = {"FULL", "DATE", "HOUR", "NONE", "PROJET"};

    public ClientConnexion(String host, int port){
        try {
            connexion = new Socket(host, port);
            System.err.println("Connexion réussie.");
            System.err.println("Commandes disponibles : FULL, DATE, HOUR, PROJET, CLOSE");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run(){
        while(true) {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {

                writer = new PrintWriter(connexion.getOutputStream(), true);
                reader = new BufferedInputStream(connexion.getInputStream());
                //On envoie la commande au serveur

                Scanner scan = new Scanner(System.in);
                String commande = scan.next();
                writer.write(commande);
                //TOUJOURS UTILISER flush() POUR ENVOYER RÉELLEMENT DES INFOS AU SERVEUR
                writer.flush();
                System.out.println("Commande " + commande + " envoyée au serveur");
                //On attend la réponse
                String response = read();
                System.out.println("\t Réponse reçue : " + response);

            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //writer.write("CLOSE");
        //writer.flush();
        //writer.close();
    }

    //Méthode pour lire les réponses du serveur
    private String read() throws IOException{
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }
}
