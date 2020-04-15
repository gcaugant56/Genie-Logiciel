package Données;


import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class main {


    public static void main(String[] args){

        //Serialisation();
       Deserialisation();
    }
    private static void Deserialisation(){

        String nmdf = "//Users//MatteoQ//Documents//FinalTest//Testser.txt";
        String json = "";
        try (BufferedReader bufferedreader = new BufferedReader(new FileReader(nmdf))) {
            String strCurrentLine;
            while ((strCurrentLine = bufferedreader.readLine()) != null) {
                json = json + strCurrentLine;

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Racine racine = new Gson().fromJson(json,Racine.class);
    }
}
