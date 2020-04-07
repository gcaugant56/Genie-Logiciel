package Données:

public class Message {
    private String Utilisateur;
    private String message;
    private String destinataire;
    private int numerod;

    public Message(String utilisateur, String message, String destinataire, int numerod) {
        this.Utilisateur = utilisateur;
        this.message = message;
        this.destinataire = destinataire;
        this.numerod = numerod;
    }

    public String getUtilisateur() {
        return Utilisateur;
    }

    public String getMessage() {
        return message;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public int getNumerod() {
        return numerod;
    }
}