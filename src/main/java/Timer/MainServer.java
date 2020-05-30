package Timer;

public class MainServer {

    public static void main(String[] args){
        //String host = "127.0.0.1";
        //int port = 1515;

        TimeServer ts = new TimeServer();
        ts.open();

        System.out.println("Serveur initialisé.");
    }

    public static boolean start(){
        new MainServer();
        return true;
    }
}
