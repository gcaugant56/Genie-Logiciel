package Timer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Hashtable;

/**
 * TimerServer est le serveur principal,
 * il créer un socket, accepte toute les connexions entrantes.
 * Pour chaque connexion entrante un thread est lancer avec une instance de ClientProcessor
 */
public class TimeServer {

    //On initialise des valeurs par défaut
    private int port = 2345;
    private String host = "192.168.1.22";
    private ServerSocket server = null;
    private boolean isRunning = true;
    Hashtable dic = new Hashtable();

    /**
     * Constructeur de l'objet TimerServeur, il créer un socket. En utilisant des valeur par défaut
     */
    public TimeServer() {
        try {
            server = new ServerSocket(port, 100, InetAddress.getByName(host));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *      * Constructeur de l'objet TimerServeur, il créer un socket.
     * @param pHost IP du socket
     * @param pPort port du socket
     */
    public TimeServer(String pHost, int pPort) {
        host = pHost;
        port = pPort;
        try {

            server = new ServerSocket(port, 100, InetAddress.getByName(host));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ecoute en continu, si un client ce connecte  la connexion est accepter et un objet ClientProcessor est créer
     */
    //On lance notre serveur
    public void open() {

        //Toujours dans un thread à part vu qu'il est dans une boucle infinie
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (isRunning == true) {

                    try {
                        //On attend une connexion d'un client
                        Socket client = server.accept();

                        //Une fois reçue, on la traite dans un thread séparé
                        System.out.println("Connexion cliente reçue.");
                        Thread t = new Thread(new ClientProcessor(client,dic));
                        t.start();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    server = null;
                }
            }
        });

        t.start();
    }

    /**
     * Arrete le serveur
     */
    public void close() {
        isRunning = false;
    }
}