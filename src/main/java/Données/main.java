package Données;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class main {




    public static void main(String[] args){

        Serialisation();
        Deserialisation();
    }

    private static boolean Serialisation(File File) {

        boolean verif;

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();

        String jsone = gson.toJson(Racine);

        try
        {
            FileWriter fw = new FileWriter(File);
            BufferedWriter output = new BufferedWriter(fw);
            output.write(jsone);
            output.close();
            verif = true
        }
        catch(IOException ioe){
            ioe.printStackTrace();
            verif = false;
        }

        return verif;
    }

    private static  Racine Deserialisation(File File) {

        String path = File.getAbsolutePath();
        String json = " ";
        try (BufferedReader bufferedreader = new BufferedReader(new FileReader(path))) {
            String strCurrentLine;
            while ((strCurrentLine = bufferedreader.readLine()) != null) {
                json += strCurrentLine;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Racine Racine = new Gson().fromJson(json, Racine.class);

        return Racine;
    }
}
