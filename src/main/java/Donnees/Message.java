package Donnees;

import com.google.gson.annotations.SerializedName;

/**
 * Objet message
 */
public class Message {
    @SerializedName("content")
    private String content;
    @SerializedName("destinataire")
    private String destinataire;
    @SerializedName("expediteur")
    private String expediteur;

    /**
     * Constructeur de l'objet Message
     * @param content Contenu du message
     * @param destinataire Destinateir du message
     * @param expediteur Expediteur du message
     */
    public Message(String content, String destinataire, String expediteur) {
        this.content = content;
        this.destinataire = destinataire;
        this.expediteur = expediteur;
    }

    /**
     * Permet de recupere le contenu d'un message
     * @return Contenu d'un message
     */
    public String getContent() {
        return content;
    }

}

