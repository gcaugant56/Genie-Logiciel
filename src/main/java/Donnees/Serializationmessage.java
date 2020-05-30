package Donnees;


import Singletons.Singletons;
import com.google.gson.Gson;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.*;

public class Serializationmessage {


    public static boolean Serialization(Racine File, String path) throws IOException {

        String jsone = Singletons.getGsonInstance().toJson(File);
        FileWriter fw = new FileWriter(Paths.get(path).toString());
        fw.write(jsone);
        fw.close();
        return true;
    }

    public static Racine Deserialization (String path) throws IOException {

        String json = Files.readString(Paths.get(path));
        Racine File = Singletons.getGsonInstance().fromJson(json, Racine.class);
        return File;
    }
}
