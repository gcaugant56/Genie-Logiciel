package Donnees;

/**
 * Enumeration permettant de définir des codes pour la communication entre le client et le serveur
 */
public enum RequestCode {

    CREATION_COMPTE(1),
    CONNEXION_CHAT(2),
    DECONNEXION(3),
    ENVOI_MSG(4),
    MODIF_MDP(5),
    MODIF_USERNAME(6),
    AJOUT_CONTACT(7),
    CREATION_GROUP(8),
    ENVOI_GROUP(9),
    DEMANDE_LISTE(10),
    Historique_Message(11),
    Suppression_Message(12),
    Suppression_Compte(13);

    private int numero;


    RequestCode(int numero) {
        this.numero = numero;
    }

    public String toString() {
        return Integer.toString(this.numero);
    }

}
