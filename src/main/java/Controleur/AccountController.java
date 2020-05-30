package Controleur;

import Donnees.RequestClient;
import Interface.InterfaceAccount;
import Interface.InterfaceNewGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class AccountController implements ActionListener, KeyListener {

    private JButton validationButton1;
    private JButton validationButton2;
    private JTextField jTextFieldNewPseudo;
    private JTextField jTextFieldRepeatNewPassword;

    public AccountController(JButton validationButton1, JButton validationButton2, JTextField jTextFieldRepeatNewPassword, JTextField jTextFieldNewPseudo) {
        this.validationButton1 = validationButton1;
        this.validationButton2 = validationButton2;
        this.jTextFieldRepeatNewPassword = jTextFieldRepeatNewPassword;
        this.jTextFieldNewPseudo = jTextFieldNewPseudo;

        validationButton1.addActionListener(this);
        validationButton2.addActionListener(this);
        jTextFieldRepeatNewPassword.addKeyListener(this);
        jTextFieldNewPseudo.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = ((JButton)actionEvent.getSource()).getActionCommand();
        if(actionCommand.equals(validationButton1.getActionCommand())) {
            try {
                String verdict;
                RequestClient.checkPassword(InterfaceAccount.getUtilisateur().getUserName(), InterfaceAccount.getJtextFieldOldPassword().getText(), InterfaceAccount.getJtextFieldNewPassword().getText());
                verdict = InterfaceAccount.getConnexion().getVerdict();
                while(verdict == null)
                {
                    System.out.println("null");
                    verdict = InterfaceAccount.getConnexion().getVerdict();

                }
                if (Boolean.parseBoolean(verdict)) {
                    JOptionPane.showMessageDialog(null, "Votre mot de passe a été modifié");
                    InterfaceAccount.getJtextFieldOldPassword().setText("");
                    InterfaceAccount.getJtextFieldNewPassword().setText("");
                    InterfaceAccount.getJtextFieldRepeatNewPassword().setText("");
                    InterfaceAccount.getAccountWindows().dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Le mot de passe n'est pas bon");
                    InterfaceAccount.getJtextFieldOldPassword().setBackground(Color.RED);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(actionCommand.equals(validationButton2.getActionCommand())) {
            try {
                RequestClient.checkPseudo(InterfaceAccount.getUtilisateur().getUserName(), InterfaceAccount.getJtextFieldNewPseudo().getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String verdict = InterfaceAccount.getConnexion().getVerdict();
            while(verdict == null)
            {
                System.out.println("null");
                verdict = InterfaceAccount.getConnexion().getVerdict();
            }
            if (Boolean.parseBoolean(verdict))
            {
                InterfaceAccount.getUtilisateur().setPseudo(InterfaceAccount.getJtextFieldNewPseudo().getText());
                JOptionPane.showMessageDialog(null, "Votre pseudo a été modifié");
                InterfaceAccount.getBottomPanel().revalidate();
                InterfaceAccount.getDisplayCurrentPseudo().setText(InterfaceAccount.getUtilisateur().getPseudo());
                InterfaceAccount.getJtextFieldNewPseudo().setText("");
                InterfaceAccount.getAccountWindows().dispose();
            } else
            {
                JOptionPane.showMessageDialog(null, "Le pseudo n'est pas valide");
                InterfaceAccount.getJtextFieldNewPseudo().setBackground(Color.RED);
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        JTextField actionCommand = (JTextField)keyEvent.getSource();
        if(actionCommand.equals(jTextFieldNewPseudo)) {
            if(!InterfaceAccount.getJtextFieldNewPseudo().getText().equals(InterfaceAccount.getUtilisateur().getPseudo()) && InterfaceAccount.getJtextFieldNewPseudo().getText().length() > 5) {
                InterfaceAccount.getJtextFieldNewPseudo().setForeground(Color.GREEN);
                validationButton2.setEnabled(true);
            }
            else {
                InterfaceAccount.getJtextFieldNewPseudo().setForeground(Color.RED);
                validationButton2.setEnabled(false);
            }
        } else if(actionCommand.equals(jTextFieldRepeatNewPassword)) {
            if(InterfaceAccount.getJtextFieldNewPassword().getText().equals(InterfaceAccount.getJtextFieldRepeatNewPassword().getText()) && InterfaceAccount.getJtextFieldNewPassword().getText().length() > 5) {
                InterfaceAccount.getJtextFieldNewPassword().setForeground(Color.GREEN);
                InterfaceAccount.getJtextFieldRepeatNewPassword().setForeground(Color.GREEN);
                validationButton1.setEnabled(true);
            }
            else {
                InterfaceAccount.getJtextFieldNewPassword().setForeground(Color.RED);
                InterfaceAccount.getJtextFieldRepeatNewPassword().setForeground(Color.RED);
                validationButton1.setEnabled(false);
            }
        }
    }
}
