package Données;

public class Message {
    private String content="";
    private Utilisateur destinataire;
    private Utilisateur expediteur;
    private Groupe destinataire;
    private date heure;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Utilisateur getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Groupe destinataire) {
        this.destinataire = destinataire;
    }

    public date getHeure() {
        return heure;
    }

    public void setHeure(date heure) {
        this.heure = heure;
    }

    public void setDestinataire(Utilisateur destinataire) {
        this.destinataire = destinataire;
    }

    public Utilisateur getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Utilisateur expediteur) {
        this.expediteur = expediteur;
    }
}