package Singletons;

import Timer.ClientConnexion;
import com.google.gson.Gson;

public class Singletons {
    private static Gson gsonInstance;

    public static Gson getGsonInstance() {
        if(gsonInstance == null) {
            gsonInstance = new Gson();
        }
        return gsonInstance;
    }

}
