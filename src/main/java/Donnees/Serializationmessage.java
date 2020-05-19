package Donnees;


import com.google.gson.Gson;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.*;

public class Serializationmessage {


    public static boolean Serialization(Racine File, String path) throws IOException {

        if (File !=null) {

            try {
                String jsone = new Gson().toJson(File);
                FileWriter fw = new FileWriter(Paths.get(path).toString());
                fw.write(jsone);
                fw.close();
                return true;
            } catch (IOException e) {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public static Racine Deserialization (String path) throws IOException {

        try{

        String json = Files.readString(Paths.get(path));

        Racine File = new Gson().fromJson(json, Racine.class);

        return File;}
        catch (IOException e){
            return null;
        }
    }
}
