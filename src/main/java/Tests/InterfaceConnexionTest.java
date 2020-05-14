package Tests;

        import Donnees.RequestClient;
        import Timer.MainServer;
        import org.junit.jupiter.api.Test;
        import static org.junit.jupiter.api.Assertions.*;

        import java.io.IOException;

class InterfaceConnexionTest {

    @Test
    public void testLancementServeur(){
        assertEquals(true, MainServer.start());
    }

    @Test
    public void testCreationCompte() throws IOException {
        MainServer.start();
        assertEquals(true, RequestClient.createAccount("testUnitaire", "UnitTest", "motdepasse"));
    }

    // verifier le json pour voir si le compte à bien été créé

    public void testConnexionCompte(){
    }
}


