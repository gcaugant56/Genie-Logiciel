package Interface;

import Donnees.RequestClient;
import Donnees.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InterfaceNewConv {

    private JLabel label1 = new JLabel("Destinataire : ");
    private JTextField textField1 = new JTextField(10);
    private JButton startButton = new JButton(" Demarrer");
    private Utilisateur utilisateur;
    private JPanel topPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();

    public InterfaceNewConv(Utilisateur user) {


        //création de la fenêtre newconwindows
        JFrame newConvWindows = new JFrame();
        newConvWindows.setMinimumSize(new Dimension(290, 120));
        newConvWindows.setLayout(new GridLayout(2,1));
        newConvWindows.setLocationRelativeTo(null);

        //ajout des panels du haut et du bas à la fenêtre
        newConvWindows.add(topPanel, new FlowLayout());
        newConvWindows.add(bottomPanel);

        //ajout des composants dans le panel du haut
        topPanel.add(label1);
        topPanel.add(textField1);

        //ajout des composants dans le panel du bas
        bottomPanel.add(startButton);

        newConvWindows.setVisible(true);
        newConvWindows.revalidate();
        newConvWindows.repaint();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    utilisateur = RequestClient.addContact(user.getUserName(),textField1.getText());
                    if(utilisateur != null) {
                        JOptionPane.showMessageDialog(null, "Contact ajouté");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur dans l'ajout du contact");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
