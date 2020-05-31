import Donnees.Contacts;
import Donnees.Utilisateur;
import org.junit.Test;
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
        contact.setPseudo("rename");
        user.setContacts(contact);
        assertEquals("rename", user.getContacts().get(0).getPseudo());
    }
}
