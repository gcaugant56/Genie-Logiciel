package Donnees;


import java.util.*;

public class Groupe {
    List<Utilisateur> Groupe= new ArrayList<Utilisateur>();

    public Groupe(List<Utilisateur> groupe) {
        Groupe = groupe;
    }
    public List<Utilisateur> getGroupe() {
        return Groupe;
    }

    public void setGroupe(List<Utilisateur> groupe) {
        Groupe = groupe;
    }
}