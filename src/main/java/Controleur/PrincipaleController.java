package Controleur;

import Donnees.Message;
import Donnees.RequestClient;
import Donnees.Utilisateur;
import Interface.*;
import Timer.ClientConnexion;
import com.google.gson.Gson;
import Singletons.Singletons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Date;

public class PrincipaleController implements ActionListener, WindowListener {

    private JButton myAccount;
    private JButton newConv;
    private JButton newGroup;
    private JButton sendMsg;
    private JButton disconnect;
    private JComboBox listConv;
    private JFrame mainWindows;
    private static ClientConnexion connection;
    private String[] tabVerdict;
    public static Thread t;

    public PrincipaleController(JButton myAccount, JButton newConv, JButton newGroup, JButton sendMsg, JButton disconnect, JComboBox listConv, JFrame mainWindows, Utilisateur utilisateur) {
        this.myAccount = myAccount;
        this.newConv = newConv;
        this.newGroup = newGroup;
        this.sendMsg = sendMsg;
        this.disconnect = disconnect;
        this.listConv = listConv;
        this.mainWindows = mainWindows;

        myAccount.addActionListener(this);
        newConv.addActionListener(this);
        newGroup.addActionListener(this);
        sendMsg.addActionListener(this);
        disconnect.addActionListener(this);
        listConv.addActionListener(this);
        mainWindows.addWindowListener(this);
    }

    public static ClientConnexion connexionThread() {
        t = new Thread(connection = new ClientConnexion(RequestClient.getSock(), InterfacePrincipale.getUtilisateur(), InterfacePrincipale.getConvText(),InterfacePrincipale.getListeConv()));
        t.start();
        t.setPriority(Thread.MAX_PRIORITY);

        return connection;
    }

    public static ClientConnexion getConnection() {
        return connection;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        if(actionCommand.equals(myAccount.getActionCommand())) {
            new InterfaceAccount(InterfacePrincipale.getUtilisateur(), connection);
        } else if(actionCommand.equals(newConv.getActionCommand())) {
            try {
                String verdict;
                RequestClient.askListContact(InterfacePrincipale.getUtilisateur().getUserName());
                verdict = connection.getVerdict();
                while (verdict == null)
                {
                    System.out.println("null");
                    verdict = connection.getVerdict();
                }
                if(verdict.equals("false"))
                {
                    Boolean.parseBoolean(verdict);
                    JOptionPane.showMessageDialog(null, "Aucun contacts disponibles");
                }
                else
                {
                    tabVerdict = verdict.split(",");
                    System.out.println("split "+tabVerdict);
                    new InterfaceNewConv(InterfacePrincipale.getUtilisateur(), tabVerdict, connection, InterfacePrincipale.getListeConv());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if(actionCommand.equals(newGroup.getActionCommand())) {
            new InterfaceNewGroup();
        } else if(actionCommand.equals(sendMsg.getActionCommand())) {
            Date aujourdhui = new Date(); //date de l'envoi du message
            String message= InterfacePrincipale.getMsgText().getText();
            if(!message.isEmpty()) {
                InterfacePrincipale.getConvText().append(InterfacePrincipale.getFormater().format(aujourdhui) + " : " + message + "\n"); //format du message : date + contenu
                InterfacePrincipale.getMsgText().setText(""); //RAZ du jtextfield à chaque envoi de message
                try {
                    RequestClient.SendMsg(InterfacePrincipale.getUtilisateur().getUserName(), (String) InterfacePrincipale.getListeConv().getSelectedItem(),InterfacePrincipale.getFormater().format(aujourdhui) + " : " + message + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } else if(actionCommand.equals(disconnect.getActionCommand())) {
            try {
                RequestClient.chatDisconnect(InterfacePrincipale.getUtilisateur().getUserName());//on va chercher la valeur du JTextField user présente dans l'interface connexion
                JOptionPane.showMessageDialog(null, "Déconnexion du chat");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                RequestClient.getSock().close();
                t.interrupt();
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mainWindows.dispose();
        } else if(actionCommand.equals(listConv.getActionCommand())) {
            InterfacePrincipale.getConvText().setText("");
            Message[] Multimessage = null;
            String messages = "";
            try {
                RequestClient.GetMsgHistory(InterfacePrincipale.getUtilisateur().getUserName(), (String) InterfacePrincipale.getListeConv().getSelectedItem());
                messages = connection.getVerdict();
                while (messages == null)
                {
                    System.out.println("null");
                    messages = connection.getVerdict();
                }
                System.out.println("pas null");

                try
                {
                    Multimessage = Singletons.getGsonInstance().fromJson(messages,Message[].class);
                    for(Message message : Multimessage)
                    {
                        InterfacePrincipale.getConvText().append(message.getContent());
                    }
                }
                catch (Exception e)
                {
                    Message Snglemessage = Singletons.getGsonInstance().fromJson(messages,Message.class);
                    InterfacePrincipale.getConvText().append(Snglemessage.getContent());
                }
            } catch (IOException e) {

            }

        }

    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        try {
            RequestClient.chatDisconnect(InterfacePrincipale.getUtilisateur().getUserName());
            RequestClient.getSock().close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
