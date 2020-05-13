package Tests;

        import Donnees.RequestClient;
        import Timer.MainServer;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.Assertions;

        import java.io.IOException;

        import static org.junit.jupiter.api.Assertions.*;

class InterfaceConnexionTest {

    @Test

    public void testLancementServeur(){
        Assertions.assertEquals(true, MainServer.start());
    }

    @Test
    public void testCreationCompte() throws IOException {
        Assertions.assertEquals(true, RequestClient.createAccount("testUnitaire", "UnitTest", "motdepasse"));
    }

    // verifier le json

    public void testConnexionCompte(){
    }
}


