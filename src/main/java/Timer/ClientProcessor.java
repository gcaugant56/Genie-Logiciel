package Timer;

import Données.Utilisateur;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.util.Date;

public class ClientProcessor implements Runnable{

    private Socket sock;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;
    private Gson gson = new Gson();

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
                Utilisateur utilisateur = new Utilisateur("armelito","armel","tchiasso");
                String json = gson.toJson(this);
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

                if (response.contains("1")) {
                    toSend = "true";
                } else if (response.contains("2")) {
                    toSend = json;
                } else if (response.contains("3")) {

                } else if ("PROJET".equals(response.toUpperCase())) {
                    toSend = "C'EST QUI QUI VA RÉUSSIR LE PROJET ?? C'EST NOUUUUUUUUUUS";
                } else if ("CLOSE".equals(response.toUpperCase())) {
                    toSend = "Communication terminée";
                    closeConnexion = true;
                } else {
                    toSend = "Commande inconnu !";
                }

                //On envoie la réponse au client
                writer.write(toSend);
                //Il FAUT IMPERATIVEMENT UTILISER flush()
                //Sinon les données ne seront pas transmises au client
                //et il attendra indéfiniment
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
