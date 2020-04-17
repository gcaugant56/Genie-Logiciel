package Données;


import java.util.*;

public class Groupe {
    List<Utilisateur> Groupe= new ArrayList<Utilisateur>();

    public List<Utilisateur> getGroupe() {
        return Groupe;
    }

    public void setGroupe(List<Utilisateur> groupe) {
        Groupe = groupe;
    }
}