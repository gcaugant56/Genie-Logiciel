package Donnees;

import Singletons.Singletons;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Classe regroupant tout les requetes que le client envoie au serveur
 */
public class RequestClient {


    private static Socket sock = null;

    public RequestClient() throws IOException {

    }

    public static Socket getSock() {
        return sock;
    }

    /**
     * Requete permettant de créer un compte
     * @param userName Nom de compte du nouvel utilisateur
     * @param pseudo  Pseudo du nouvel utilisateur
     * @param password Mot de passe du nouvel utilisateur
     * @return Renvoie true si le serveur arrive a créer le comte sinon false
     * @throws IOException
     */
    public static boolean createAccount(String userName, String pseudo, String password) throws IOException {
        String requestAccount = RequestCode.CREATION_COMPTE+"*"+userName+"*"+pseudo+"*"+password;
        //sock = new Socket("92.89.87.230",2345);
        sock = new Socket("127.0.0.1",1515);
        //nous créons donc un flux en écriture
        BufferedOutputStream bos = new BufferedOutputStream(sock.getOutputStream());

        //nous écrivons notre requête
        bos.write(requestAccount.getBytes());

        //Vu que nous utilisons un buffer, nous devons utiliser la méthode flush afin que les données soient bien écrites et envoyées au serveur
        bos.flush();

        //On récupère maintenant la réponse du serveur dans un flux
        BufferedInputStream bis = new BufferedInputStream(sock.getInputStream());

        //Il ne nous reste plus qu'à le lire
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = bis.read(b);
        response = new String(b, 0, stream);

        return Boolean.parseBoolean(response);
    }

    /**
     * Requete permettant a un utilisateur de ce connecter
     * @param userName Nom de compte de l'utilisateur souhaitant se connecter
     * @param password Mot de passe de l'utilisateur souhaitant se connecter
     * @return Renvoi un l'objet de l'utilisateur venant de se connecter
     * @throws IOException
     */
    public static Utilisateur chatConnect(String userName, String password) throws IOException {
        String requestConnect = RequestCode.CONNEXION_CHAT+"*"+userName+"*"+password;
        Utilisateur user;
        //sock = new Socket("92.89.87.230",2345);
        sock = new Socket("127.0.0.1",1515);

        BufferedOutputStream bos1 = new BufferedOutputStream(sock.getOutputStream());
        bos1.write(requestConnect.getBytes());
        bos1.flush();
        BufferedInputStream bis = new BufferedInputStream(sock.getInputStream());

        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = bis.read(b);
        response = new String(b, 0, stream);

        user = Singletons.getGsonInstance().fromJson(response, Utilisateur.class);

        return user;
    }

    /**
     * Requete permettant de se deconnecter
     * @param userName Nom de comte de l'utilisateur qui souhaite ce deconnecter
     * @throws IOException
     */
    public static void chatDisconnect(String userName) throws IOException {
        String requestConnect = RequestCode.DECONNEXION+"*"+userName;

        BufferedOutputStream bos1 = new BufferedOutputStream(RequestClient.getSock().getOutputStream());
        bos1.write(requestConnect.getBytes());
        bos1.flush();
        RequestClient.getSock().close();

    }

    /**
     * Requete permettant modifier le mot de passe d'un utilisateur
     * @param userName Nom de compte de l'utilisateur
     * @param oldPassword Ancien mot de passe
     * @param newPassword Nouveau mot de passe
     * @throws IOException
     */
    public static void checkPassword(String userName, String oldPassword, String newPassword) throws IOException {
        String requestConnect = RequestCode.MODIF_MDP+"*"+userName+"*"+oldPassword+"*"+newPassword;

        BufferedOutputStream bos1 = new BufferedOutputStream(sock.getOutputStream());
        bos1.write(requestConnect.getBytes());
        bos1.flush();

    }

    /**
     * Requete permettant de changer le pseudo
     * @param username Nom de compte de l'utilisateur
     * @param newPseudo Nouveau pseudo de l'utilisateur
     * @throws IOException
     */
    public static void checkPseudo(String username, String newPseudo) throws IOException {
        String requestConnect = RequestCode.MODIF_USERNAME+"*"+username+"*"+newPseudo;
        Utilisateur user;

        BufferedOutputStream bos1 = new BufferedOutputStream(sock.getOutputStream());
        bos1.write(requestConnect.getBytes());
        bos1.flush();
    }

    /**
     * Requete permettant de recuperer la liste des contacts
     * @param username Nom de compte de l'utilisateur
     * @throws IOException
     */
    public static void askListContact(String username) throws IOException {
        String requestConnect = RequestCode.DEMANDE_LISTE+"*"+username;
        Utilisateur user;

        BufferedOutputStream bos1 = new BufferedOutputStream(sock.getOutputStream());
        bos1.write(requestConnect.getBytes());
        bos1.flush();
    }

    /**
     * Requete pour ajouter un contact a un utilisateur
     * @param username Nom de compte de l'utilisateur
     * @param contactUsermame Pseudo de l'utilisateur a ajouter en contact
     * @throws IOException
     */
    public static void addContact(String username, String contactUsermame) throws IOException {
        String requestConnect = RequestCode.AJOUT_CONTACT+"*"+username+"*"+contactUsermame;
        Utilisateur user;

        BufferedOutputStream bos1 = new BufferedOutputStream(sock.getOutputStream());
        bos1.write(requestConnect.getBytes());
        bos1.flush();

    }

    /**
     * Requete qui permet d'envoyer un message a un contact
     * @param username Nom de compte de l'utilisateur
     * @param destinataire Pseudo du destinataire
     * @param msg Message a envoyé
     * @throws IOException
     */
    public static void SendMsg(String username, String destinataire, String msg) throws IOException {
        String requestsMsg = RequestCode.ENVOI_MSG+"*"+username+"*"+destinataire+"*"+msg;
        BufferedOutputStream bos1 = new BufferedOutputStream(sock.getOutputStream());
        bos1.write(requestsMsg.getBytes());
        bos1.flush();
    }

    /**
     * Requete permettant de recupere l'historique des messages envoyé entre un utilisateur et un contact
     * @param username Nom de compte de l'utilisateur
     * @param destinataire Pseudo du contact
     * @throws IOException
     */
    public static void GetMsgHistory(String username, String destinataire) throws IOException {
        String requestsMsg = RequestCode.Historique_Message+"*"+username+"*"+destinataire;
        BufferedOutputStream bos1 = new BufferedOutputStream(sock.getOutputStream());
        bos1.write(requestsMsg.getBytes());
        bos1.flush();
    }

    public static void SupprUser(String username, String pseudo) throws IOException {
        String requestsMsg = RequestCode.Suppression_Compte+"*"+username+"*"+pseudo;
        BufferedOutputStream bos1 = new BufferedOutputStream(sock.getOutputStream());
        bos1.write(requestsMsg.getBytes());
        bos1.flush();
    }

    public static void SupprMsg(String pseudo, String username) throws IOException {
        String requestsMsg = RequestCode.Suppression_Message+"*"+username+"*"+pseudo;
        BufferedOutputStream bos1 = new BufferedOutputStream(sock.getOutputStream());
        bos1.write(requestsMsg.getBytes());
        bos1.flush();
    }
}
