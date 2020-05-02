package Interface;

import Données.RequestClient;
import Données.RequestCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class InterfaceAddAccount implements RequestClient {

    private JLabel createAccount = new JLabel("Créer un compte");
    private JLabel userName = new JLabel("Nom d'utilisateur : ");
    private JLabel pseudo = new JLabel("Pseudo : ");
    private JLabel password = new JLabel("Mot de passe : ");
    private JTextField jTextFieldUserName = new JTextField();
    private JTextField jTextFieldPseudo = new JTextField();
    private JTextField jTextFieldPassword = new JTextField();
    private JButton create = new JButton("Créer");
    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel(new GridLayout(3,2));
    private JPanel southPanel = new JPanel();

    public InterfaceAddAccount() {

        //création de la fenêtre addaccount
        JFrame addAccount = new JFrame();
        addAccount.setMinimumSize(new Dimension(390, 220));
        addAccount.setLayout(new GridLayout(3,1));
        addAccount.setLocationRelativeTo(null);

        //ajout des panels du haut, du milieu et du bas à la fenêtre
        addAccount.add(northPanel,BorderLayout.NORTH);
        addAccount.add(centerPanel,BorderLayout.CENTER);
        addAccount.add(southPanel,BorderLayout.SOUTH);

        //ajout du label dans le panel du haut
        northPanel.add(createAccount, BorderLayout.CENTER);

        //ajout des composants dans le panel du milieu
        centerPanel.add(userName);
        centerPanel.add(jTextFieldUserName);
        centerPanel.add(pseudo);
        centerPanel.add(jTextFieldPseudo);
        centerPanel.add(password);
        centerPanel.add(jTextFieldPassword);

        //ajout du bouton "Créer" dans le panel du bas
        southPanel.add(create);

        addAccount.setVisible(true);
        addAccount.revalidate();
        addAccount.repaint();

        //Actions à l'appui du bouton "Créer" du panel du bas
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if(createAccount()) {
                        JOptionPane.showMessageDialog(null, "Création du compte réussie");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Création du compte impossible");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean createAccount() throws IOException {
        String requestAccount = RequestCode.CREATION_COMPTE+"*"+jTextFieldUserName.getText() +"*"+jTextFieldPseudo.getText()+"*"+jTextFieldPassword.getText();
        Socket sock = new Socket("127.0.0.1",1515);

        //nous créons donc un flux en écriture
        BufferedOutputStream bos = new BufferedOutputStream(sock.getOutputStream());

        //nous écrivons notre requête
        bos.write(requestAccount.getBytes());

        //Vu que nous utilisons un buffer, nous devons utiliser la méthode flush afin que les données soient bien écrites et envoyées au serveur
        bos.flush();

        //On récupère maintenant la réponse du serveur dans un flux
        BufferedInputStream bis = new BufferedInputStream(sock.getInputStream());

        //Il ne nous reste plus qu'à le lire
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = bis.read(b);
        response = new String(b, 0, stream);

        return Boolean.parseBoolean(response);
    }
}
