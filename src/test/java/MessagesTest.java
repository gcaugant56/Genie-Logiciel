import Donnees.Racine;
import Donnees.RequestClient;
import Donnees.Serializationmessage;
import Donnees.Utilisateur;
import Timer.MainServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;


public class MessagesTest {
    @Test
    public void envoiMessage() throws IOException, InterruptedException {
        //MainServer.start();
        RequestClient.createAccount("compte1", "compte1", "compte1");
        RequestClient.createAccount("compte2", "compte2", "compte2");
        RequestClient.chatConnect("compte1", "compte1");
        RequestClient.chatConnect("compte2", "compte2");
        TimeUnit.MILLISECONDS.sleep(100);
        RequestClient.addContact("compte1", "compte2");
        TimeUnit.MILLISECONDS.sleep(100);
        RequestClient.addContact("compte2", "compte1");
        TimeUnit.MILLISECONDS.sleep(100);
        RequestClient.SendMsg("compte1", "compte2", "test message 1");
        TimeUnit.MILLISECONDS.sleep(100);
        RequestClient.SendMsg("compte2", "compte1", "test message 2");
        TimeUnit.MILLISECONDS.sleep(100);

        Racine Json = Serializationmessage.Deserialization("Json.json");
        assertEquals("test message 1", Json.getUtilisateur().get(1).getContacts().get(0).getMessage().get(0).getContent());
        assertEquals("test message 2", Json.getUtilisateur().get(2).getContacts().get(0).getMessage().get(1).getContent());

    }

    @Test
    public void suppressionMsg() throws IOException, InterruptedException {
        //MainServer.start();
        RequestClient.chatConnect("compte1", "compte1");
        TimeUnit.MILLISECONDS.sleep(500);
        RequestClient.SupprMsg("compte2", "compte1");
        TimeUnit.MILLISECONDS.sleep(500);
        boolean validation = false;
        Racine Json = Serializationmessage.Deserialization("Json.json");
        if (Json.getUtilisateur().get(1).getContacts().get(0).getMessage().isEmpty()){
            validation = true;
        }

        assertEquals(true, validation);

        RequestClient.SupprUser("compte1", "compte1");
        TimeUnit.MILLISECONDS.sleep(100);
        RequestClient.SupprUser("compte2", "compte2");
        TimeUnit.MILLISECONDS.sleep(100);
    }
}
