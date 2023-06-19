package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class GUI_Match implements Observer {
    private JFrame matchFrame;

    private JPanel matchPanel;
    private JPanel goalPanel;
    private JLabel cg1Label;
    private JLabel cg2Label;
    private JLabel pgLabel;
    private JPanel centerPanel;
    private JPanel bsPanel;
    private PublicModelNetwork match_data;
    private PrivateModelNetwork player_data;
    private ImageIcon LRBImageIcon = new ImageIcon("src/main/resources/img/boards/livingroom.png");
    private ImageIcon cg1ImageIcon;
    private ImageIcon cg2ImageIcon;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void init() {

        cg1ImageIcon = new ImageIcon("src/main/resources/img/common goal card/"+match_data.getCgs()[0]+".jpg");
        cg2ImageIcon = new ImageIcon("src/main/resources/img/common goal card/"+match_data.getCgs()[1]+".jpg");

        cg1Label.setIcon(cg1ImageIcon);
        cg2Label.setIcon(cg2ImageIcon);

        matchFrame = new JFrame("MyShelfie match");
        matchFrame.setContentPane(matchPanel);
        matchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        matchFrame.pack();
        matchFrame.setVisible(true);

    }


    @Override
    public void update(Observable o, Object arg) {
        //todo comunicare aggiornamenti turno etc
        if (arg instanceof PrivateModelNetwork)
            this.player_data = (PrivateModelNetwork) arg;
        if (arg instanceof PublicModelNetwork)
            this.match_data  = (PublicModelNetwork) arg;
    }
}
