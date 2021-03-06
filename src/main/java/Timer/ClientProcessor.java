package Timer;

import Donnees.*;
import Singletons.Singletons;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

/**
 * Cette classe est utilise pour lire en continue sur le socket du client,
 * sur la reception d'une requete, il l'a traite et répond au client
 */
public class ClientProcessor implements Runnable{

    private Socket sock;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;
    private Hashtable dic;
    private Utilisateur currentUser = null;
    private Socket socketClient;

    /**
     * Constructeur de l'objet ClientProcesseur
     * @param pSock Socket du client qui c'est connecter au serveur
     * @param dic Dictionnaire permettant de stocker tout les socket des utilisateurs connecter, le nom de compte de
     *            L'utilisateur est utilisé comme clé, et son socket comme valeur
     */
    public ClientProcessor(Socket pSock, Hashtable dic){
        sock = pSock;
        socketClient = pSock;
        this.dic = dic;
    }

    /**
     * Cette méthode permet de lire en continu le socket du client afin de récuperer la requete
     * et la reponse est défini en fonction de la requete du client
     */
    public void run(){
        System.err.println("Lancement du traitement de la connexion cliente");

        boolean closeConnexion = false;
        //tant que la connexion est active, on traite les demandes
        while(!sock.isClosed()){
            String username,password,contact, nameOfGroup, recipients;
            boolean isSend = false;
            ArrayList<Groupe> groupeArrayList = null;
            Contacts contacts;
            Groupe groupeContacts;
            Utilisateur user;
            String pseudo;
            try {

                //Ici, nous n'utilisons pas les mêmes objets que précédemment
                //Je vous expliquerai pourquoi ensuite
                writer = new PrintWriter(sock.getOutputStream());
                reader = new BufferedInputStream(sock.getInputStream());

                //On attend la demande du client
                String response = read();
                InetSocketAddress remote = (InetSocketAddress)sock.getRemoteSocketAddress();

                //On affiche quelques infos, pour le débuggage
                String debug = "";
                debug = "Thread : " + Thread.currentThread().getName() + ". ";
                debug += "Demande de l'adresse : " + remote.getAddress().getHostAddress() +".";
                debug += " Sur le port : " + remote.getPort() + ".\n";
                debug += "\t -> Commande reçue : " + response + "\n";
                System.err.println("\n" + debug);

                //On traite la demande du client en fonction de la commande envoyée
                String toSend = "";
                String[] tabResponse = response.split("\\*"); //permet de séparer le tableau par carractère
                RequestCode Code = RequestCode.values()[Integer.parseInt(tabResponse[0])-1];
                Racine Json = null;
                String json = "";
                try {
                switch (Code) {
                    case CREATION_COMPTE:
                        Json = Donnees.Serializationmessage.Deserialization("Json.json");
                        for (Utilisateur base : Json.getUtilisateur()) {
                            if (base.getUserName().equals(tabResponse[1]) ||
                                    base.getPseudo().equals(tabResponse[2])) {
                                toSend = "false";
                            }

                        }
                        if (!toSend.equals("false")) {
                            Utilisateur nouveau = new Utilisateur(tabResponse[1], tabResponse[2], tabResponse[3]);
                            Json.setUtilisateur(nouveau);
                            Donnees.Serializationmessage.Serialization(Json, "Json.json");
                            toSend = "true";
                        }
                        break;
                    case CONNEXION_CHAT:
                        Json = Donnees.Serializationmessage.Deserialization("Json.json");
                        json = "";
                        username = tabResponse[1];
                        password = tabResponse[2];
                        user = findUserByUsername(username,Json);
                        if(user != null && user.getPassword().equals(password))
                        {
                            currentUser = user;
                            json = Singletons.getGsonInstance().toJson(user);//transformation de l'objet en json
                            if(!dic.containsKey(user.getUserName()))
                            {
                                dic.put(user.getUserName(),sock);
                            }
                        }
                        if (json.equals("")) {
                            toSend = "null";
                        } else {
                            toSend = json;
                        }
                        break;

                    case DECONNEXION:
                        Json = Donnees.Serializationmessage.Deserialization("Json.json");
                        json = "";
                        username = tabResponse[1];
                        user = findUserByUsername(username,Json);
                        if (user != null)
                        // on cherche à savoir si le mdp/user_name correspondent au Json
                        {
                            if(dic.containsKey(user.getUserName()))
                            {
                                dic.remove(user.getUserName());
                            }

                        }
                        break;

                    case ENVOI_MSG:
                        username = tabResponse[1];
                        String destinataire = tabResponse[2];
                        String msg = tabResponse[3];

                        Json = Donnees.Serializationmessage.Deserialization("Json.json");
                        contacts = findContactByPseudo(destinataire,username,Json);
                        groupeContacts = findGroupByName(destinataire, Json);
                        if(contacts != null && groupeContacts == null)
                        {
                            Message message = new Message(msg,destinataire,currentUser.getPseudo());
                            contacts.setMessage(message);
                            if(dic.containsKey(contacts.getUsername()))
                            {
                                sock = (Socket) dic.get(contacts.getUsername());
                                writer = new PrintWriter(sock.getOutputStream());
                                toSend = RequestCode.ENVOI_MSG + "*"+msg+"*"+currentUser.getPseudo();
                            }
                        } else if(contacts != null && groupeContacts != null) {
                            Message message = new Message(msg,destinataire,currentUser.getPseudo());
                            contacts.setMessage(message);

                            for(String groupe : groupeContacts.getUserName()) {
                                if(dic.containsKey(groupe) && !groupe.equals(username))
                                {
                                    sock = (Socket) dic.get(groupe);
                                    writer = new PrintWriter(sock.getOutputStream());
                                    toSend = RequestCode.ENVOI_MSG + "*"+msg+"*"+groupeContacts.getName();
                                    writer.write(toSend);
                                    writer.flush();
                                    isSend = true;
                                }
                                for (Utilisateur base : Json.getUtilisateur()) {
                                    if (base.getPseudo().equals(groupe)) {
                                        for (Contacts contactToSend : base.getContacts()) {
                                            if (contactToSend.getUsername().equals(groupeContacts.getName())) {
                                                message = new Message(msg, destinataire, base.getPseudo());
                                                contactToSend.setMessage(message);
                                            }
                                        }
                                    }
                                }
                            }

                        }

                        if(!isSend) {
                            for (Utilisateur base : Json.getUtilisateur()) {
                                if (base.getPseudo().equals(destinataire)) {
                                    for (Contacts contactToSend : base.getContacts()) {
                                        if (contactToSend.getUsername().equals(username)) {
                                            Message message = new Message(msg, destinataire, base.getPseudo());
                                            contactToSend.setMessage(message);
                                        }
                                    }
                                }
                            }
                        }

                        Donnees.Serializationmessage.Serialization(Json,"Json.json");
                        break;

                    case MODIF_MDP:
                        Json = Donnees.Serializationmessage.Deserialization("Json.json");
                        username = tabResponse[1];
                        password = tabResponse[2];
                        String newPassword = tabResponse[3];

                        user = findUserByUsername(username,Json);
                            if(user != null && user.getPassword().equals(password))
                            {
                                user.setPassword(newPassword);
                                currentUser.setPassword(newPassword);
                                Donnees.Serializationmessage.Serialization(Json,"Json.json");
                                toSend=RequestCode.MODIF_MDP+"*true";

                            }
                            if (!toSend.equals(RequestCode.MODIF_MDP+"*true")){
                                toSend= RequestCode.MODIF_MDP+"*false";
                            }
                        break;

                    case MODIF_USERNAME:
                        Json = Donnees.Serializationmessage.Deserialization("Json.json");
                        username = tabResponse[1];
                        String newPseudo = tabResponse[2];

                        user = findUserByUsername(username,Json);
                        if(user != null)
                        {
                            user.setPseudo(newPseudo);
                            currentUser.setPseudo(newPseudo);
                            Donnees.Serializationmessage.Serialization(Json, "Json.json");
                            toSend= RequestCode.MODIF_USERNAME+"*true";
                        }

                        if (!toSend.equals(RequestCode.MODIF_USERNAME+"*true")){
                            toSend= RequestCode.MODIF_USERNAME+"*false";
                        }
                        break;

                    case AJOUT_CONTACT:
                        Json = Donnees.Serializationmessage.Deserialization("Json.json");
                        groupeArrayList = Json.getGroupeList();
                        json = "";
                        contacts = null;
                        for (Utilisateur base : Json.getUtilisateur()) {
                            if(base.getPseudo().equals(tabResponse[2])) {
                                contacts = new Contacts(base.getPseudo(), base.getUserName());
                                json = RequestCode.AJOUT_CONTACT+"*"+ Singletons.getGsonInstance().toJson(contacts);
                            }
                        }

                        for(int i = 0; i != groupeArrayList.size(); i++) {
                            if(groupeArrayList.get(i).getName().equals(tabResponse[2])) {
                                contacts = new Contacts(groupeArrayList.get(i).getName());
                                json = RequestCode.AJOUT_CONTACT+"*"+ Singletons.getGsonInstance().toJson(contacts);
                            }
                        }

                        if(json.equals("")) {
                            toSend = RequestCode.AJOUT_CONTACT+"*false";
                        } else {
                            for (Utilisateur base : Json.getUtilisateur()) {
                                if(base.getUserName().equals(tabResponse[1])) {
                                    base.getContacts().add(contacts);
                                    Donnees.Serializationmessage.Serialization(Json, "Json.json");
                                }
                            }
                            toSend = json;
                            System.out.println("Reponse : "+json);
                        }
                        break;
                    case CREATION_GROUP:
                        Json = Donnees.Serializationmessage.Deserialization("Json.json");
                        boolean isCreated = false;
                        nameOfGroup = tabResponse[1];
                        recipients = tabResponse[2];
                        groupeArrayList = Json.getGroupeList();

                        ArrayList<String> recipientsList = new ArrayList<>();

                        for(int i = 0; i != groupeArrayList.size(); i++) {
                            if(!groupeArrayList.get(i).getName().equals(nameOfGroup) && isCreated == false) {
                                Collections.addAll(recipientsList, recipients.split(","));
                                Groupe newGroupe = new Groupe(nameOfGroup, recipientsList);
                                Json.setGroupeList(newGroupe);
                                Donnees.Serializationmessage.Serialization(Json, "Json.json");
                                isCreated = true;
                            }
                        }

                        if(isCreated)
                            toSend = RequestCode.CREATION_GROUP+"*true";
                        else
                            toSend = RequestCode.CREATION_GROUP+"*false";

                        break;
                    case DEMANDE_LISTE:
                        Json = Donnees.Serializationmessage.Deserialization("Json.json");
                        String pseudoListString = "";
                        String pseudoGroupListString = "";
                        ArrayList<Utilisateur> allUser = Json.getUtilisateur();
                        if(allUser.size() > 2)
                        {
                            for (Utilisateur base : allUser)
                            {
                                if(!base.getUserName().equals(currentUser.getUserName()))
                                {
                                    pseudoListString += base.getPseudo()+",";
                                }
                            }
                        }
                        else
                        {
                            if(!allUser.get(1).getUserName().equals(currentUser.getUserName()))
                            {
                                pseudoListString = allUser.get(1).getPseudo();
                            }
                        }
                        pseudoListString = pseudoListString.substring(1, pseudoListString.length()-1);

                        ArrayList<Groupe> allGroup = Json.getGroupeList();

                        if(allGroup.size() > 1) {
                            if(allGroup.size() > 2)
                            {
                                for (Groupe base : allGroup)
                                {
                                    if(!base.getUserName().equals(currentUser.getUserName()))
                                    {
                                        pseudoGroupListString += base.getName()+",";
                                    }
                                }
                            }
                            else
                            {

                                if(!allGroup.get(1).getUserName().equals(currentUser.getUserName()))
                                {
                                    pseudoGroupListString = allGroup.get(1).getName();
                                }
                            }
                        }

                        if(pseudoGroupListString.length() > 3) {
                            pseudoListString = pseudoListString+","+pseudoGroupListString;
                        }

                        if(pseudoListString.equals("")) {
                            toSend = RequestCode.DEMANDE_LISTE+"*false";
                        } else {
                            toSend = RequestCode.DEMANDE_LISTE+"*"+pseudoListString;
                        }
                        break;
                    case ENVOI_GROUP:
                        break;
                    case Historique_Message:
                        Json = Donnees.Serializationmessage.Deserialization("Json.json");
                        json = "";
                        username = tabResponse[1];
                        pseudo = tabResponse[2];
                        contacts = findContactByPseudo(pseudo,username,Json);

                        if(contacts != null)
                        {
                            json = Singletons.getGsonInstance().toJson(contacts.getMessage());
                        }

                        if(json.equals(""))
                        {
                            toSend = "null";
                        }
                        else
                        {
                            toSend = RequestCode.Historique_Message+"*"+json;
                        }
                        break;

                    case Suppression_Message:
                        Json = Donnees.Serializationmessage.Deserialization("Json.json");
                        json = "";
                        username = tabResponse[1];
                        pseudo = tabResponse[2];
                        contacts = findContactByPseudo(pseudo,username,Json);
                        Collection temp = contacts.getMessage();
                        contacts.getMessage().removeAll(temp);
                        TimeUnit.MILLISECONDS.sleep(300);
                        Serializationmessage.Serialization(Json, "Json.json");
                        TimeUnit.MILLISECONDS.sleep(300);
                        Gson gson = new Gson();
                        json = RequestCode.Suppression_Message+"*"+gson.toJson(contacts);
                        toSend = RequestCode.Suppression_Message+"*"+json;

                    case Suppression_Compte:
                        Json = Donnees.Serializationmessage.Deserialization("Json.json");
                        json = "";
                        username = tabResponse[1];
                        pseudo = tabResponse[2];

                        user = findUserByUsername(username, Json);
                        Json.getUtilisateur().remove(user);

                        Serializationmessage.Serialization(Json, "Json.json");
                        toSend = RequestCode.Suppression_Message+"*"+ true;
                   }
                }
                catch (IOException e)
                {
                e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(!isSend) {
                    writer.write(toSend);
                    writer.flush();
                }
            sock = socketClient;
            }catch(SocketException e){
                System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //La méthode que nous utilisons pour lire les réponses
    private String read() throws IOException{
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }

    private Utilisateur findUserByUsername(String username, Racine root)
    {
        for(Utilisateur base : root.getUtilisateur())
        {
            if(base.getUserName().equals(username)) {
                return base;

            }
        }
        return null;
    }

    private Contacts findContactByPseudo(String pseudo, String username, Racine root)
    {
        Utilisateur user = findUserByUsername(username,root);
        for (Contacts contacts : user.getContacts())
        {
            if(contacts.getPseudo().equals(pseudo))
            {
                return contacts;
            }
        }
        return null;
    }

    private Groupe findGroupByName(String nameOfGroup, Racine root)
    {
        for(Groupe groupe : root.getGroupeList())
        {
            if(groupe.getName().equals(nameOfGroup)) {
                return groupe;

            }
        }
        return null;
    }

}
