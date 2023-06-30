package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.server.model.Item;
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
    private JButton takeTilesButton;
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
    private JButton bs00;
    private JButton bs01;
    private JButton bs10;
    private JButton bs02;
    private JButton bs03;
    private JButton bs04;
    private JButton bs20;
    private JButton bs30;
    private JButton bs40;
    private JButton bs50;
    private JButton bs11;
    private JButton bs12;
    private JButton bs13;
    private JButton bs14;
    private JButton bs21;
    private JButton bs22;
    private JButton bs23;
    private JButton bs24;
    private JButton bs31;
    private JButton bs32;
    private JButton bs33;
    private JButton bs34;
    private JButton bs41;
    private JButton bs42;
    private JButton bs43;
    private JButton bs44;
    private JButton bs51;
    private JButton bs52;
    private JButton bs53;
    private JButton bs54;
    private JRadioButton col0;
    private JRadioButton col1;
    private JRadioButton col2;
    private JRadioButton col3;
    private JRadioButton col4;
    private JButton selectColumnButton;
    private int tilequantity;
    private char tiledirection;
    private int tileY;
    private int tileX;
    private int[] coordX;
    private int[] coordY;
    private ItemTile[] handtiles;
    private View view;
    private boolean play;
    private ButtonGroup columnRadio = new ButtonGroup();
    private int column;


    /**
     * GUI_Match constructor create the graphic view for the match
     *
     * @param view
     */
    public GUI_Match(View view) {
        this.view = view;
        this.tilequantity = 0;
        this.coordX = new int[3];
        this.coordY = new int[3];
        this.handtiles = null;
        this.tiledirection = ' ';
        this.tileX = -1;
        this.tileY = -1;
        this.play = true;

        matchFrame = new JFrame("MyShelfie");
        createUIComponents();
    }

    /**
     * Checking if the tiles have been taken correctly.
     *
     * @param cordX
     * @param cordY
     * @return true if the choice is correct
     */
    public boolean checkSameRowOrColumn(int[] cordX, int[] cordY) {

        // Controllo se i due array hanno la stessa lunghezza e se la quantità è valida
        if (cordX.length != cordY.length || tilequantity <= 0 || tilequantity > cordX.length) {
            return false;
        }

        // Controllo se le tessere sono sulla stessa riga
        boolean sameRow = true;
        int startRow = cordY[0];

        for (int i = 0; i < tilequantity; i++) {
            if (cordY[i] != startRow) {
                sameRow = false;
                break;
            }
        }

        // Controllo se le tessere sono sulla stessa colonna
        boolean sameColumn = true;
        int startColumn = cordX[0];

        for (int i = 0; i < tilequantity; i++) {
            if (cordX[i] != startColumn) {
                sameColumn = false;
                break;
            }
        }

        //inverto ma perchè? todo
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


    /**
     * setting string with the type of the item tile
     *
     * @param it item tile
     * @return a string with the type of item tile
     */
    private String getTileUri(ItemTile it) {
        String uri = "";
        if (it != null) {
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
        } else return "Empty";
        uri += "1." + (it.getNum() + 1);

        return uri;
    }

    /**
     * setting LRB with clickable tile, or unclickable tile if tile type is NR or Empty
     *
     * @param map
     * @param enabled
     */
    public void setLRB(ItemTile[][] map, boolean enabled) {
        takeTilesButton.setEnabled(enabled);

        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                String elementName = "it" + i + j;
                try {
                    Field field = GUI_Match.class.getDeclaredField(elementName);
                    JButton button = (JButton) field.get(this);

                    if (!getTileUri(map[i][j]).equals("NR") && !getTileUri(map[i][j]).equals("Empty")) {
                        button.setEnabled(enabled);
                        button.setIcon(new ImageIcon(new ImageIcon("src/main/resources/img/it/" + getTileUri(view.match_data.getLrb()[i][j]) + ".png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                    } else {
                        button.setEnabled(false);
                        button.setIcon(null);
                    }


                } catch (NoSuchFieldException | IllegalAccessException er) {
                    er.printStackTrace();
                }
            }
        }
    }

    //todo javadoc
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
        boolean enabled = false;
        if (view.match_data.getCurrent().equals(view.nickname)) enabled = true;
        setLRB(view.match_data.getLrb(), enabled);

        cg1DescLabel.setText("<html><body style='width: 200px;'>" + view.cgDescriptions[(view.match_data.getCgs()[0] - 1)] + "</body></html>");
        cg2DescLabel.setText("<html><body style='width: 200px;'>" + view.cgDescriptions[(view.match_data.getCgs()[1] - 1)] + "</body></html>");

        columnRadio.add(col0);
        columnRadio.add(col1);
        columnRadio.add(col2);
        columnRadio.add(col3);
        columnRadio.add(col4);

        col0.addActionListener(e -> {
            column = 0;
            activateHtButtons();
        });

        col1.addActionListener(e -> {

            column = 1;
            activateHtButtons();

        });

        col2.addActionListener(e -> {
            column = 2;
            activateHtButtons();

        });

        col3.addActionListener(e -> {
            column = 3;
            activateHtButtons();
        });

        col4.addActionListener(e -> {
            column = 4;
            activateHtButtons();
        });


        //initialize handtiles button
        for (int k = 1; k < 4; k++) {
            String elementName = "ht" + k;
            try {
                Field field = GUI_Match.class.getDeclaredField(elementName);
                JButton button = (JButton) field.get(this);

                button.setText("");
                button.setOpaque(false);
                button.setBorderPainted(false);
                int finalK = k;


                button.setPreferredSize(new Dimension(45, 45));

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        //initialize taketiles
        takeTilesButton.setText("Scegli tiles");
        takeTilesButton.addActionListener(e -> {

            if (checkSameRowOrColumn(coordX, coordY)) {
                try {
                    handtiles = view.con.chooseTiles(view.nickname, view.matchId, tileX, tileY, tilequantity, tiledirection);
                } catch (RemoteException ex) {
                    popup("catch");
                    throw new RuntimeException(ex);
                }
            } else {
                setLRB(view.match_data.getLrb(), true);
                popup("Non hai scelto correttamente le tiles, riprova.");
            }

            tileY = -1;
            tileX = -1;
            tiledirection = ' ';
            tilequantity = 0;


            boolean nullo = false;
            if (handtiles != null)
                //todo test
                for (int i = 0; i < handtiles.length; i++)
                    if (handtiles[i]==null || handtiles[i].getItem().equals(Item.EMPTY)) {
                        nullo = true;
                        i = handtiles.length;
                    }

            if (nullo)
                handtiles = null;


            if (handtiles != null) {
                setLRB(view.match_data.getLrb(), false);
                popup("Scegli una colonna in cui posizionare le files nella tua bookshelf.");
                takeTilesButton.setEnabled(false);


                for (int k = 0; k < handtiles.length; k++) {
                    String elementName = "ht" + (k + 1);
                    try {
                        Field field = GUI_Match.class.getDeclaredField(elementName);
                        JButton button = (JButton) field.get(this);

                        button.setIcon(new ImageIcon(new ImageIcon("src/main/resources/img/it/" + getTileUri(handtiles[k]) + ".png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));

                    } catch (NoSuchFieldException | IllegalAccessException er) {
                        er.printStackTrace();
                    }
                }

            } else {
                setLRB(view.match_data.getLrb(), true);
                popup("C'è stato un problema nel prelevare le tiles, riprova");
            }


        });

        //dopo che scelgo la colonna

       /*
        for k handtiles etc actionlistener to inserttile
        button.addActionListener(e -> {
            if(handtiles!=null)
                if (handtiles[finalK -1]!=null)
                {
                    //todo insert tile
                }
        });*/

        //initialize itemtiles buttons in lrb
        // Ripetizione per tutti gli elementi da it00 a it88
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                String elementName = "it" + i + j;
                try {
                    Field field = GUI_Match.class.getDeclaredField(elementName);
                    JButton button = (JButton) field.get(this);

                    int finalI = i;
                    int finalJ = j;
                    button.setText("");
                    button.setOpaque(false);
                    button.setBorderPainted(false);
                    button.addActionListener(e -> {
                        if (tilequantity > 2) {
                            JOptionPane.showMessageDialog(null, "Select maximum 3 tiles", "Error", 1);
                        } else {
                            button.setEnabled(false);
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


        //initizlize  tiles buttons in bookshelf
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 4; j++) {
                String elementName = "bs" + i + j;
                try {
                    Field field = GUI_Match.class.getDeclaredField(elementName);
                    JButton button = (JButton) field.get(this);

                    int finalI = i;
                    int finalJ = j;
                    button.setText("");
                    button.setOpaque(false);
                    button.setBorderPainted(false);


                    button.setPreferredSize(new Dimension(45, 45));

                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    //todo javadoc
    private void activateHtButtons() {
        try {

            if (view.con.getAvailableSlots(view.nickname, column) < handtiles.length) {
                popup("Column has not enough space, retry");
            } else {


                popup("Select the tiles one by one to insert it in the column");
                for (int k = 0; k < handtiles.length; k++) {
                    String elementName = "ht" + (k + 1);
                    try {
                        Field field = GUI_Match.class.getDeclaredField(elementName);
                        JButton button = (JButton) field.get(this);

                        int finalK = k;
                        button.addActionListener(e1 -> {
                            try {
                                if (view.con.insertTile(view.nickname, handtiles[finalK], column)) {
                                    handtiles[finalK] = null;
                                    button.setIcon(null);
                                    button.setEnabled(false);
                                } else {
                                    popup("there was a problem inserting the tile in the column, try to restart the GUI");
                                }
                            } catch (RemoteException ex) {
                                throw new RuntimeException(ex);
                            }

                            int nulli = 0;
                            for (int i = 0; i < handtiles.length; i++) {
                                if (handtiles[i] == null) {
                                    nulli++;
                                }
                            }
                            if (nulli == handtiles.length) {

                                try {
                                    view.con.nextRound(view.nickname, view.matchId);


                                } catch (RemoteException e) {
                                    throw new RuntimeException(e);
                                }


                                deselectColumn();

                                resetHTButtons();

                                while (view.turnNumber == view.match_data.getTurnNumber()) {
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }


                                if (view.match_data.getWinner() == null) {
                                    //waiting for change of current player
                                    while ((view.turnNumber == view.match_data.getTurnNumber() - 1) && (view.match_data.getCurrent().equals(view.nickname))) {
                                        try {
                                            Thread.sleep(500);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }

                            }

                        });

                    } catch (NoSuchFieldException | IllegalAccessException er) {
                        er.printStackTrace();
                    }


                }


            }


        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void resetHTButtons() {
        ht1.setEnabled(true);
        ht2.setEnabled(true);
        ht3.setEnabled(true);
    }

    private void deselectColumn() {
        col0.setSelected(false);
        col1.setSelected(false);
        col2.setSelected(false);
        col3.setSelected(false);
        col4.setSelected(false);

    }

    /**
     * showing message on screen
     *
     * @param msg message to show
     */
    private void popup(String msg) {
        JOptionPane.showMessageDialog(null, msg, "todo", 1);

    }

    /**
     * starts GUI
     */
    public void init() {


        matchFrame.setContentPane(matchPanel);
        matchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        matchFrame.pack();
        matchFrame.setVisible(true);

    }

    //todo check this javadoc

    /**
     * updating bookshelf on screen
     *
     * @param bookshelf
     */
    public void setBookShelf(ItemTile[][] bookshelf) {

        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 4; j++) {
                String elementName = "bs" + i + j;
                try {
                    Field field = GUI_Match.class.getDeclaredField(elementName);
                    JButton button = (JButton) field.get(this);

                    button.setIcon(new ImageIcon(new ImageIcon("src/main/resources/img/it/" + getTileUri(bookshelf[i][j]) + ".png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));

                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
