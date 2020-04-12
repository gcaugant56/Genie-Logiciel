package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InterfacePrincipale {

    private int numberUser;
    private JComboBox listeConv;
    private JLabel msg = new JLabel("Message : ");
    private JTextArea convText = new JTextArea(12,35);
    private JScrollPane scrollPane;
    private JLabel userOnLine = new JLabel("Nombre d'utilisateur en ligne : " + numberUser);
    private InterfacePrincipale interfacePrincipale;
    private JTextField msgText = new JTextField();
    private JButton myAccount = new JButton("Mon Compte");
    private JButton newConv = new JButton("Nouvelle Conversation");
    private JButton newGroup = new JButton("Nouveau Groupe");
    private JButton sendMsg = new JButton("Envoyer");
    private Object[] elements = new Object[]{"liste 1", "liste 2"};
    private String[] tab;
    private SimpleDateFormat formater = new SimpleDateFormat("h:mm a");

    public JTextField getMsgText() {
        return msgText;
    }

    public JTextArea getConvText() {
        return convText;
    }

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

        scrollPane = new JScrollPane(convText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        centerPanel.add(scrollPane);

        convText.setEditable(false);
        convText.setLineWrap(true);
        convText.setWrapStyleWord(true);

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

        myAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new InterfaceAccount();
            }
        });

        newConv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new InterfaceNewConv();
            }
        });

        newGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new InterfaceNewGroup();
            }
        });

        sendMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Date aujourdhui = new Date();
                String message= msgText.getText();
                if(!message.isEmpty()) {
                    convText.append(formater.format(aujourdhui) + " : " + message + "\n");
                    msgText.setText("");
                }
            }
        });

    }


    public static void main(String[] args) {
        new InterfacePrincipale();
    }
}
