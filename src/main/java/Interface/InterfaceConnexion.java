package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceConnexion {
    private JLabel chat = new JLabel("Accédez au Chat");
    private JLabel userName = new JLabel("Identifiant : ");
    private JLabel passWord = new JLabel("Mot de passe : ");
    private JTextField jTextFieldUserName = new JTextField();
    private JTextField jTextFieldPassWord = new JTextField();
    private JButton plugIn = new JButton("Connexion");
    private Font font = new Font("Arial", Font.BOLD, 12);
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
        southPanel.add(plugIn,BorderLayout.CENTER);

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
    }

    public static void main(String[] args) {
        new InterfaceConnexion();
    }
}
