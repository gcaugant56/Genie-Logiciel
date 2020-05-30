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
        //lancement serveur
        //MainServer.start();
        boolean validationTrue = false;
        boolean validationFalse = false;
        boolean validationSuppr = false;
        String userName = "testUnitaire";

        //tests de création de compte
        assertEquals(true, RequestClient.createAccount(userName, "UnitTest", "motdepasse"));

        assertEquals(false, RequestClient.createAccount("testUnitaire", "UnitTest", "motdepasse"));

        assertEquals(false, RequestClient.createAccount("testUnitaire2", "UnitTest", "motdepasse"));

        assertEquals(false, RequestClient.createAccount("", "test", ""));

        //assertEquals(false, RequestClient.createAccount("", "", ""));

        // verification dans le Json de la création du compte 1 et de la non-création des autres
        Racine Json = Serializationmessage.Deserialization("Json.json");
        for(Utilisateur user: Json.getUtilisateur()){
            if (user.getUserName().equals("testUnitaire") && user.getPseudo().equals("UnitTest") ){
                validationTrue = true;
            }else if(user.getUserName().equals("testUnitaire2") || user.getPseudo().equals("test")){
                validationFalse = true;
            }
        }
        //tests associés à la lecture Json
        assertEquals(true, validationTrue);
        assertEquals(false, validationFalse);

        //suppression du compte créé précedemment
        RequestClient.SupprUser("testUnitaire", "UnitTest");

        Json = Serializationmessage.Deserialization("Json.json");
        for(Utilisateur user: Json.getUtilisateur()){
            if (user.getUserName().equals("testUnitaire") && user.getPseudo().equals("UnitTest")){
                validationSuppr = true;
            }
        }
        //test associé à la suppression
        assertEquals(true, validationSuppr);
    }

    @Test
    public void testConnexionCompte() throws IOException {
        MainServer.start();
        boolean validationConnexion = false;
        RequestClient.createAccount("compte1", "compte1", "compte1");
        Utilisateur userConnected = RequestClient.chatConnect("compte1", "compte1");

        assertNotNull(userConnected);

        if (userConnected.getUserName().equals("compte1") && userConnected.getPseudo().equals("compte1") && userConnected.getPassword().equals("compte1")){
            validationConnexion = true;
        }

        assertEquals(true, validationConnexion);
        RequestClient.SupprUser("compte1", "compte1");
    }
}


