package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.server.model.ItemTile;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.rmi.RemoteException;


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
    private JButton makeMoveBTN;
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
    private JButton ht2;
    private JButton ht3;
    private JButton ht1;
    private int tilequantity;
    private char tiledirection;
    private int tileY;
    private int tileX;
    private int[] coordX;
    private int[] coordY;
    private ItemTile[] handtiles;
    private View view;


    public GUI_Match(View view) {
        this.view = view;
        this.tilequantity = 0;
        this.coordX = new int[3];
        this.coordY = new int[3];
        this.handtiles = null;
        this.tiledirection = ' ';
        this.tileX = -1;
        this.tileY = -1;

        matchFrame = new JFrame("MyShelfie match");
        createUIComponents();
    }

    public boolean checkSameRowOrColumn(int[] cordX, int[] cordY) {

        // Controllo se i due array hanno la stessa lunghezza e se la quantità è valida
        if (cordX.length != cordY.length || tilequantity <= 0 || tilequantity > cordX.length) {
            return false;
        }

        // Controllo se le tessere sono sulla stessa riga
        boolean sameRow = true;
        int startRow = cordY[0];

        for (int i = 1; i < tilequantity; i++) {
            if (cordY[i] != startRow) {
                sameRow = false;
                break;
            }
        }

        // Controllo se le tessere sono sulla stessa colonna
        boolean sameColumn = true;
        int startColumn = cordX[0];

        for (int i = 1; i < tilequantity; i++) {
            if (cordX[i] != startColumn) {
                sameColumn = false;
                break;
            }
        }

        //inverto ma perchè?
        tileY = cordX[0];
        tileX = cordY[0];

        if (sameRow) {
            int deltaX = cordX[1] - cordX[0];
            if (deltaX > 0) {
                tiledirection = 'R';
            } else if (deltaX < 0) {
                tiledirection = 'L';
            }
            return true;
        } else if (sameColumn) {
            int deltaY = cordY[1] - cordY[0];
            if (deltaY > 0) {
                tiledirection = 'D';
            } else if (deltaY < 0) {
                tiledirection = 'U';
            }
            return true;
        }
        return false;
    }

    private String getTileUri(ItemTile it) {
        String uri = "";
        switch (it.getItem()) {
            case CAT -> {
                uri += "Gatti";
            }
            case BOOK -> {
                uri += "Libri";
            }
            case GAME -> {
                uri += "Giochi";
            }
            case FRAME -> {
                uri += "Cornici";
            }
            case TROPHY -> {
                uri += "Trofei";
            }
            case PLANT -> {
                uri += "Piante";
            }
            case EMPTY -> {
                return "Empty";
            }
            case NOTREACHABLE -> {
                return "NR";
            }
        }
        uri += "1." + (it.getNum() + 1);

        return uri;
    }

    private void setLRB(ItemTile[][] map, boolean enabled) {

        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                String elementName = "it" + i + j;
                try {
                    Field field = GUI_Match.class.getDeclaredField(elementName);
                    JButton button = (JButton) field.get(this);
                    if (!getTileUri(map[i][j]).equals("NR") && !getTileUri(map[i][j]).equals("Empty")) {
                        button.setEnabled(enabled);
                        tilequantity = 0;
                    }
                } catch (NoSuchFieldException | IllegalAccessException er) {
                    er.printStackTrace();
                }
            }
        }
    }

    private void createUIComponents() {
        // dentro la setIcon() > new ImageIcon(new ImageIcon(" PATH ").getImage().getScaledInstance(200,500, Image.SCALE_SMOOTH))

        goalPanel.setPreferredSize(new Dimension(250, 1000));

        //showing common goal cards using file path
        cg2Label.setIcon(new ImageIcon(new ImageIcon("src/main/resources/img/cgs/" + (view.match_data.getCgs()[1]) + ".jpg").getImage().getScaledInstance(200, 120, Image.SCALE_SMOOTH)));
        cg1Label.setIcon(new ImageIcon(new ImageIcon("src/main/resources/img/cgs/" + (view.match_data.getCgs()[0]) + ".jpg").getImage().getScaledInstance(200, 120, Image.SCALE_SMOOTH)));
        pgLabel.setIcon(new ImageIcon(new ImageIcon("src/main/resources/img/pgc/" + view.player_data.getPgc() + ".png").getImage().getScaledInstance(200, 400, Image.SCALE_SMOOTH)));

        cg2Label.setText("");
        cg1Label.setText("");
        pgLabel.setText("");

        cg1DescLabel.setText("<html><body style='width: 200px;'>" + view.cgDescriptions[(view.match_data.getCgs()[0] - 1)] + "</body></html>");
        cg2DescLabel.setText("<html><body style='width: 200px;'>" + view.cgDescriptions[(view.match_data.getCgs()[1] - 1)] + "</body></html>");


        makeMoveBTN.setText("Scegli tiles");
        makeMoveBTN.addActionListener(e -> {
            if (handtiles != null) {

                //todo popup bro hai già delle tiles

            } else {
                if (checkSameRowOrColumn(coordX, coordY)) {
                    try {
                        handtiles = view.con.chooseTiles(view.nickname, view.matchId, tileX, tileY, tilequantity, tiledirection);
                    } catch (RemoteException ex) {
                        popup("catch");
                        throw new RuntimeException(ex);
                    }
                } else {
                    popup("else");
                }
                //handle if handtiles ==null
                if (handtiles != null) {
                    popup("handtiles non nullo");
                    setLRB(view.match_data.getLrb(), false);
                } else
                    popup("handtiles nullo");


                for (int i = 0; i <= 8; i++) {
                    for (int j = 0; j <= 8; j++) {
                        String elementName = "it" + i + j;
                        try {
                            Field field = GUI_Match.class.getDeclaredField(elementName);
                            JButton button = (JButton) field.get(this);
                            if (!getTileUri(view.match_data.getLrb()[i][j]).equals("NR") && !getTileUri(view.match_data.getLrb()[i][j]).equals("Empty")) {
                                button.setEnabled(true);
                                tilequantity = 0;
                            }
                        } catch (NoSuchFieldException | IllegalAccessException er) {
                            er.printStackTrace();
                        }
                    }
                }
            }
        });

// Ripetizione per tutti gli elementi da it00 a it88
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                String elementName = "it" + i + j;
                try {
                    Field field = GUI_Match.class.getDeclaredField(elementName);
                    JButton button = (JButton) field.get(this);
                    if (!getTileUri(view.match_data.getLrb()[i][j]).equals("NR"))
                        button.setIcon(new ImageIcon(new ImageIcon("src/main/resources/img/it/" + getTileUri(view.match_data.getLrb()[i][j]) + ".png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                    else
                        button.setEnabled(false);
                    button.setText("");
                    button.setOpaque(false);
                    button.setBorderPainted(false);
                    int finalI = i;
                    int finalJ = j;
                    button.addActionListener(e -> {
                        if (tilequantity > 2) {
                            JOptionPane.showMessageDialog(null, "Select maximum 3 tiles", "Error", 1);
                        } else {
                            button.setEnabled(false);
                            button.setBorderPainted(true);

                            coordX[tilequantity] = finalJ;
                            coordY[tilequantity] = finalI;
                            tilequantity++;
                        }
                    });

                    button.setPreferredSize(new Dimension(45, 45));

                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        //lrbLabel.setIcon(new ImageIcon("src/main/resources/img/boards/livingroom.png"));
    }

    private void popup(String msg) {
        JOptionPane.showMessageDialog(null, msg, "todo", 1);

    }

    public void init() {


        matchFrame.setContentPane(matchPanel);
        matchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        matchFrame.pack();
        matchFrame.setVisible(true);

    }

}
