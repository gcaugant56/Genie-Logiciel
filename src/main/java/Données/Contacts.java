package Données;

import com.google.gson.annotations.SerializedName;

import java.util.*;

public class Contacts {
    @SerializedName("userName")
    private String userName="";
    @SerializedName("pseudo")
    private String pseudo="";
    @SerializedName("password")
    private String password="";
    @SerializedName("Message")
    private ArrayList<Message> Message = new ArrayList<Message>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Données.Message> getMessage() {
        return Message;
    }

    public void setMessage(ArrayList<Données.Message> message) {
        Message = message;
    }
}
