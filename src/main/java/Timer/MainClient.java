package Timer;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainClient {

    public static void main(String[] args) throws IOException {
        try{

            Thread t = new Thread(new ClientConnexion("127.0.0.1", 1515));
            t.start();
        }
        catch(Exception e)
        {
            System.out.println("erreur");
        }


    }
}
