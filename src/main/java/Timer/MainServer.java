package Timer;

/**
 * Class principal du serveur, cette dernière lance une instance de TimeServer
 */

public class MainServer {

    public static boolean start(){
        String host = "127.0.0.1";
        int port = 1515;

        TimeServer ts = new TimeServer(host, port);
        ts.open();

        System.out.println("Serveur initialisé.");
        return true;
    }
}
