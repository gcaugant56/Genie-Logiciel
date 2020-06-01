package Donnees;


import java.util.*;

/**
 * Classe racine elle conteint tout les objets du json
 */
public class Racine {

    ArrayList<Utilisateur> Utilisateur= new ArrayList<Utilisateur>();

    /**
     * Constructeur de l'objet Racine
     * @param utilisateur La liste es objet utilisateur existant
     */
    public Racine(ArrayList<Donnees.Utilisateur> utilisateur) {
        Utilisateur = utilisateur;
    }

    /**
     * Renvoi la liste des objet utilisateur
     * @return un ArrayList d'objet utilisateur
     */
    public ArrayList<Donnees.Utilisateur> getUtilisateur() {
        return Utilisateur;
    }

    /**
     * Ajout un utilisateur a la liste d'utilisateur existant
     * @param utilisateur l'utilisateur a ajouter
     */
    public void setUtilisateur(Utilisateur utilisateur) { Utilisateur.add(utilisateur);
    }
}
