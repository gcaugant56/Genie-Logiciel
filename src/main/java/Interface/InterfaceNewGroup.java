package Interface;

import javax.swing.*;
import java.awt.*;

public class InterfaceNewGroup {

    private JLabel label1 = new JLabel("Nom du groupe : ");
    private JLabel label2 = new JLabel("<html>Entrez les noms des destinataires<br>espacés par une virgule :</html>");
    private JTextField textField1 = new JTextField(15);
    private JTextField textField2 = new JTextField(15);
    private JButton startTalk = new JButton("Demarrer la discussion");

    public InterfaceNewGroup() {
        JFrame newGroupWindows = new JFrame();
        newGroupWindows.setMinimumSize(new Dimension(390, 220));
        newGroupWindows.setLayout(new GridLayout(3,1));
        newGroupWindows.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new FlowLayout());
        newGroupWindows.add(topPanel);
        JPanel centerPanel = new JPanel(new GridLayout(2,1));
        newGroupWindows.add(centerPanel);
        JPanel bottomPanel = new JPanel();
        newGroupWindows.add(bottomPanel);

        label2.setHorizontalAlignment(SwingConstants.CENTER);

        topPanel.add(label1);
        topPanel.add(textField1);
        centerPanel.add(label2);
        centerPanel.add(textField2);
        bottomPanel.add(startTalk);

        newGroupWindows.setVisible(true);
        newGroupWindows.revalidate();
        newGroupWindows.repaint();
    }

}
