package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Observer;

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

    /*// resize image
    Image LRBResized = LRBImageIcon.getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH);
    ImageIcon LRBResizedIcon = new ImageIcon(LRBResized);*/

    public GUI(ConnectionController con) {

        super(con);


        newplayerLabel.setText("Insert player name:");
        newplayerButton.setText("Create player");
        newplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nickname = newplayerField.getText();
                try {
                    if (!con.connect(nickname)) {
                        popup("Nome utente già in uso, riprova...");
                    } else {
                        newplayerField.setVisible(false);
                        if (con.hasQueueMatch()) {
                            matchId = con.addToLastMatch(nickname);
                            showOnly(waitingPanel);
                            con.addObserver(null, nickname, matchId);

                            // todo avvisare il giocatore di ciò che segue:
                            // popup("Sei stato aggiunto al match n."+matchId);
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
                    if (!con.newMatch((int) newmatchSpinner.getValue())) {
                        if (con.hasQueueMatch()) {
                            matchId = con.addToLastMatch(nickname);
                            showOnly(waitingPanel);
                            con.addObserver(null, nickname, matchId);
                            popup("Non è stato possibile creare il match perchè esiste un match appena creato in attesa di giocatori.");
                            popup("Sei stato aggiunto al match n."+matchId);
                        } else
                            popup("Inserisci un numero tra 2 e 4 giocatori.");
                    } else {
                        matchId = con.addToLastMatch(nickname);
                        showOnly(waitingPanel);
                        con.addObserver(null, nickname, matchId);
                        popup("Match creato!");

                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        waitingLabel.setText("Attendi l'inizio del match!");

    }

    private void startMatch() {
        GUI_Match matchFrame = new GUI_Match(match_data, player_data, this);
        this.addObserver(matchFrame);
        matchFrame.init();
        frame.setExtendedState(JFrame.ICONIFIED);
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

        if (publicData.getIdMatch() == this.matchId || this.matchId == -1) {

            boolean wasplayable = this.match_data.isPlayable();

            super.updatePublicData(publicData);

            setChanged();
            notifyObservers(this.match_data);
            //System.out.println("aggiorno public");

            if (this.match_data.isPlayable()) {
                //System.out.println("playable");
                if (!wasplayable) {
                    //System.out.println("Match pronto!");
                    startMatch();
                }
            }
        }
    }

    @Override
    public void updatePrivateData(PrivateModelNetwork privateData)
    {
        super.updatePrivateData(privateData);
        setChanged();
        notifyObservers(this.player_data);
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

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(match_data);
        setChanged();
        notifyObservers(player_data);
    }
}