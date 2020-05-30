package Controleur;

import Donnees.RequestClient;
import Donnees.Utilisateur;
import Interface.InterfaceAddAccount;
import Interface.InterfaceConnexion;
import Interface.InterfacePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class ConnexionController implements ActionListener, MouseListener {

    private JButton logIn;
    private JLabel createAccountLink;

    public ConnexionController(JButton logIn, JLabel createAccountLink) {
        this.logIn = logIn;
        this.createAccountLink = createAccountLink;
        logIn.addActionListener(this);
        createAccountLink.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        if(actionCommand.equals(logIn.getActionCommand())) {
            try {
                Utilisateur user = RequestClient.chatConnect(InterfaceConnexion.getjTextFieldUserName(),InterfaceConnexion.getjTextFieldPassWord());
                if(user != null && InterfaceConnexion.getjTextFieldUserName() != "" && InterfaceConnexion.getjTextFieldPassWord() != "") {
                    new InterfacePrincipale(user);
                    InterfaceConnexion.getNewConnection().dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Connexion impossible");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getSource().equals(createAccountLink)) {
            try {
                new InterfaceAddAccount();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
