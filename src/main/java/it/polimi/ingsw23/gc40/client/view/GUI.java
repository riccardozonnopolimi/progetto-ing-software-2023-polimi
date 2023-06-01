package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GUI extends View {
    private JButton okBTN;
    private JPanel jpanel;
    private JFrame jframe;
    private JTextField nicknameField;
    private JTextPane inserisciNomeGiocatoreTextPane;
    private JPanel insNicknamePanel;
    private JPanel insNumGiocPanel;
    private JTextPane staiCreandoUnNuovoTextPane;
    private JTextField numGiocField;
    private JButton matchCreateBtn;
    private JSpinner numPlayerSpinner;

    private void popup(String st) {
        JOptionPane.showMessageDialog(jframe, st);
    }

    public GUI(ConnectionController con) {
        super(con);
        okBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //errorField.setText(nicknameField.getText());

                    if (!con.addPlayer(nicknameField.getText()))
                        popup("Nome utente già in uso, riprova...");

                    else {
                        insNicknamePanel.setVisible(false);
                        nickname=nicknameField.getText();
                        if (con.hasQueueMatch()) {
                            popup("Sei stato aggiunto ad un match pre-esistente in attesa di giocatori.");
                        } else {
                            insNumGiocPanel.setVisible(true);
                        }

                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        matchCreateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if(!con.newMatch((int) numPlayerSpinner.getValue()))
                        popup("Inserisci un numero tra 2 e 4 giocatori.");
                    else
                        popup("Match creato!");
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
    }

    @Override
    public void init() {
        jframe = new JFrame("MyShelfie");
        jframe.setContentPane(jpanel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setVisible(true);
        insNumGiocPanel.setVisible(false);
    }

}
