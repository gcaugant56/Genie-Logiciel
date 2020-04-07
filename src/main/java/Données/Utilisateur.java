package Données;

public class Utilisateur {
    private String nom;
    private String prenom;
    private int num;

    public Utilisateur (String nom, String prenom, String message){
        this.nom = nom;
        this.prenom = prenom;
        this.num = num;
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
}