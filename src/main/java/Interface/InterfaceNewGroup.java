package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceNewGroup {

    private JLabel nameOfGroup = new JLabel("Nom du groupe : ");
    private JLabel recipients = new JLabel("<html>Entrez les noms des destinataires<br>espacés par une virgule :</html>");
    private JTextField jtextFieldNameOfGroup = new JTextField(15);
    private JTextField jtextFieldRecipients = new JTextField(15);
    private JButton startTalk = new JButton("Demarrer la discussion");
    JPanel topPanel = new JPanel(new FlowLayout());
    JPanel centerPanel = new JPanel(new GridLayout(2,1));
    JPanel bottomPanel = new JPanel();

    public InterfaceNewGroup() {

        //création de la fenêtre newgroupwindows
        JFrame newGroupWindows = new JFrame();
        newGroupWindows.setMinimumSize(new Dimension(390, 220));
        newGroupWindows.setLayout(new GridLayout(3,1));
        newGroupWindows.setLocationRelativeTo(null);

        //ajout des panels du haut, du milieu et du bas à la fenêtre
        newGroupWindows.add(topPanel);
        newGroupWindows.add(centerPanel);
        newGroupWindows.add(bottomPanel);

        //centrer le label
        recipients.setHorizontalAlignment(SwingConstants.CENTER);

        //ajout des composants dans les différents panels
        topPanel.add(nameOfGroup);
        topPanel.add(jtextFieldNameOfGroup);
        centerPanel.add(recipients);
        centerPanel.add(jtextFieldRecipients);
        bottomPanel.add(startTalk);

        //Actions à l'appui du bouton "Démarrer la discussion" :
        startTalk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        newGroupWindows.setVisible(true);
        newGroupWindows.revalidate();
        newGroupWindows.repaint();
    }

}
