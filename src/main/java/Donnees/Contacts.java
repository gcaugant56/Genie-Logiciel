package Donnees;

import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

import java.util.*;

/**
 * Classe permettant de créer un objet contact
 */
public class Contacts {
    @SerializedName("pseudo")
    private String pseudo="";
    @SerializedName("userName")
    private String username="";
    @SerializedName("Message")
    private ArrayList<Message> Message = new ArrayList<Message>();

    /**
     * Constructeur de l'objet contacts
     * @param pseudo pseudo du contact
     * @param username Nom de compte du contact
     */
    public Contacts(String pseudo, String username) {
        this.pseudo = pseudo;
        this.username = username;
    }
    
    /**
     * Permettant de recupere le nom de compte d'un contact
     * @return nom de compte du contact
     */
    public Contacts(String nameOfGroup) {
        this.pseudo = nameOfGroup;
        this.username = nameOfGroup;
    }
    public String getUsername() {
        return username;
    }

    /**
     * Permettant de recupere le Pseudo d'un contact
     * @return pseudo du contact
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Recupere la liste des messages echanger avec un contact
     * @return Liste des objet messages
     */
    public ArrayList<Message> getMessage() {
        return Message;
    }

    /**
     * Ajoute un message a la liste des messages
     * @param message message a ajouter a la liste des messages
     */
    public void setMessage(Message message) {
        Message.add(message);
    }
}
