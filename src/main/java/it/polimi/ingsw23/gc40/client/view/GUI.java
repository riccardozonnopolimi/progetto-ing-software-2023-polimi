package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GUI extends View {
    private JFrame frame;
    private JPanel panel;
    private JPanel newplayerPanel;
    private JTextField newplayerField;
    private JButton newplayerButton;
    private JLabel newplayerLabel;
    private JPanel newmatchPanel;
    private JSpinner newmatchSpinner;
    private JLabel newmatchLabel;
    private JButton newmatchButton;
    private JPanel waitingPanel;
    private JLabel waitingLabel;
    private JPanel gamePanel;
    private JPanel LRBPanel;
    private JLabel LRBImageLabel;
    private ImageIcon LRBImageIcon =new ImageIcon("img/boards/livingroom.png");



    public GUI(ConnectionController con) {

        super(con);


        newplayerLabel.setText("Insert player name:");
        newplayerButton.setText("Create player");
        newplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nickname = newplayerField.getText();
                try {
                    if (!con.addPlayer(nickname)) {
                        popup("Nome utente già in uso, riprova...");
                    } else {
                        newplayerField.setVisible(false);
                        if (con.hasQueueMatch()) {
                            matchId = con.addToLastMatch(nickname);
                            showOnly(waitingPanel);
                            popup("Sei stato aggiunto ad un match pre-esistente in attesa di giocatori.");

                        } else {
                            showOnly(newmatchPanel);
                        }

                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });


        newmatchLabel.setText("Enter number of players to create a Match");
        newmatchButton.setText("Create match");
        newmatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!con.newMatch((int) newmatchSpinner.getValue()))
                        popup("Inserisci un numero tra 2 e 4 giocatori.");
                    else {
                        matchId = con.addToLastMatch(nickname);
                        showOnly(waitingPanel);
                        popup("Match creato!");

                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        waitingLabel.setText("Attendi l'inizio del match!");

        LRBImageLabel.setIcon(LRBImageIcon);
    }

    public void init() {
        frame = new JFrame("MyShelfie");


        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        showOnly(newplayerPanel);


    }

    @Override
    public void updatePublicData(PublicModelNetwork publicData) {
        if (publicData.getIdMatch() == matchId || matchId == -1) {
            boolean wasplayable = match_data.isPlayable();
            String temp = null;
            if (match_data != null)
                temp = match_data.getCurrent();

            super.updatePublicData(publicData);

            System.out.println("aggiorno public");

            if (publicData.isPlayable()) {
                System.out.println("playable");
                if (!wasplayable) {
                    System.out.println("Match pronto!");
                    showOnly(gamePanel);
                }

                if (temp != null && match_data != null && this.nickname != null) {
                    System.out.println("tutto diverso da null");
                    if ((match_data.getCurrent().equals(this.nickname)) && !temp.equals(nickname)) {
                        System.out.println("tocca a te");
                    } else if (!match_data.getCurrent().equals(temp) && !match_data.getCurrent().equals(nickname) && !temp.equals(nickname))
                        System.out.println("non tocca a te");
                }
            }
        }
    }

    public void showOnly(JPanel panelAttr) {
        gamePanel.setVisible(false);
        waitingPanel.setVisible(false);
        newmatchPanel.setVisible(false);
        newplayerPanel.setVisible(false);
        panelAttr.setVisible(true);
    }

    public void popup(String st) {
        JOptionPane.showMessageDialog(frame, st);
    }

}
