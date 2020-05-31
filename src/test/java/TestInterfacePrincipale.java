import Donnees.Utilisateur;
import Interface.InterfaceConnexion;
import Interface.InterfacePrincipale;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestInterfacePrincipale {
    @Test
    public void testInterface() throws IOException {
        InterfaceConnexion test = new InterfaceConnexion();
        Utilisateur user = new Utilisateur("test", "test", "test");
        InterfacePrincipale test2 = new InterfacePrincipale(user);

    }
}
