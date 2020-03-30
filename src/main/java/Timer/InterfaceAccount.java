package Timer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InterfaceAccount {

    private JPanel topPanel = new JPanel(new GridLayout(3,3));
    private JPanel bottomPanel = new JPanel(new GridLayout(2,3));
    private Border topTitle =  BorderFactory.createTitledBorder("Modifier votre mot de passe : ");
    private Border bottomTitle =  BorderFactory.createTitledBorder("Modifier votre nom d'utilisateur : ");
    private JLabel label1 = new JLabel("Ancien : ");
    private JLabel label2 = new JLabel("Nouveau : ");
    private JLabel label3 = new JLabel("Nouveau : ");
    private JLabel label4 = new JLabel("Actuel : ");
    private JLabel label5 = new JLabel("Nouveau : ");
    private JLabel label6 = new JLabel("");
    private JLabel label7 = new JLabel("");
    private JButton validateButton = new JButton("Valider");
    private JButton validateButton1 = new JButton("Valider");
    private JTextField textField = new JTextField();
    private JTextField textField1 = new JTextField();
    private JTextField textField2 = new JTextField();
    private JTextField textField3 = new JTextField();
    private JTextField textField4 = new JTextField();
    private JTextField textField5 = new JTextField();

    public InterfaceAccount() {
        JFrame accountWindows = new JFrame();
        accountWindows.setMinimumSize(new Dimension(490, 320));
        accountWindows.setLayout(new GridLayout(2,1));
        accountWindows.setLocationRelativeTo(null);

        accountWindows.add(topPanel);
        accountWindows.add(bottomPanel);

        topPanel.setBorder(topTitle);
        bottomPanel.setBorder(bottomTitle);

        topPanel.add(label1);
        topPanel.add(textField);
        topPanel.add(label6);
        topPanel.add(label2);
        topPanel.add(textField1);
        topPanel.add(validateButton);
        topPanel.add(label3);
        topPanel.add(textField2);

        bottomPanel.add(label4);
        bottomPanel.add(textField3);
        bottomPanel.add(label7);
        bottomPanel.add(label5);
        bottomPanel.add(textField4);
        bottomPanel.add(validateButton1);

        accountWindows.setVisible(true);
        accountWindows.revalidate();
        accountWindows.repaint();

    }

}
