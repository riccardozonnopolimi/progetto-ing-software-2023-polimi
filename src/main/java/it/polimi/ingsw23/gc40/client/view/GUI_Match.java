package it.polimi.ingsw23.gc40.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Match {
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
    private JButton button1;
    private JButton it00;
    private JButton it01;
    private JButton it02;
    private JButton it03;
    private JButton it04;
    private JButton it05;
    private JButton it06;
    private JButton it07;
    private JButton it08;
    private JButton it10;
    private JButton it11;
    private JButton it12;
    private JButton it13;
    private JButton it14;
    private JButton it15;
    private JButton it16;
    private JButton it17;
    private JButton it18;
    private JButton it20;
    private JButton it21;
    private JButton it22;
    private JButton it23;
    private JButton it24;
    private JButton it25;
    private JButton it26;
    private JButton it27;
    private JButton it28;
    private JButton it30;
    private JButton it31;
    private JButton it32;
    private JButton it33;
    private JButton it34;
    private JButton it35;
    private JButton it36;
    private JButton it37;
    private JButton it38;
    private JButton it40;
    private JButton it41;
    private JButton it42;
    private JButton it43;
    private JButton it44;
    private JButton it45;
    private JButton it46;
    private JButton it47;
    private JButton it48;
    private JButton it50;
    private JButton it51;
    private JButton it52;
    private JButton it53;
    private JButton it54;
    private JButton it55;
    private JButton it56;
    private JButton it57;
    private JButton it58;
    private JButton it60;
    private JButton it61;
    private JButton it62;
    private JButton it63;
    private JButton it64;
    private JButton it65;
    private JButton it66;
    private JButton it67;
    private JButton it68;
    private JButton it70;
    private JButton it71;
    private JButton it72;
    private JButton it73;
    private JButton it74;
    private JButton it75;
    private JButton it76;
    private JButton it77;
    private JButton it78;
    private JButton it80;
    private JButton it81;
    private JButton it82;
    private JButton it83;
    private JButton it84;
    private JButton it85;
    private JButton it86;
    private JButton it87;
    private JButton it88;


    private View view;

    public GUI_Match(View view) {
        this.view = view;


        matchFrame = new JFrame("MyShelfie match");
        createUIComponents();

    }

    private void createUIComponents() {
        // dentro la setIcon() > new ImageIcon(new ImageIcon(" PATH ").getImage().getScaledInstance(200,500, Image.SCALE_SMOOTH))


        //showing common goal cards using file path
        cg2Label.setIcon(new ImageIcon(new ImageIcon("src/main/resources/img/cgs/" + (view.match_data.getCgs()[1] ) + ".jpg").getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH)));
        cg1Label.setIcon(new ImageIcon(new ImageIcon("src/main/resources/img/cgs/" + (view.match_data.getCgs()[0] ) + ".jpg").getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH)));
        pgLabel.setIcon(new ImageIcon(new ImageIcon("src/main/resources/img/pgc/" + view.player_data.getPgc() + ".png").getImage().getScaledInstance(200, 500, Image.SCALE_SMOOTH)));

        cg2Label.setText("");
        cg1Label.setText("");
        pgLabel.setText("");



        //TODO ELIMINARE
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(view.pgCards.get(view.player_data.getPgc()-1)[i][j].toString() + "|");
            }
            System.out.println();
        }
        //MI SERVE PER VERIFICARE CORRETTA ASSOCIAZIONE PGC CLI E GUI

        cg1DescLabel.setText(view.cgDescriptions[(view.match_data.getCgs()[0]-1)]);
        cg2DescLabel.setText(view.cgDescriptions[(view.match_data.getCgs()[1]-1)]);

        it00.setIcon(new ImageIcon(new ImageIcon("src/main/resources/img/it/Cornici1.1.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        it00.setText("");
        it00.setOpaque(false);
        it00.setBorderPainted(false);

        //lrbLabel.setIcon(new ImageIcon("src/main/resources/img/boards/livingroom.png"));
    }

    public void init() {


        matchFrame.setContentPane(matchPanel);
        matchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        matchFrame.pack();
        matchFrame.setVisible(true);

    }

}
