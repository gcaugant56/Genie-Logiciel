package Données;


import com.google.gson.annotations.SerializedName;

import java.util.*;


public class Racine {

    ArrayList<Utilisateur> Utilisateur= new ArrayList<Utilisateur>();

    public Racine(ArrayList<Données.Utilisateur> utilisateur) {
        Utilisateur = utilisateur;
    }

    public ArrayList<Données.Utilisateur> getUtilisateur() {
        return Utilisateur;
    }

    public void setUtilisateur(ArrayList<Données.Utilisateur> utilisateur) {
        Utilisateur = utilisateur;
    }
}
