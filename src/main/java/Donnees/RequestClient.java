package Donnees;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RequestClient {


    private static Socket sock = null;
    private static Gson gson = new Gson();

    public RequestClient() throws IOException {

    }

    public static Socket getSock() {
        return sock;
    }


    public static boolean createAccount(String userName, String pseudo, String password) throws IOException {
        String requestAccount = RequestCode.CREATION_COMPTE+"*"+userName+"*"+pseudo+"*"+password;
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

    public static Utilisateur chatConnect(String userName, String password) throws IOException {
        String requestConnect = RequestCode.CONNEXION_CHAT+"*"+userName+"*"+password;
        Utilisateur user;
        sock = new Socket("127.0.0.1",1515);

        BufferedOutputStream bos1 = new BufferedOutputStream(sock.getOutputStream());
        bos1.write(requestConnect.getBytes());
        bos1.flush();
        BufferedInputStream bis = new BufferedInputStream(sock.getInputStream());

        //Il ne nous reste plus qu'à le lire
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = bis.read(b);
        response = new String(b, 0, stream);

        user = gson.fromJson(response, Utilisateur.class);

        return user;
    }

    public static void chatDisconnect(String userName) throws IOException {
        String requestConnect = RequestCode.DECONNEXION+"*"+userName;

        BufferedOutputStream bos1 = new BufferedOutputStream(RequestClient.getSock().getOutputStream());
        bos1.write(requestConnect.getBytes());
        bos1.flush();
        RequestClient.getSock().close();

    }
}
