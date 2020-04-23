package Données;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.*;

public class main {




    public static void main(String[] args){

        Serialisation();
        Deserialisation();
    }

    public static boolean Serialisation(Racine File) throws IOException {

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();

        String jsone = gson.toJson(File);
        FileWriter fw = new FileWriter("./messagecomplete.json");
        fw.write(jsone);
        fw.close();
        return true;

    }

    public static Racine Deserialisation (String path) throws IOException {

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();

        String json = Files.readString(Paths.get(path));

        Racine File = gson.fromJson(json, Racine.class);

        return File;

    }
}
