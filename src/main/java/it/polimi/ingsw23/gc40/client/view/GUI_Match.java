package it.polimi.ingsw23.gc40.client.view;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.Observable;
import java.util.Observer;

public class GUI_Match implements Observer {
    private JPanel matchPanel;
    private JPanel goalPanel;
    private JLabel cg1Label;
    private JLabel cg2Label;
    private JLabel pgLabel;
    private JPanel centerPanel;
    private JPanel bsPanel;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void init() {
        System.out.println("ok");


    }


    @Override
    public void update(Observable o, Object arg) {
        //todo
        if (arg instanceof String)
            System.out.println(arg);
    }
}
