package Controleur;

import Donnees.Groupe;
import Donnees.Racine;
import Donnees.RequestClient;
import Interface.InterfaceNewGroup;
import Interface.InterfacePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class NewGroupController implements ActionListener {

    private JButton startTalk;
    private Racine Json = null;
    private Groupe groupe;
    private String json = "";

    public NewGroupController(JButton startTalk) {

        this.startTalk = startTalk;
        startTalk.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = ((JButton)actionEvent.getSource()).getActionCommand();
        if(actionCommand.equals(startTalk.getActionCommand())) {
            try {
                if(!InterfaceNewGroup.getJtextFieldNameOfGroup().getText().equals("") && !InterfaceNewGroup.getJtextFieldRecipients().getText().equals("")) {
                    RequestClient.createGroup(InterfaceNewGroup.getJtextFieldNameOfGroup().getText(), InterfaceNewGroup.getJtextFieldRecipients().getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Création de groupe impossible");
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Création de groupe impossible");
            }
        }
    }
}
