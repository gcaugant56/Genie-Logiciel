package Timer;

import Donnees.*;
import Interface.InterfaceAccount;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe instancié du coté client, son but est lire en continu afin de déterminé si le serveur envoie une demande
 * au client
 */
public class ClientConnexion implements Runnable{

    private Socket connexion = null;
    private BufferedInputStream reader = null;
    private Utilisateur user;
    private String verdict;
    private JTextArea text;
    private JComboBox contact;

    /**
     * Constructeur de l'objet ClientConnexion
     * @param connexion Socket du client
     * @param user Objet de l'utilisateur connecté
     * @param text Fenetre principal de chat
     * @param contact Menu deroulant du choix des contacts
     */
    public ClientConnexion(Socket connexion, Utilisateur user,JTextArea text, JComboBox contact){
        this.connexion = connexion;
        this.user = user;
        this.text = text;
        this.contact = contact;
    }

    /**
     *
     */
    public void run(){
        while(true) {
            try {
                reader = new BufferedInputStream(connexion.getInputStream());
                verdict = "";
                String response = read();
                System.out.println(response);
                String[] tabResponse = response.split("\\*"); //permet de séparer le tableau par carractère
                RequestCode Code = RequestCode.values()[Integer.parseInt(tabResponse[0])-1];
                response = tabResponse[1];
                Racine Json = null;
                String json = "";
                switch (Code) {
                    case DECONNEXION:
                        break;
                    case ENVOI_MSG:
                        String expediteur = tabResponse[2];
                        if(expediteur.equals(contact.getSelectedItem()))
                        {
                            verdict = response;
                            text.append(response);
                        }

                        break;
                    case MODIF_MDP:
                        verdict = response;
                        break;
                    case MODIF_USERNAME:
                        verdict = response;
                        break;
                    case AJOUT_CONTACT:
                        verdict = response;
                        break;
                    case CREATION_GROUP:
                        break;
                    case DEMANDE_LISTE:
                        verdict = response;
                        System.out.println(verdict);
                        break;
                    case ENVOI_GROUP:
                        break;
                    case Historique_Message:
                        verdict = response;
                        break;
                }


            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    public String getVerdict()
    {
        return verdict;
    }
}
