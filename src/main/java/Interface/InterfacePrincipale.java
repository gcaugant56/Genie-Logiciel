package Interface;

import Controleur.PrincipaleController;
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

import Timer.ClientConnexion;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class InterfacePrincipale {

    private static Utilisateur utilisateur;
    private int numberUser;
    private JLabel msg = new JLabel("Message : ");
    private JScrollPane scrollPane;
    private JLabel userOnLine = new JLabel("Nombre d'utilisateur en ligne : " + numberUser);
    private JButton myAccount = new JButton("Mon Compte");
    private JButton newConv = new JButton("Nouvelle Conversation");
    private JButton newGroup = new JButton("Nouveau Groupe");
    private JButton sendMsg = new JButton("Envoyer");
    private JButton disconnect = new JButton("Déconnexion");
    private JPanel centerPanel = new JPanel();
    private JPanel eastPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JPanel centerNorthPanel = new JPanel();
    private JPanel centerSouthPanel = new JPanel();
    private JPanel southWestPanel = new JPanel();
    private JPanel southEastPanel = new JPanel();
    private PrincipaleController principaleController;
    private JFrame mainWindows;
    private static SimpleDateFormat formater = new SimpleDateFormat("h:mm a");
    private static JTextArea convText = new JTextArea(12,35);
    private static ArrayList<String> tabContact;
    private static JPanel northPanel = new JPanel();
    private static JComboBox listeConv;
    private static JTextField msgText = new JTextField();

    public static JTextArea getConvText() {
        return convText;
    }

    public static JTextField getMsgText() {
        return msgText;
    }

    public static JComboBox getListeConv() {
        return listeConv;
    }

    public static Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public static SimpleDateFormat getFormater() {
        return formater;
    }

    public InterfacePrincipale(Utilisateur user) {

        utilisateur = user;
        //création de la fenêtre principale
        mainWindows = new JFrame();
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

        PrincipaleController.connexionThread();
        principaleController = new PrincipaleController(myAccount, newConv, newGroup, sendMsg, disconnect, listeConv, mainWindows, utilisateur);

    }

    public static ArrayList<String> getTabContact() {
        return tabContact;
    }

}
