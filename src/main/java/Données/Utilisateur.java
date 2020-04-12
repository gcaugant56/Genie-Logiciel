package Données;

public class Utilisateur {
    private String userName;
    private String pseudo;
    private String password;
    private Message List = [];

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
