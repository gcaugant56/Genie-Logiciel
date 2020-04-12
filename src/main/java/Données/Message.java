package Données;

public class Message {
    private String content="";
    private Utilisateur destinataire;
    private Utilisateur expediteur;
    private Groupe destinataire;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Utilisateur getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Utilisateur user) {
        this.destinataire = user;
    }

    public Groupe getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Groupe groupe) {
        this.destinataire = groupe;
    }

    public Utilisateur getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Utilisateur expediteur) {
        this.expediteur = expediteur;
    }
}