package Données;


import com.google.gson.annotations.SerializedName;

import java.util.*;


public class Utilisateur {
    @SerializedName("userName")
    String userName ;
    @SerializedName("pseudo")
    private String pseudo ;
    @SerializedName("password")
    private String password ;
    @SerializedName("Contacts")
    private ArrayList<Contacts> Contacts = new ArrayList<Contacts>();

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

    public ArrayList<Données.Contacts> getContacts() {
        return Contacts;
    }

    public void setContacts(ArrayList<Données.Contacts> contacts) {
        Contacts = contacts;
    }
}

