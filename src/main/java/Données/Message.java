package Données;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("content")
    private String content;
    @SerializedName("destinataire")
    private String destinataire;
    @SerializedName("expediteur")
    private String expediteur;
    @SerializedName("date")
    private String date;

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

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

