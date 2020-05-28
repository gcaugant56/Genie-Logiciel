package Interface;

import Donnees.Contacts;
import Donnees.RequestClient;
import Donnees.Utilisateur;
import Timer.ClientConnexion;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Type;

public class InterfaceNewConv {

    private JLabel label1 = new JLabel("Destinataire : ");
    private static JComboBox comboBoxNewConv;
    private static JComboBox comboBoxPrincipale;
    private JButton startButton = new JButton(" Demarrer");
    private JPanel topPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private Utilisateur utilisateur;
    private String[] pseudonymeList;

    public InterfaceNewConv(Utilisateur user, String[] pseudoList, ClientConnexion connexion, JComboBox comboBox) {

        utilisateur = user;
        pseudonymeList = pseudoList;
        comboBoxPrincipale = comboBox;

        //création de la fenêtre newconwindows
        JFrame newConvWindows = new JFrame();
        newConvWindows.setMinimumSize(new Dimension(390, 220));
        newConvWindows.setLayout(new GridLayout(2,1));
        newConvWindows.setLocationRelativeTo(null);

        //ajout des panels du haut et du bas à la fenêtre
        newConvWindows.add(topPanel, new FlowLayout());
        newConvWindows.add(bottomPanel);


        comboBoxNewConv = new JComboBox(pseudonymeList);

        //ajout des composants dans le panel du haut
        topPanel.add(label1);
        topPanel.add(comboBoxNewConv);
        comboBox.setPreferredSize(new Dimension(100, 30));

        //ajout des composants dans le panel du bas
        bottomPanel.add(startButton);

        newConvWindows.setVisible(true);
        newConvWindows.revalidate();
        newConvWindows.repaint();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String verdict = null;
                    RequestClient.addContact(utilisateur.getUserName(),(String)comboBoxNewConv.getSelectedItem());
                    verdict = connexion.getVerdict();
                    while(verdict == null)
                    {
                        System.out.println("null");
                        verdict = connexion.getVerdict();

                    }
                    if(verdict.equals("false")) {
                        JOptionPane.showMessageDialog(null, "Ajout de contact impossible");
                    }
                    else {
                        Gson gson = new Gson();
                        Contacts contacts = gson.fromJson(verdict, (Type) Contacts.class);
                        utilisateur.setContacts(contacts);
                        JOptionPane.showMessageDialog(null, "Ajout de contact effectué");
                        comboBoxPrincipale.addItem(contacts.getPseudo());
                        newConvWindows.dispose();
                    }
                } catch(IOException e) {
                    JOptionPane.showMessageDialog(null, "Ajout de contact impossible");
                }
            }
        });
    }

}
