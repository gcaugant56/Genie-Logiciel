package Controleur;

import Donnees.RequestClient;
import Interface.InterfaceAddAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddAccountController implements ActionListener {

    private JButton create;

    public AddAccountController(JButton create) {
        this.create = create;
        create.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = ((JButton)actionEvent.getSource()).getActionCommand();
        if(actionCommand.equals(create.getActionCommand())) {
            try {
                if(RequestClient.createAccount(InterfaceAddAccount.getjTextFieldUserName(),InterfaceAddAccount.getjTextFieldPseudo(),InterfaceAddAccount.getjTextFieldPassword())) {
                    JOptionPane.showMessageDialog(null, "Création du compte réussie");
                    RequestClient.getSock().close();  //fermeture du socket création de compte
                    InterfaceAddAccount.getAddAccount().setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Création du compte impossible");
                    RequestClient.getSock().close();  //fermeture du socket créationde compte
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
