package Timer;

import javax.swing.*;
import java.awt.*;

public class InterfacePrincipale {

    private JComboBox listeConv;
    private JTextArea convText = new JTextArea();
    private JLabel msg = new JLabel("Message : ");
    private JLabel userOnLine = new JLabel("Nombre d'utilisateur en ligne : ");
    private JTextField msgText = new JTextField();
    private JButton myAccount = new JButton("Mon Compte");
    private JButton newConv = new JButton("Nouvelle Conversation");
    private JButton newGroup = new JButton("Nouveau Groupe");
    private JButton sendMsg = new JButton("Envoyer");
    private Object[] elements = new Object[]{"liste 1", "liste 2"};

    public InterfacePrincipale() {

        // déclaration de la fenêtre principale
        JFrame mainWindows = new JFrame();
        mainWindows.setMinimumSize(new Dimension(640, 380));
        mainWindows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindows.setLayout(new BorderLayout());
        mainWindows.setLocationRelativeTo(null);

        JPanel northPanel = new JPanel();
        mainWindows.add(northPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel();
        mainWindows.add(centerPanel, BorderLayout.CENTER);
        JPanel eastPanel = new JPanel();
        mainWindows.add(eastPanel, BorderLayout.EAST);
        eastPanel.setLayout(new GridLayout(4,0));
        JPanel southPanel = new JPanel();
        mainWindows.add(southPanel, BorderLayout.SOUTH);
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));

        JPanel centerNorthPanel = new JPanel();
        centerPanel.add(centerNorthPanel, BorderLayout.NORTH);
        JPanel centerSouthPanel = new JPanel();
        centerPanel.add(centerSouthPanel, BorderLayout.SOUTH);

        JPanel southWestPanel = new JPanel();
        southPanel.add(southWestPanel, BorderLayout.WEST);
        JPanel southEastPanel = new JPanel();
        southPanel.add(southEastPanel, BorderLayout.EAST);

        listeConv = new JComboBox(elements);
        northPanel.add(listeConv, BorderLayout.WEST);
        listeConv.setPreferredSize(new Dimension(300, 30));

        centerPanel.add(convText);
        convText.setPreferredSize(new Dimension(400, 300));

        eastPanel.add(myAccount);
        eastPanel.add(newConv);
        eastPanel.add(newGroup);
        eastPanel.add(userOnLine);

        southPanel.add(msg, BorderLayout.WEST);
        southPanel.add(msgText);
        southPanel.add(sendMsg);
        msgText.setPreferredSize(new Dimension(300,50));


        mainWindows.getContentPane().add(centerPanel);
        mainWindows.setVisible(true);
        mainWindows.revalidate();
        mainWindows.repaint();
    }


    public static void main(String[] args) {

        new InterfacePrincipale();

    }
}
