package Interface;

import Donnees.Contacts;
import Donnees.Message;
import Donnees.RequestClient;
import Donnees.Utilisateur;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import Timer.ClientConnexion;
public class InterfacePrincipale {

    private int numberUser;
    private static JComboBox listeConv;
    private JLabel msg = new JLabel("Message : ");
    private JTextArea convText = new JTextArea(12,35);
    private JScrollPane scrollPane;
    private JLabel userOnLine = new JLabel("Nombre d'utilisateur en ligne : " + numberUser);
    private JTextField msgText = new JTextField();
    private JButton myAccount = new JButton("Mon Compte");
    private JButton newConv = new JButton("Nouvelle Conversation");
    private JButton newGroup = new JButton("Nouveau Groupe");
    private JButton sendMsg = new JButton("Envoyer");
    private JButton disconnect = new JButton("Déconnexion");
    private SimpleDateFormat formater = new SimpleDateFormat("h:mm a");
    private static ClientConnexion connection;
    private static ArrayList<String> tabContact;
    private Utilisateur utilisateur;
    private String[] tabVerdict;
    public Thread t;
    static JPanel northPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel eastPanel = new JPanel();
    JPanel southPanel = new JPanel();
    JPanel centerNorthPanel = new JPanel();
    JPanel centerSouthPanel = new JPanel();
    JPanel southWestPanel = new JPanel();
    JPanel southEastPanel = new JPanel();

    public InterfacePrincipale(Utilisateur user) {

        utilisateur = user;
        //création de la fenêtre principale
        JFrame mainWindows = new JFrame();
        mainWindows.setMinimumSize(new Dimension(640, 380));
        mainWindows.setLayout(new BorderLayout());
        mainWindows.setLocationRelativeTo(null);

        //ajout des panels du haut, du milieu, du bas et de droite à la fenêtre
        mainWindows.add(northPanel, BorderLayout.NORTH);
        mainWindows.add(centerPanel, BorderLayout.CENTER);
        mainWindows.add(eastPanel, BorderLayout.EAST);
        eastPanel.setLayout(new GridLayout(4,0));
        mainWindows.add(southPanel, BorderLayout.SOUTH);
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));

        scrollPane = new JScrollPane(convText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        centerPanel.add(centerNorthPanel, BorderLayout.NORTH);
        centerPanel.add(centerSouthPanel, BorderLayout.SOUTH);
        southPanel.add(southWestPanel, BorderLayout.WEST);
        southPanel.add(southEastPanel, BorderLayout.EAST);
        centerPanel.add(scrollPane);

        convText.setEditable(false); //abscence de curseur pour écrire sur le jtextarea
        convText.setLineWrap(true); //Retour à la ligne de la zone de texte
        convText.setWrapStyleWord(true); //Lignes enveloppées aux limites des mots
        //stocke dans une liste tous les pseudos pour less afficher dans le jcombobox

        tabContact = new ArrayList<>();
        for (Contacts c : utilisateur.getContacts()) {
            tabContact.add(c.getPseudo());
        }
        listeConv = new JComboBox(tabContact.toArray());

        for(Contacts contact : utilisateur.getContacts())
        {
            if(contact.getPseudo().equals(listeConv.getSelectedItem()))
            {
                for(Message message : contact.getMessage())
                {
                    convText.append(message.getContent());

                }
            }
        }

        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 85, 10));
        northPanel.add(listeConv);
        northPanel.add(disconnect);
        listeConv.setPreferredSize(new Dimension(300, 30));

        //ajout au panel du centre d'un scroll vertical et horizontal quand le texte dépasse le jtextarea


        //ajout des composants dans le panel de droite
        eastPanel.add(myAccount);
        eastPanel.add(newConv);
        eastPanel.add(newGroup);
        eastPanel.add(userOnLine);

        //ajout des composants dans le panel du bas
        southPanel.add(msg, BorderLayout.WEST);
        southPanel.add(msgText);
        southPanel.add(sendMsg);
        msgText.setPreferredSize(new Dimension(300,50));

        mainWindows.getContentPane().add(centerPanel);
        mainWindows.setVisible(true);
        mainWindows.revalidate();
        mainWindows.repaint();
        t = new Thread(connection = new ClientConnexion(RequestClient.getSock(), utilisateur,convText));
        t.start();
        t.setPriority(Thread.MAX_PRIORITY);

        //Actions à l'appui du bouton "Mon Compte" : redirection vers l'interface Mon Compte
        myAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new InterfaceAccount(utilisateur, connection);
            }
        });

        //Actions à l'appui du bouton "Nouvelle Conversation" : redirection vers l'interface Nouvelle Conversation
        newConv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    RequestClient.askListContact(utilisateur.getUserName());
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tabVerdict = connection.getVerdict().split(",");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                new InterfaceNewConv(utilisateur, tabVerdict, connection, listeConv);

            }
        });

        //Actions à l'appui du bouton "Nouveau Groupe" : redirection vers l'interface Nouveau Groupe
        newGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new InterfaceNewGroup();
            }
        });

        //Actions à l'appui du bouton "Envoyer" : envoi du contenu écrit dans le jtextfield vers le jtextarea
        sendMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Date aujourdhui = new Date(); //date de l'envoi du message
                String message= msgText.getText();
                if(!message.isEmpty()) {
                    convText.append(formater.format(aujourdhui) + " : " + message + "\n"); //format du message : date + contenu
                    msgText.setText(""); //RAZ du jtextfield à chaque envoi de message
                    try {
                        RequestClient.SendMsg(user.getUserName(), (String) listeConv.getSelectedItem(),formater.format(aujourdhui) + " : " + message + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    RequestClient.chatDisconnect(utilisateur.getUserName());//on va chercher la valeur du JTextField user présente dans l'interface connexion
                    JOptionPane.showMessageDialog(null, "Déconnexion du chat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    RequestClient.getSock().close();
                    t.interrupt();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mainWindows.dispose();
            }
        });
        listeConv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                convText.removeAll();
                for(Contacts contact : utilisateur.getContacts())
                {
                    if(contact.getPseudo().equals(listeConv.getSelectedItem()))
                    {
                        for(Message message : contact.getMessage())
                        {
                            convText.append(message.getContent());

                        }
                    }
                }
            }
        }





        );
    }

    public static ArrayList<String> getTabContact() {
        return tabContact;
    }

}
