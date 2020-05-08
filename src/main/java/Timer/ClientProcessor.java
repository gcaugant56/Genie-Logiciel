package Timer;
import Donnees.Racine;
import Donnees.RequestCode;
import Donnees.Utilisateur;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Hashtable;

public class ClientProcessor implements Runnable{

    private Socket sock;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;
    private Hashtable dic = null;

    public ClientProcessor(Socket pSock, Hashtable dic){
        sock = pSock;
        this.dic = dic;

    }
    //Le traitement lancé dans un thread séparé
    public void run(){
        System.err.println("Lancement du traitement de la connexion cliente");

        boolean closeConnexion = false;
        //tant que la connexion est active, on traite les demandes
        while(!sock.isClosed()){

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
                        for (Utilisateur base : Json.getUtilisateur()) {
                            if (base.getUserName().equals(tabResponse[1]) &&
                                    base.getPassword().equals(tabResponse[2]))
                            // on cherche à savoir si le mdp/user_name correspondent au Json
                            {
                                Gson gson = new Gson();
                                json = gson.toJson(base);//transformation de l'objet en json
                                if(!dic.containsKey(base.getUserName()))
                                {
                                    dic.put(base.getUserName(),sock);
                                }
                            }
                        }
                        if (json.equals("")) {
                            toSend = "null";
                        } else {
                            toSend = json;
                        }
                        break;
                    case DECONNEXION:
                        break;
                    case ENVOI_MSG:
                        break;
                    case MODIF_MDP:
                        Json = Donnees.Serializationmessage.Deserialization("Json.json");
                        for (Utilisateur base : Json.getUtilisateur()) {
                            if(base.getUserName().equals(tabResponse[1]) &&
                                    base.getPassword().equals(tabResponse[2]))
                            {
                                base.setPassword(tabResponse[3]);
                                Donnees.Serializationmessage.Serialization(Json,"Json.json");
                                toSend="true";

                            }
                            if (!toSend.equals("true")){
                                toSend= "false";
                            }
                        }
                        break;
                    case MODIF_USERNAME:
                        Json = Donnees.Serializationmessage.Deserialization("Json.json");
                        json = "";
                        for (Utilisateur base : Json.getUtilisateur()) {
                            if (base.getUserName().equals(tabResponse[1])) {
                                base.setPseudo(tabResponse[2]);
                                Donnees.Serializationmessage.Serialization(Json, "Json.json");
                                Gson gson = new Gson();
                                json = gson.toJson(base);
                            }
                        }
                        if (json.equals("")) {
                            toSend = "null";
                        } else {
                            toSend = json;
                        }
                        break;
                    case AJOUT_CONTACT:
                        break;
                    case CREATION_GROUP:
                        break;
                    case ENVOI_GROUP:
                        break;

                   }} catch (IOException e) {
                e.printStackTrace();
            }
            writer.write(toSend);
                writer.flush();

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

}
