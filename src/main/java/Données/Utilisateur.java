package Données;

import java.util.ArrayList;

public class Utilisateur {
    private String userName;
    private String pseudo;
    private String password;

    public String getUserName() {
        return userName;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
