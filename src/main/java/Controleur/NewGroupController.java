package Controleur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGroupController implements ActionListener {

    private JButton startTalk;

    public NewGroupController(JButton startTalk) {

        this.startTalk = startTalk;
        startTalk.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = ((JButton)actionEvent.getSource()).getActionCommand();
        if(actionCommand.equals(startTalk.getActionCommand())) {

        }
    }
}
