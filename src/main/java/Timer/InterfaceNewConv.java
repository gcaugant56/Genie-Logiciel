package Timer;

import javax.swing.*;
import java.awt.*;

public class InterfaceNewConv {

    private JLabel label1 = new JLabel("Destinataire : ");
    private JTextField textField1 = new JTextField(10);
    private JButton startButton = new JButton(" Demarrer");

    public InterfaceNewConv() {
        JFrame newConvWindows = new JFrame();
        newConvWindows.setMinimumSize(new Dimension(290, 120));
        newConvWindows.setLayout(new GridLayout(2,1));
        newConvWindows.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        newConvWindows.add(topPanel, new FlowLayout());
        newConvWindows.add(bottomPanel);

        topPanel.add(label1);
        topPanel.add(textField1);

        bottomPanel.add(startButton);

        newConvWindows.setVisible(true);
        newConvWindows.revalidate();
        newConvWindows.repaint();

    }
}
