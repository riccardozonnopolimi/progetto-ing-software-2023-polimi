package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;

import javax.swing.*;
import java.awt.*;
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
    private JLabel cg1DescLabel;
    private JLabel cg2DescLabel;
    private JPanel lrbPanel;
    private PublicModelNetwork match_data;
    private PrivateModelNetwork player_data;
    private View view;

    public GUI_Match(PublicModelNetwork md, PrivateModelNetwork pd, View view) {
        this.view =view;
        this.match_data = md;
        this.player_data =pd;
        matchFrame = new JFrame("MyShelfie match");
        createUIComponents();
    }

    private void createUIComponents() {

        //showing common goal cards using file path
        cg2Label.setIcon(new ImageIcon("src/main/resources/img/cgs/"+match_data.getCgs()[1]+".jpg"));
        pgLabel.setIcon(new ImageIcon("src/main/resources/img/pgc/Personal_Goals"+ player_data.getPgc()+".png"));
        cg1Label.setIcon(new ImageIcon("src/main/resources/img/cgs/"+match_data.getCgs()[0]+".jpg"));
        cg1Label.setPreferredSize(new Dimension(200,100));
        cg2Label.setPreferredSize(new Dimension(200,100));
        cg2Label.setPreferredSize(new Dimension(100,200));


        cg1DescLabel.setText(view.cgDescriptions[match_data.getCgs()[0]]);
        cg2DescLabel.setText(view.cgDescriptions[match_data.getCgs()[1]]);


        lrbLabel.setIcon(new ImageIcon("src/main/resources/img/boards/livingroom.png"));
    }

    public void init() {




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
