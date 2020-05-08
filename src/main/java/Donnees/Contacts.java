package Donnees;

import com.google.gson.annotations.SerializedName;

import java.util.*;

public class Contacts {
    @SerializedName("pseudo")
    private String pseudo="";
    private String userName;
    @SerializedName("Message")
    private ArrayList<Message> Message = new ArrayList<Message>();

    public Contacts( String userName, String pseudo)
    {
        this.userName = userName;
        this.pseudo = pseudo;
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
