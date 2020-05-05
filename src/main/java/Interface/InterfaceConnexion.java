package Interface;

import Données.RequestClient;
import Données.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class InterfaceConnexion {
    private JLabel chat = new JLabel("Accédez au Chat");
    private JLabel userName = new JLabel("Username : ");
    private JLabel passWord = new JLabel("Mot de passe : ");
    private static JTextField jTextFieldUserName = new JTextField();
    private JTextField jTextFieldPassWord = new JTextField();
    private JButton plugIn = new JButton("Connexion");
    private Font font = new Font("Arial", Font.BOLD, 12);
    private JLabel createCompte = new JLabel("Pas de compte créez-en un ici");
    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel(new GridLayout(2,2));
    private JPanel southPanel = new JPanel();
    private RequestClient requestClient = new RequestClient();

    public static String getjTextFieldUserName() {
        return jTextFieldUserName.getText();
    }

    public InterfaceConnexion() throws IOException {

        //création de la fenêtre newconnection
        JFrame newConnection = new JFrame();
        newConnection.setMinimumSize(new Dimension(390, 220));
        newConnection.setLayout(new GridLayout(3,1));
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
        southPanel.add(plugIn);
        createCompte.setForeground(Color.BLUE.darker()); //label transformé en hyperlink standard
        createCompte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Pour que le curseur de la souris se transforme en icône de main lorsque l'utilisateur déplace la souris sur l'étiquette
        southPanel.add(createCompte);

        newConnection.setVisible(true);
        newConnection.revalidate();
        newConnection.repaint();

        //Actions à l'appui du bouton "Connecter" du panel du bas : redirection vers l'interface du chat
        plugIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Utilisateur user = RequestClient.chatConnect(jTextFieldUserName.getText(),jTextFieldPassWord.getText());

                    if(user != null) {
                        new Interface.InterfacePrincipale();
                    } else {
                        JOptionPane.showMessageDialog(null, "Connexion impossible");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        //Actions à l'appui de l'hyperlink du panel du bas : redirection vers l'interface de création d'un compte
        createCompte.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    new InterfaceAddAccount();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        new InterfaceConnexion();
    }
}
