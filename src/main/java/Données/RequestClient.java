package Données;

import java.io.IOException;

public interface RequestClient {
    public boolean createAccount() throws IOException;
    public boolean chatConnect();
    public boolean chatPlugDisconnect();
    public String sendMsg();
    public boolean modifPassword();
    public boolean modifUsername();
    public boolean addContact();
    public boolean createGroup();
}
