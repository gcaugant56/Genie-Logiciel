package Interface;

        import Donnees.RequestClient;
        import Timer.MainServer;
        import org.junit.Test;
        import org.junit.jupiter.api.Assertions;

        import java.io.IOException;

        import static org.junit.jupiter.api.Assertions.*;

class InterfaceConnexionTest {

    @Test

    public void testLancementServeur(){
        assertEquals(true, MainServer.start());
    }

    public void testCreationCompte() throws IOException {
        assertEquals(true, RequestClient.createAccount("testUnitaire", "UnitTest", "motdepasse"));
    }

    // verifier le json

    public void testConnexionCompte(){
    }
}


