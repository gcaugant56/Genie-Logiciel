package Donnees;

import com.google.gson.annotations.SerializedName;

import java.util.*;

public class Contacts {
    @SerializedName("pseudo")
    private String pseudo="";
    @SerializedName("userName")
    private String username="";
    @SerializedName("Message")
    private ArrayList<Message> Message = new ArrayList<Message>();

    public Contacts(String pseudo, String username) {
        this.pseudo = pseudo;
        this.username = username;
    }

    public String getUsername() {
        return username;
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
