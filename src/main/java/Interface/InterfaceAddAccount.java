package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public InterfaceAddAccount() {
        JFrame addAccount = new JFrame();
        addAccount.setMinimumSize(new Dimension(390, 220));
        addAccount.setLayout(new GridLayout(3,1));
        addAccount.setLocationRelativeTo(null);

        addAccount.add(northPanel,BorderLayout.NORTH);
        addAccount.add(centerPanel,BorderLayout.CENTER);
        addAccount.add(southPanel,BorderLayout.SOUTH);

        northPanel.add(createAccount, BorderLayout.CENTER);

        centerPanel.add(userName);
        centerPanel.add(jTextFieldUserName);
        centerPanel.add(pseudo);
        centerPanel.add(jTextFieldPseudo);
        centerPanel.add(password);
        centerPanel.add(jTextFieldPassword);

        southPanel.add(create);

        addAccount.setVisible(true);
        addAccount.revalidate();
        addAccount.repaint();

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }
}
