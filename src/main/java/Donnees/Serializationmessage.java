package Donnees;


import Singletons.Singletons;
import com.google.gson.Gson;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.*;

/**
 * Classe permettant de lire des données existante et d'écriture de nouvelle donnée
 */
public class Serializationmessage {

    /**
     * Serialize un objet racine dans un fichier .json pour le stocker
     * @param File Objet racine a enregistré dans le json
     * @param path Chemin du fichier .json dans lequel stocker l'obet
     * @return Renvoi true si l'objet est correcte enregistre
     * @throws IOException
     */
    public static boolean Serialization(Racine File, String path) throws IOException {

        String jsone = Singletons.getGsonInstance().toJson(File);
        FileWriter fw = new FileWriter(Paths.get(path).toString());
        fw.write(jsone);
        fw.close();
        return true;
    }

    /**
     * Deserialize le fichier pour recuperer un objet racine
     * @param path Le fichier .json a lire
     * @return le fichier .json convertie en objet Racine
     * @throws IOException
     */
    public static Racine Deserialization (String path) throws IOException {

        String json = Files.readString(Paths.get(path));
        Racine File = Singletons.getGsonInstance().fromJson(json, Racine.class);
        return File;
    }
}
