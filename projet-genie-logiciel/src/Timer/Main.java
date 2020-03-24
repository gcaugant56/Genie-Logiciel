package Timer;
public class Main {

    public static void main(String[] args) {

        Thread t = new Thread(new ClientConnexion("92.89.87.230", 2345));
        t.start();

    }
}
