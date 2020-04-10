package Données;

public class Message {
    private String Utilisateur;
    private String contenu;
    private String destinataire;
    private int numerod;
    private String Objet;


    public Message(String utilisateur, String message, String destinataire, int numerod) {
        this.Utilisateur = utilisateur;
        this.message = message;
        this.destinataire = destinataire;
        this.numerod = numerod;
    }

    public String getUtilisateur() {
        return Utilisateur;
    }

    public String getContenu() {
        return contenu;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public int getNumerod() {
        return numerod;
    }
}