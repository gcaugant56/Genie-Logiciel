package Donnees;

import com.google.gson.annotations.SerializedName;

import java.util.*;

public class Contacts {
    @SerializedName("pseudo")
    private String pseudo="";
    @SerializedName("Message")
    private ArrayList<Message> Message = new ArrayList<Message>();

    public Contacts(String pseudo,ArrayList<Donnees.Message> message) {
        this.pseudo = pseudo;
        Message = message;
    }


    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public ArrayList<Donnees.Message> getMessage() {
        return Message;
    }

    public void setMessage(ArrayList<Donnees.Message> message) {
        Message = message;
    }
}
