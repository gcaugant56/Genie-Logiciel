package Données;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RequestClient {

    public RequestClient() {

    }

    public static boolean createAccount(String userName, String pseudo, String password) throws IOException {
        String requestAccount = RequestCode.CREATION_COMPTE+"*"+userName +"*"+pseudo+"*"+password;
        Socket sock = new Socket("127.0.0.1",1515);

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
}
