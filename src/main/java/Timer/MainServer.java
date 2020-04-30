package Timer;

public class MainServer {

    public static void main(String[] args){
        String host = "127.0.0.1";
        int port = 2345;

        TimeServer ts = new TimeServer(host, port);
        ts.open();

        System.out.println("Serveur initialisé.");

    }
}
