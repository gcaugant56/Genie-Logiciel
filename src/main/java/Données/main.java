package Données;


import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class main {


    public static void main(String[] args){

        //Serialisation();
        Deserialisation();
    }

    private static void Serialisation(){
       // Utilisateur Utilisateur = new Utilisateur();
    }

    private static void Deserialisation(){

        File file = new File("Json.json");
        String path = file.getAbsolutePath();
        String json = " ";
        try (BufferedReader bufferedreader = new BufferedReader(new FileReader(path))) {
            String strCurrentLine;
            while ((strCurrentLine = bufferedreader.readLine()) != null) {
                json += strCurrentLine;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Racine Liste_Utilisateurs = new Gson().fromJson( json , Racine.class);

    }
}
