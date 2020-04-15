package Données;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;


public class main {


    public static void main(String[] args){

        //Serialisation();
       Deserialisation();
    }


    private static void Deserialisation(){

        String nmdf = "//Users//MatteoQ//Documents//FinalTest//Testser.txt";
        String json = "";
        //String test = "{'Racine':[{'Utilisateur':[{'userName':'Matteo','pseudo':'Alloms','password':'1234','Message': [{'destinataire': {},'expediteur': {},'content': 'Salut je m'appelle Matteo','date': {}}]}]}]}";
        try (BufferedReader bufferedreader = new BufferedReader(new FileReader(nmdf))) {
            String strCurrentLine;
            while ((strCurrentLine = bufferedreader.readLine()) != null) {
                json = json + strCurrentLine;
                // System.out.println(strCurrentLine);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        //Type racinet = new TypeToken<ArrayList<Racine>>(){}.getType();

        //ArrayList<Racine> racine = new Gson().fromJson(json, racinet);

       // Racine[] racine = new Gson().fromJson(json, Racine[].class);

       Racine racine = new Gson().fromJson(test,Racine.class);
       // Utilisateur utilisateur = new Gson().fromJson(json, Utilisateur.class);
    }
}
