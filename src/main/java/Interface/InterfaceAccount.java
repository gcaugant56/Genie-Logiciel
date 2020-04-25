package Interface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceAccount {

    private JPanel topPanel = new JPanel(new GridLayout(3,3));
    private JPanel bottomPanel = new JPanel(new GridLayout(2,3));
    private Border topTitle =  BorderFactory.createTitledBorder("Modifier votre mot de passe : ");
    private Border bottomTitle =  BorderFactory.createTitledBorder("Modifier votre pseudo : ");
    private JLabel oldPassword = new JLabel("Ancien : ");
    private JLabel newPassword = new JLabel("Nouveau : ");
    private JLabel newRepeatPassword = new JLabel("Nouveau : ");
    private JLabel currentPseudo = new JLabel("Actuel : ");
    private JLabel newPseudo = new JLabel("Nouveau : ");
    private JLabel labelEmptyTopPanel = new JLabel("");
    private JLabel labelEmptyBottomPanel = new JLabel("");
    private JLabel displayCurrentPseudo = new JLabel("");
    private JButton validateButton1 = new JButton("Valider");
    private JButton validateButton2 = new JButton("Valider");
    private JTextField jtextFieldOldPassword = new JTextField();
    private JTextField jtextFieldNewPassword = new JTextField();
    private JTextField jtextFieldRepeatNewPassword = new JTextField();
    private JTextField jtextFieldNewPseudo = new JTextField();

    public InterfaceAccount() {
        //création de la fenêtre Mon Compte
        JFrame accountWindows = new JFrame();
        accountWindows.setMinimumSize(new Dimension(490, 320));
        accountWindows.setLayout(new GridLayout(2,1));
        accountWindows.setLocationRelativeTo(null);

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

        //Actions à l'appui du bouton "Valider" du panel du haut
        validateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        //Actions à l'appui du bouton "Valider" du panel du bas
        validateButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        accountWindows.setVisible(true);
        accountWindows.revalidate();
        accountWindows.repaint();

    }

}
