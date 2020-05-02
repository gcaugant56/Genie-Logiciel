package Donnees;


import com.google.gson.annotations.SerializedName;

import java.util.*;


public class Utilisateur {
    @SerializedName("userName")
    private String userName ;
    @SerializedName("pseudo")
    private String pseudo ;
    @SerializedName("password")
    private String password ;
    @SerializedName("Contacts")
    private ArrayList<Contacts> Contacts = new ArrayList<Contacts>();


    public Utilisateur(String userName, String pseudo, String password) {
        this.userName = userName;
        this.pseudo = pseudo;
        this.password = password;
    }



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

    public ArrayList<Donnees.Contacts> getContacts() {
        return Contacts;
    }

    public void setContacts(ArrayList<Donnees.Contacts> contacts) {
        Contacts = contacts;
    }
}

