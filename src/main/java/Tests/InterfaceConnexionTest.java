package Tests;

        import Donnees.Racine;
        import Donnees.RequestClient;
        import Donnees.Serializationmessage;
        import Donnees.Utilisateur;
        import Timer.MainServer;
        import org.junit.jupiter.api.Test;
        import static org.junit.jupiter.api.Assertions.*;

        import java.io.IOException;
        import java.util.*;

class InterfaceConnexionTest {

    InterfaceConnexionTest() throws IOException {
    }

    @Test
    public void testLancementServeur(){
        assertEquals(true, MainServer.start());
    }

    @Test
    public void testCreationCompte() throws IOException {
        //MainServer.start();
        boolean validationTrue = false;
        boolean validationFalse = true;
        String userName = "testUnitaire";
        //ArrayList<Utilisateur> listUser = new ArrayList<Utilisateur>();
        //listUser = Serializationmessage.Deserialization("Json.json").getUtilisateur();

        assertEquals(true, RequestClient.createAccount(userName, "UnitTest", "motdepasse"));

        assertEquals(false, RequestClient.createAccount("testUnitaire", "UnitTest", "motdepasse"));

        assertEquals(false, RequestClient.createAccount("testUnitaire2", "UnitTest", "motdepasse"));

        assertEquals(false, RequestClient.createAccount("", "test", ""));

        //assertEquals(false, RequestClient.createAccount("", "", ""));

        // verifier le json pour voir si le compte à bien été créé
        Racine Json = Serializationmessage.Deserialization("Json.json");
        for(Utilisateur user: Json.getUtilisateur()){
            if (user.getUserName().equals("UnitTest")){
                validationTrue = true;
            }else if(user.getUserName().equals("testUnitaire") || user.getUserName().equals("testUnitaire2") || user.getPseudo().equals("test")){
                validationFalse = false;
            }
        }
        //tests d'écriture dans json
        assertEquals(true, validationTrue);
        assertEquals(false, validationFalse);
    }

    public void testConnexionCompte(){
    }
}


