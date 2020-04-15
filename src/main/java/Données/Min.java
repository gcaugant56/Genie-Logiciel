package Données;


import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Min {

    private static String nmdf = "//Users//MatteoQ//Documents//Serser//Testser.txt";
    private static String json;


    public static void main(String[] args){

        //Serialisation();
       Deserialisation();
    }


    private static void Deserialisation(){

        try (BufferedReader bufferedreader = new BufferedReader(new FileReader(nmdf))) {
            String strCurrentLine;
            while ((strCurrentLine = bufferedreader.readLine()) != null) {
                json = json + strCurrentLine;
                // System.out.println(strCurrentLine);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Racine racine = new Gson().fromJson(json, Racine.class);

    }
}
