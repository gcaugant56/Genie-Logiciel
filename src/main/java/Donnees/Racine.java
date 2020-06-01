package Donnees;


import com.google.gson.annotations.SerializedName;

import java.util.*;


public class Racine {

    @SerializedName("Utilisateur")
    ArrayList<Utilisateur> utilisateurList = new ArrayList<Utilisateur>();
    @SerializedName("Groupe")
    ArrayList<Groupe> groupeList = new ArrayList<>();

    public Racine(ArrayList<Utilisateur> utilisateur, ArrayList<Groupe> groupe) {
        utilisateurList = utilisateur;
        groupeList = groupe;
    }

    public Racine(ArrayList<Utilisateur> utilisateur) {
        utilisateurList = utilisateur;
    }

    public ArrayList<Groupe> getGroupeList() {
        return groupeList;
    }

    public void setGroupeList(ArrayList<Groupe> groupeList) {
        this.groupeList = groupeList;
    }

    public ArrayList<Donnees.Utilisateur> getUtilisateur() {
        return utilisateurList;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        utilisateurList.add(utilisateur);
    }

    public void setGroupeList(Groupe groupe) {
        groupeList.add(groupe);
    }
}
