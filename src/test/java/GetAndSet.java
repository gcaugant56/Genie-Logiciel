import Donnees.*;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class GetAndSet {

    @Test
    public void testUtilisateur(){
        Utilisateur test = new Utilisateur("test1", "test1", "test1");
        assertEquals("test1", test.getUserName());
        assertEquals("test1", test.getPseudo());
        assertEquals("test1", test.getPassword());
        test.setUserName("rename");
        test.setPseudo("rename2");
        test.setPassword("rename3");
        assertEquals("rename", test.getUserName());
        assertEquals("rename2", test.getPseudo());
        assertEquals("rename3", test.getPassword());
    }

    @Test
    public void testContact(){
        Utilisateur user = new Utilisateur("test1", "test1", "test1");
        Contacts contact = new Contacts("test2", "test2");
        user.setContacts(contact);
        assertEquals("test2", user.getContacts().get(0).getPseudo());
    }

    @Test
    public void testMessage(){
        Contacts contact = new Contacts("test1", "test1");
        Message message = new Message("ajout message", "test1", "IntelliJ");
        contact.setMessage(message);
        assertEquals("ajout message", contact.getMessage().get(0).getContent());
    }

    @Test
    public void testRacine(){
        Utilisateur user = new Utilisateur("test", "test", "test");
        ArrayList<Utilisateur> list = new ArrayList<>();
        list.add(user);
        Racine racine = new Racine(list);
        assertEquals("test", racine.getUtilisateur().get(0).getPseudo());
    }
    @Test
    public void testGroupe(){
        Utilisateur user = new Utilisateur("test1", "test1", "test1");
        Utilisateur user2 = new Utilisateur("test2", "test2", "test2");
        ArrayList<Utilisateur> list = new ArrayList<>();
        Groupe groupe = new Groupe(list);
        list.add(user);
        list.add(user2);
        groupe.setGroupe(list);
        assertEquals("test1", groupe.getGroupe().get(0).getPseudo());
        assertEquals("test2", groupe.getGroupe().get(1).getPassword());
    }
}
