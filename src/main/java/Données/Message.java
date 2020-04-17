package Données;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Message {
    @SerializedName("content")
    private String content;
    @SerializedName("destinataire")
    private String destinataire;
    @SerializedName("expediteur")
    private Utilisateur expediteur;
    @SerializedName("date")
    private Date date;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public Utilisateur getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Utilisateur expediteur) {
        this.expediteur = expediteur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

