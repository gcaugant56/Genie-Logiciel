package Timer;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainClient {

    public static void main(String[] args) throws IOException {
        try{

            Thread t = new Thread(new ClientConnexion("92.89.87.230", 2345));
            t.start();
        }
        catch(Exception e)
        {
            System.out.println("erreur");
        }


    }
}
