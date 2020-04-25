package Interface;

import javax.swing.*;
import java.awt.*;

public class InterfaceNewConv {

    private JLabel label1 = new JLabel("Destinataire : ");
    private JTextField textField1 = new JTextField(10);
    private JButton startButton = new JButton(" Demarrer");
    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();

    public InterfaceNewConv() {

        //création de la fenêtre newconwindows
        JFrame newConvWindows = new JFrame();
        newConvWindows.setMinimumSize(new Dimension(290, 120));
        newConvWindows.setLayout(new GridLayout(2,1));
        newConvWindows.setLocationRelativeTo(null);

        //ajout des panels du haut et du bas à la fenêtre
        newConvWindows.add(topPanel, new FlowLayout());
        newConvWindows.add(bottomPanel);

        //ajout des composants dans le panel du haut
        topPanel.add(label1);
        topPanel.add(textField1);

        //ajout des composants dans le panel du bas
        bottomPanel.add(startButton);

        newConvWindows.setVisible(true);
        newConvWindows.revalidate();
        newConvWindows.repaint();

    }
}
