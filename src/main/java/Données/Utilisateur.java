package Données;

public class Utilisateur {
    private String nom;
    private String prenom;
    private int num;
    private String contacts;

    public Utilisateur (String nom, String prenom, String message){
        this.nom = nom;
        this.prenom = prenom;
        this.num = num;
        this.contacts = contacts;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getNum() {
        return num;
    }

    public String getContacts() {
        return contacts;
    }
}