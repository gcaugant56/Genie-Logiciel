package Controleur;

import Donnees.Contacts;
import Donnees.RequestClient;
import Interface.InterfaceNewConv;
import Interface.InterfacePrincipale;
import Singletons.Singletons;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class NewConvController implements ActionListener {

    private JButton startButton;

    public NewConvController(JButton startButton) {
        this.startButton = startButton;
        startButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = ((JButton)actionEvent.getSource()).getActionCommand();
        if(actionCommand.equals(startButton.getActionCommand())) {
            try {
                String verdict = null;
                RequestClient.addContact(InterfaceNewConv.getUtilisateur().getUserName(),(String)InterfaceNewConv.getComboBoxNewConv().getSelectedItem());
                verdict = PrincipaleController.getConnection().getVerdict();
                while(verdict == null)
                {
                    System.out.println("null");
                    verdict = PrincipaleController.getConnection().getVerdict();
                    TimeUnit.MILLISECONDS.sleep(100);

                }
                if(verdict.equals("false")) {
                    JOptionPane.showMessageDialog(null, "Ajout de contact impossible");
                }
                else {
                    Contacts contacts = Singletons.getGsonInstance().fromJson(verdict, Contacts.class);
                    InterfaceNewConv.getUtilisateur().setContacts(contacts);
                    JOptionPane.showMessageDialog(null, "Ajout de contact effectué");
                    InterfaceNewConv.getComboBoxPrincipale().addItem(contacts.getPseudo());
                    InterfaceNewConv.getNewConvWindows().dispose();
                }
            } catch(IOException | InterruptedException e) {
                JOptionPane.showMessageDialog(null, "Ajout de contact impossible");
            }
        }
    }
}
