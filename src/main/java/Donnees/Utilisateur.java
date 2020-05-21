package Donnees;


import com.google.gson.annotations.SerializedName;

import java.util.*;

/**
 * Class regroupant les informations sur un utilisateur
 */
public class Utilisateur {
    @SerializedName("userName")
    private String userName ;
    @SerializedName("pseudo")
    private String pseudo ;
    @SerializedName("password")
    private String password ;
    @SerializedName("Contacts")
    private ArrayList<Contacts> Contacts = new ArrayList<Contacts>();

    /**
     * Constructeur de l'objet utilisateur
     * @param userName Nom de compte de l'utilisateur
     * @param pseudo Pseudo de l'utilisateur
     * @param password Mot de passe de l'utilisateur
     */
    public Utilisateur(String userName, String pseudo, String password) {
        this.userName = userName;
        this.pseudo = pseudo;
        this.password = password;
    }


    /**
     * Renvoie le nom de compte de l'utilisateur
     * @return une chaine de charactère contenant le nom de compte
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Permet de changer le nom de compte de l'utilisateur
     * @param userName le nouveau nom de compte souhaité
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Permet de recuperer le pseudo d'un utilisateur
     * @return Une chaine de charactère contenant le pseudo de l'utilisateur
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Permet de changer le pseudo de l'utilisateur
     * @param pseudo le nouveau pseudo souhaité
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Permet de recuperer le mot de pass de l'utilisateur
     * @return Chaine de charactère contenant le mot de passe
     */
    public String getPassword() {
        return password;
    }

    /**
     * Permet de changer le mot de pass de l'utilisateur
     * @param password le nouveau mot de passe souhaité
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Permet de recuperer le pseudo de l'utilisateur
     * @return Un array list d'objet contact
     */
    public ArrayList<Donnees.Contacts> getContacts() {
        return Contacts;
    }

    /**
     * Permet de changer le pseudo de l'utilisateur
     * @param contacts le contact a ajouté
     */
    public void setContacts(Contacts contacts) {
        Contacts.add(contacts);
    }

}

