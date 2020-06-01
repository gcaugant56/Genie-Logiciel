package Interface;

import Controleur.AccountController;
import Donnees.RequestClient;
import Donnees.Utilisateur;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import Timer.ClientConnexion;

/**
 * Interface pour la modification du compte de l'utilisateur
 */
public class InterfaceAccount {

    private JPanel topPanel;
    private Border topTitle =  BorderFactory.createTitledBorder("Modifier votre mot de passe : ");
    private Border bottomTitle =  BorderFactory.createTitledBorder("Modifier votre pseudo : ");
    private JLabel newRepeatPassword = new JLabel("Nouveau : ");
    private JLabel currentPseudo = new JLabel("Actuel : ");
    private JLabel newPseudo = new JLabel("Nouveau : ");
    private JButton validateButton1 = new JButton("Valider");
    private JButton validateButton2 = new JButton("Valider ");
    private AccountController accountController;
    private static JTextField jtextFieldOldPassword = new JTextField();
    private static JTextField jtextFieldNewPassword = new JTextField();
    private static JTextField jtextFieldRepeatNewPassword = new JTextField();
    private static JTextField jtextFieldNewPseudo = new JTextField();
    private static JFrame accountWindows;
    private static JLabel labelEmptyTopPanel = new JLabel("");
    private static JLabel labelEmptyBottomPanel = new JLabel("");
    private static JLabel displayCurrentPseudo = new JLabel("");
    private static Utilisateur utilisateur;
    private static JLabel oldPassword = new JLabel("Ancien : ");
    private static JLabel newPassword = new JLabel("Nouveau : ");
    private static JPanel bottomPanel;
    private static ClientConnexion connexion;

    public static JPanel getBottomPanel() {
        return bottomPanel;
    }

    public static JTextField getJtextFieldOldPassword() {
        return jtextFieldOldPassword;
    }

    public static JTextField getJtextFieldNewPassword() {
        return jtextFieldNewPassword;
    }

    public static JTextField getJtextFieldRepeatNewPassword() {
        return jtextFieldRepeatNewPassword;
    }

    public static JTextField getJtextFieldNewPseudo() {
        return jtextFieldNewPseudo;
    }

    public static Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public static ClientConnexion getConnexion() {
        return connexion;
    }

    public static JLabel getDisplayCurrentPseudo() {
        return displayCurrentPseudo;
    }

    public static JFrame getAccountWindows() {
        return accountWindows;
    }

    /**
     * Constructeur de la fenetre "Mon Compte"
     * @param user l'utilisateur connecté
     * @param connexion socket de l'utilisateur connecté
     */
    public InterfaceAccount(Utilisateur user, ClientConnexion connexion) {
        this.connexion = connexion;
        this.utilisateur = user;

        //création de la fenêtre Mon Compte
        accountWindows = new JFrame();
        accountWindows.setMinimumSize(new Dimension(490, 320));
        accountWindows.setLayout(new GridLayout(2,1));
        accountWindows.setLocationRelativeTo(null);

        topPanel = new JPanel(new GridLayout(3,3));
        bottomPanel = new JPanel(new GridLayout(2,3));

        //ajout des panels dans la fenêtre
        accountWindows.add(topPanel);
        accountWindows.add(bottomPanel);

        topPanel.setBorder(topTitle);
        bottomPanel.setBorder(bottomTitle);

        //ajout des composants dans le panel du haut
        topPanel.add(oldPassword);
        topPanel.add(jtextFieldOldPassword);
        topPanel.add(labelEmptyTopPanel);
        topPanel.add(newPassword);
        topPanel.add(jtextFieldNewPassword);
        topPanel.add(validateButton1);
        topPanel.add(newRepeatPassword);
        topPanel.add(jtextFieldRepeatNewPassword);

        //ajout des composants dans le panel du bas
        bottomPanel.add(currentPseudo);
        bottomPanel.add(displayCurrentPseudo);
        bottomPanel.add(labelEmptyBottomPanel);
        bottomPanel.add(newPseudo);
        bottomPanel.add(jtextFieldNewPseudo);
        bottomPanel.add(validateButton2);

        accountWindows.setVisible(true);
        accountWindows.revalidate();
        accountWindows.repaint();

        validateButton1.setEnabled(false);
        displayCurrentPseudo.setText(user.getPseudo());
        displayCurrentPseudo.setHorizontalAlignment(JLabel.CENTER);
        displayCurrentPseudo.setVerticalAlignment(JLabel.CENTER);
        validateButton2.setEnabled(false);
        accountController = new AccountController(validateButton1,validateButton2, jtextFieldRepeatNewPassword, jtextFieldNewPseudo);

    }

}
