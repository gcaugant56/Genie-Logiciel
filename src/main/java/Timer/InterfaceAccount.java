package Timer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InterfaceAccount {

    private JPanel topPanel = new JPanel(new GridLayout(3,3));
    private JPanel bottomPanel = new JPanel(new GridLayout(3,3));
    private Border topTitle =  BorderFactory.createTitledBorder("Modifier votre mot de passe : ");
    private Border bottomTitle =  BorderFactory.createTitledBorder("Modifier votre nom d'utilisateur : ");
    private JLabel label1 = new JLabel("Ancien : ");
    private JLabel label2 = new JLabel("Nouveau : ");
    private JLabel label3 = new JLabel("Actuel : ");
    private JButton validateButton = new JButton("Valider");
    private JTextField textField = new JTextField();
    public InterfaceAccount() {
        JFrame accountWindows = new JFrame();
        accountWindows.setMinimumSize(new Dimension(390, 220));
        accountWindows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        accountWindows.setLayout(new GridLayout(2,1));
        accountWindows.setLocationRelativeTo(null);

        accountWindows.add(topPanel);
        accountWindows.add(bottomPanel);

        topPanel.setBorder(topTitle);
        bottomPanel.setBorder(bottomTitle);



        accountWindows.setVisible(true);
        accountWindows.revalidate();
        accountWindows.repaint();

    }

}
