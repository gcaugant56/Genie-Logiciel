package Interface;

import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class InterfaceConnexion {
    private JLabel chat = new JLabel("Accédez au Chat");
    private JLabel userName = new JLabel("Identifiant : ");
    private JLabel passWord = new JLabel("Mot de passe : ");
    private JTextField jTextFieldUserName = new JTextField();
    private JTextField jTextFieldPassWord = new JTextField();
    private JButton plugIn = new JButton("Connexion");
    private Font font = new Font("Arial", Font.BOLD, 12);
    private JLabel createCompte = new JLabel("Pas de compte créez-en un ici");
    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel(new GridLayout(2,2));
    private JPanel southPanel = new JPanel();

    public InterfaceConnexion() {
        JFrame newConnection = new JFrame();
        newConnection.setMinimumSize(new Dimension(390, 220));
        newConnection.setLayout(new GridLayout(3,1));
        newConnection.setLocationRelativeTo(null);

        newConnection.add(northPanel,BorderLayout.NORTH);
        newConnection.add(centerPanel,BorderLayout.CENTER);
        newConnection.add(southPanel,BorderLayout.SOUTH);

        chat.setFont(font);
        northPanel.add(chat,BorderLayout.CENTER);

        centerPanel.add(userName);
        centerPanel.add(jTextFieldUserName);
        centerPanel.add(passWord);
        centerPanel.add(jTextFieldPassWord);

        southPanel.setLayout(new GridLayout(2,1));
        southPanel.add(plugIn);
        createCompte.setForeground(Color.BLUE.darker());
        createCompte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        southPanel.add(createCompte);

        newConnection.setVisible(true);
        newConnection.revalidate();
        newConnection.repaint();

        plugIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new InterfacePrincipale();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Connexion impossible");
                }
            }
        });

        createCompte.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new InterfaceAddAccount();
            }
        });
    }

    public static void main(String[] args) {
        new InterfaceConnexion();
    }
}
