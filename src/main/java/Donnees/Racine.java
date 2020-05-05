package Donnees;


import java.util.*;


public class Racine {

    ArrayList<Utilisateur> Utilisateur= new ArrayList<Utilisateur>();

    public Racine(ArrayList<Donnees.Utilisateur> utilisateur) {
        Utilisateur = utilisateur;
    }

    public ArrayList<Donnees.Utilisateur> getUtilisateur() {
        return Utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) { Utilisateur.add(utilisateur);
    }
}
