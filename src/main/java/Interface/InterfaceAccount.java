package Interface;

import Donnees.RequestClient;
import Donnees.Utilisateur;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

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
    private Utilisateur utilisateur;

    public InterfaceAccount(Utilisateur user) {

        utilisateur = user;
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

        accountWindows.setVisible(true);
        accountWindows.revalidate();
        accountWindows.repaint();

        validateButton1.setEnabled(false);
        jtextFieldRepeatNewPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if(jtextFieldNewPassword.getText().equals(jtextFieldRepeatNewPassword.getText()) && jtextFieldNewPassword.getText().length() > 5) {
                    jtextFieldNewPassword.setForeground(Color.GREEN);
                    jtextFieldRepeatNewPassword.setForeground(Color.GREEN);
                    validateButton1.setEnabled(true);
                }
                else {
                    jtextFieldNewPassword.setForeground(Color.RED);
                    jtextFieldRepeatNewPassword.setForeground(Color.RED);
                    validateButton1.setEnabled(false);
                }
            }
        });

        //Actions à l'appui du bouton "Valider" du panel du haut
        validateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (RequestClient.checkPassword(utilisateur.getUserName(), jtextFieldOldPassword.getText(), jtextFieldNewPassword.getText())) {
                        JOptionPane.showMessageDialog(null, "Votre mot de passe a été modifié");
                    } else {
                        JOptionPane.showMessageDialog(null, "Le mot de passe n'est pas bon");
                        jtextFieldOldPassword.setBackground(Color.RED);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        displayCurrentPseudo.setText(user.getPseudo());
        displayCurrentPseudo.setHorizontalAlignment(JLabel.CENTER);
        displayCurrentPseudo.setVerticalAlignment(JLabel.CENTER);
        validateButton2.setEnabled(false);
        jtextFieldNewPseudo.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if(!jtextFieldNewPseudo.getText().equals(utilisateur.getPseudo()) && jtextFieldNewPseudo.getText().length() > 5) {
                    jtextFieldNewPseudo.setForeground(Color.GREEN);
                    validateButton2.setEnabled(true);
                }
                else {
                    jtextFieldNewPseudo.setForeground(Color.RED);
                    validateButton2.setEnabled(false);
                }
            }
        });

        //Actions à l'appui du bouton "Valider" du panel du bas
        validateButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    utilisateur = RequestClient.checkPseudo(user.getUserName(), jtextFieldNewPseudo.getText());
                    if (utilisateur != null) {
                        JOptionPane.showMessageDialog(null, "Votre pseudo a été modifié");
                        bottomPanel.revalidate();
                        displayCurrentPseudo.setText(utilisateur.getPseudo());
                    } else {
                        JOptionPane.showMessageDialog(null, "Le pseudo n'est pas valide");
                        jtextFieldNewPseudo.setBackground(Color.RED);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
