package Données;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RequestClient {


    private static Socket sockCreateAccount;
    private static Socket sockChatConnect;
    private static Gson gson = new Gson();

    public RequestClient() throws IOException {

    }

    public static Socket getSock() {
        return sockCreateAccount;
    }

    public static Socket getSockChatConnect() {
        return sockChatConnect;
    }


    public static boolean createAccount(String userName, String pseudo, String password) throws IOException {
        String requestAccount = RequestCode.CREATION_COMPTE+"*"+userName+"*"+pseudo+"*"+password;
        sockCreateAccount = new Socket("127.0.0.1",1515);

        //nous créons donc un flux en écriture
        BufferedOutputStream bos = new BufferedOutputStream(sockCreateAccount.getOutputStream());

        //nous écrivons notre requête
        bos.write(requestAccount.getBytes());

        //Vu que nous utilisons un buffer, nous devons utiliser la méthode flush afin que les données soient bien écrites et envoyées au serveur
        bos.flush();

        //On récupère maintenant la réponse du serveur dans un flux
        BufferedInputStream bis = new BufferedInputStream(sockCreateAccount.getInputStream());

        //Il ne nous reste plus qu'à le lire
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = bis.read(b);
        response = new String(b, 0, stream);

        return Boolean.parseBoolean(response);
    }

    public static Utilisateur chatConnect(String userName, String password) throws IOException {
        String requestConnect = RequestCode.CONNEXION_CHAT+"*"+userName+"*"+password;
        Utilisateur user;

        sockChatConnect = new Socket("127.0.0.1",1515);

        //nous créons donc un flux en écriture
        BufferedOutputStream bos1 = new BufferedOutputStream(sockChatConnect.getOutputStream());

        //nous écrivons notre requête
        bos1.write(requestConnect.getBytes());

        //Vu que nous utilisons un buffer, nous devons utiliser la méthode flush afin que les données soient bien écrites et envoyées au serveur
        bos1.flush();

        //On récupère maintenant la réponse du serveur dans un flux
        BufferedInputStream bis = new BufferedInputStream(sockChatConnect.getInputStream());

        //Il ne nous reste plus qu'à le lire
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = bis.read(b);
        response = new String(b, 0, stream);

        user = gson.fromJson(response, Utilisateur.class);

        return user;
    }
}
