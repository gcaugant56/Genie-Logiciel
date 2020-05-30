package Interface;

import Controleur.NewConvController;
import Donnees.Contacts;
import Donnees.RequestClient;
import Donnees.Utilisateur;
import Timer.ClientConnexion;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

public class InterfaceNewConv {

    private JLabel label1 = new JLabel("Destinataire : ");
    private JButton startButton = new JButton(" Demarrer");
    private JPanel topPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private String[] pseudonymeList;
    private NewConvController newConvController;
    private static Utilisateur utilisateur;
    private static JFrame newConvWindows;
    private static JComboBox comboBoxNewConv;
    private static JComboBox comboBoxPrincipale;

    public static JComboBox getComboBoxNewConv() {
        return comboBoxNewConv;
    }

    public static JComboBox getComboBoxPrincipale() {
        return comboBoxPrincipale;
    }

    public static JFrame getNewConvWindows() {
        return newConvWindows;
    }

    public static Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public InterfaceNewConv(Utilisateur user, String[] pseudoList, ClientConnexion connexion, JComboBox comboBox) {

        utilisateur = user;
        pseudonymeList = pseudoList;
        comboBoxPrincipale = comboBox;

        //création de la fenêtre newconwindows
        newConvWindows = new JFrame();
        newConvWindows.setMinimumSize(new Dimension(390, 220));
        newConvWindows.setLayout(new GridLayout(2,1));
        newConvWindows.setLocationRelativeTo(null);

        //ajout des panels du haut et du bas à la fenêtre
        newConvWindows.add(topPanel, new FlowLayout());
        newConvWindows.add(bottomPanel);


        comboBoxNewConv = new JComboBox(pseudonymeList);

        //ajout des composants dans le panel du haut
        topPanel.add(label1);
        topPanel.add(comboBoxNewConv);
        comboBox.setPreferredSize(new Dimension(100, 30));

        //ajout des composants dans le panel du bas
        bottomPanel.add(startButton);

        newConvWindows.setVisible(true);
        newConvWindows.revalidate();
        newConvWindows.repaint();

        newConvController = new NewConvController(startButton);

    }

}
