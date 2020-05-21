package Interface;

import Donnees.Racine;
import Donnees.RequestClient;
import Donnees.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Classe permettant de créer un compte
 */
public class InterfaceAddAccount {

    private JLabel createAccount = new JLabel("Créer un compte");
    private JLabel userName = new JLabel("Nom d'utilisateur : ");
    private JLabel pseudo = new JLabel("Pseudo : ");
    private JLabel password = new JLabel("Mot de passe : ");
    private JTextField jTextFieldUserName = new JTextField();
    private JTextField jTextFieldPseudo = new JTextField();
    private JTextField jTextFieldPassword = new JTextField();
    private JButton create = new JButton("Créer");
    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel(new GridLayout(3,2));
    private JPanel southPanel = new JPanel();

    /**
     * Constructeur qui permet de créer un compte
     * @throws IOException
     */
    public InterfaceAddAccount() throws IOException {

        //création de la fenêtre addaccount
        JFrame addAccount = new JFrame();
        addAccount.setMinimumSize(new Dimension(390, 220));
        addAccount.setLayout(new GridLayout(3,1));
        addAccount.setLocationRelativeTo(null);

        //ajout des panels du haut, du milieu et du bas à la fenêtre
        addAccount.add(northPanel,BorderLayout.NORTH);
        addAccount.add(centerPanel,BorderLayout.CENTER);
        addAccount.add(southPanel,BorderLayout.SOUTH);

        //ajout du label dans le panel du haut
        northPanel.add(createAccount, BorderLayout.CENTER);

        //ajout des composants dans le panel du milieu
        centerPanel.add(userName);
        centerPanel.add(jTextFieldUserName);
        centerPanel.add(pseudo);
        centerPanel.add(jTextFieldPseudo);
        centerPanel.add(password);
        centerPanel.add(jTextFieldPassword);

        //ajout du bouton "Créer" dans le panel du bas
        southPanel.add(create);

        addAccount.setVisible(true);
        addAccount.revalidate();
        addAccount.repaint();

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if(RequestClient.createAccount(jTextFieldUserName.getText(),jTextFieldPseudo.getText(),jTextFieldPassword.getText())) {
                        JOptionPane.showMessageDialog(null, "Création du compte réussie");
                        RequestClient.getSock().close();  //fermeture du socket création de compte
                        addAccount.setVisible(false);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Création du compte impossible");
                        RequestClient.getSock().close();  //fermeture du socket créationde compte
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
