package Timer;

import Donnees.Racine;
import Donnees.RequestCode;
import Donnees.Utilisateur;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;


public class ClientProcessor implements Runnable{

    private Socket sock;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;

    public ClientProcessor(Socket pSock){
        sock = pSock;
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
                switch (Code)
                {
                    case CREATION_COMPTE:
                        Racine Json = Donnees.Serializationmessage.Deserialization("Json.json");
                        for(Utilisateur base : Json.getUtilisateur())
                        {
                            if (base.getUserName().equals(tabResponse[1]) ||
                                    base.getPseudo().equals(tabResponse[2]))
                            {
                                toSend="false";
                            }

                        }
                        if ( toSend!="false")
                        {
                            Utilisateur nouveau = new Utilisateur(tabResponse[1],tabResponse[2], tabResponse[3]);
                            Json.setUtilisateur(nouveau);
                            Donnees.Serializationmessage.Serialization(Json, "Json.json");
                            toSend="true";
                        }
                        break;
                    case CONNEXION_CHAT:
                        break;
                    case DECONNEXION:
                        break;
                    case ENVOI_MSG:
                        break;
                    case MODIF_MDP:
                        break;
                    case MODIF_USERNAME:
                        break;
                    case AJOUT_CONTACT:
                        break;
                    case CREATION_GROUP:
                         break;
                    case ENVOI_GROUP:
                        break;
                }
                writer.write(toSend);
                writer.flush();

                if(closeConnexion){
                    System.err.println("COMMANDE CLOSE DETECTEE ! ");
                    writer = null;
                    reader = null;
                    sock.close();
                    break;
                }
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
