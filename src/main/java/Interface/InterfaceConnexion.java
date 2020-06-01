package Interface;

import Controleur.ConnexionController;
import Donnees.Utilisateur;
import Donnees.RequestClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Classe permetttant de créer l'interface de connexion
 */
public class InterfaceConnexion {
    private JLabel chat = new JLabel("Accédez au Chat");
    private JLabel userName = new JLabel("Username : ");
    private JLabel passWord = new JLabel("Mot de passe : ");
    private JButton logIn = new JButton("Connexion");
    private Font font = new Font("Arial", Font.BOLD, 12);
    private JLabel createAccountLink = new JLabel("Pas de compte créez-en un ici");
    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel(new GridLayout(2,2));
    private JPanel southPanel = new JPanel();
    private ConnexionController connexionController;
    private static JFrame newConnection;
    private static JTextField jTextFieldUserName = new JTextField();
    private static JTextField jTextFieldPassWord = new JTextField();

    public static String getjTextFieldUserName() {
        return jTextFieldUserName.getText();
    }

    public static String getjTextFieldPassWord() {
        return jTextFieldPassWord.getText();
    }

    public static JFrame getNewConnection() {
        return newConnection;
    }

    /**
     * Constructeur qui affiche l'ecran de connexion
     * @throws IOException
     */
    public InterfaceConnexion() throws IOException {

        //création de la fenêtre newconnection
        newConnection = new JFrame();
        newConnection.setMinimumSize(new Dimension(390, 220));
        newConnection.setLayout(new GridLayout(3,1));
        newConnection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newConnection.setLocationRelativeTo(null);

        //ajout des panels du haut, du milieu et du bas à la fenêtre
        newConnection.add(northPanel,BorderLayout.NORTH);
        newConnection.add(centerPanel,BorderLayout.CENTER);
        newConnection.add(southPanel,BorderLayout.SOUTH);

        //paramétrage de la police du label "chat" et ajout de celui-ci dans le panel du haut
        chat.setFont(font);
        northPanel.add(chat,BorderLayout.CENTER);

        //ajout des composants dans le panel du milieu
        centerPanel.add(userName);
        centerPanel.add(jTextFieldUserName);
        centerPanel.add(passWord);
        centerPanel.add(jTextFieldPassWord);

        //ajout des composants dans le panel du bas qui est quadrillé en 2 lignes et 1 colonnes
        southPanel.setLayout(new GridLayout(2,1));
        southPanel.add(logIn);
        createAccountLink.setForeground(Color.BLUE.darker()); //label transformé en hyperlink standard
        createAccountLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Pour que le curseur de la souris se transforme en icône de main lorsque l'utilisateur déplace la souris sur l'étiquette
        southPanel.add(createAccountLink);

        newConnection.setVisible(true);
        newConnection.revalidate();
        newConnection.repaint();

        connexionController = new ConnexionController(logIn, createAccountLink);

    }

    public static void main(String[] args) throws IOException {
        new InterfaceConnexion();
    }
}
