package it.polimi.ingsw23.gc40.server.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelObservable;
import it.polimi.ingsw23.gc40.server.model.*;
import it.polimi.ingsw23.gc40.server.model.goals.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class GameController extends Observable {
    private final Integer id;
    private final int numPlayers;
    private ArrayList<ItemTile> bag = new ArrayList<>();
    private CommonGoalCard[] commonGoals = new CommonGoalCard[2];
    private ArrayList<Integer> CG1Scores = new ArrayList<>();
    private ArrayList<Integer> CG2Scores = new ArrayList<>();
    private ArrayList<PersonalGoalCard> personalGoals = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private LRB map;
    private boolean ending;
    private Player next;
    private final PublicModelObservable publicModelObservable = new PublicModelObservable(this);

    public GameController(int numPlayers, int id) {
        this.numPlayers = numPlayers;
        this.next = null;
        this.ending = false;
        this.id = id;
        setChanged();
        notifyObservers(this.getId());
        //create a map for n players
        this.map = new LRB(numPlayers);
        //add to the bag all the tiles
        for (int i = 0; i < 22; i++)
            bag.add(new ItemTile(Item.CAT, i));
        for (int i = 0; i < 22; i++)
            bag.add(new ItemTile(Item.BOOK, i));
        for (int i = 0; i < 22; i++)
            bag.add(new ItemTile(Item.GAME, i));
        for (int i = 0; i < 22; i++)
            bag.add(new ItemTile(Item.FRAME, i));
        for (int i = 0; i < 22; i++)
            bag.add(new ItemTile(Item.TROPHY, i));
        for (int i = 0; i < 22; i++)
            bag.add(new ItemTile(Item.PLANT, i));
        //Creating all common goals
        ArrayList<Goal> CGs = new ArrayList<>();
        //create CG1
        CGs.add(new G1_G3(2, 6, 1));
        //create CG3
        CGs.add(new G1_G3(4, 4, 3));
        CGs.add(new G2(2));
        CGs.add(new G4(4));
        //create CG5
        CGs.add(new G5_G8_G9_G10(3, 3, true, 5));
        //create CG8
        CGs.add(new G5_G8_G9_G10(3, 4, false, 8));
        //create CG9
        CGs.add(new G5_G8_G9_G10(6, 2, true, 9));
        //create CG10
        CGs.add(new G5_G8_G9_G10(5, 2, false, 10));
        CGs.add(new G6(6));
        CGs.add(new G7(7));
        CGs.add(new G11(11));
        CGs.add(new G12(12));
        //initialize common goals scores arrays
        if (numPlayers == 4) {
            CG1Scores.add(2);
            CG1Scores.add(4);
            CG1Scores.add(6);
            CG1Scores.add(8);
            CG2Scores.add(2);
            CG2Scores.add(4);
            CG2Scores.add(6);
            CG2Scores.add(8);
        }
        if (numPlayers == 3) {
            CG1Scores.add(4);
            CG1Scores.add(6);
            CG1Scores.add(8);
            CG2Scores.add(4);
            CG2Scores.add(6);
            CG2Scores.add(8);
        }
        if (numPlayers == 2) {
            CG1Scores.add(4);
            CG1Scores.add(8);
            CG2Scores.add(4);
            CG2Scores.add(8);
        }
        //choosing 2 common goals randomly
        int rand = (int) (Math.random() * CGs.size());        commonGoals[0] = new CommonGoalCard(CGs.get(rand));
        CGs.remove(rand);
        rand = (int) (Math.random() * CGs.size());
        commonGoals[1] = new CommonGoalCard(CGs.get(rand));
        CGs.remove(rand);
        setChanged();
        notifyObservers(this.getCommonGoals());
        //import all existing personal goals from json
        Gson gson = new Gson();
        ArrayList<PersonalGoalCard> PGs;
        Type PersonalGoalCardType = new TypeToken<ArrayList<PersonalGoalCard>>() {
        }.getType();
        try {
            PGs = gson.fromJson(new FileReader("personalGoalCards.json"), PersonalGoalCardType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //choosing n personal goal randomly
        for (int i = 0; i < numPlayers; i++) {
            rand = (int) (Math.random() * PGs.size());
            personalGoals.add(PGs.get(rand));
            PGs.remove(rand);
        }
    }

    public Player getNext() {
        return this.next;
    }

    public boolean playable() {
        //Tested
        // if a player has filled his bookshelf and nextPlayer is first player I can not play
        if (ending && next.isFirst()) {
            winner();
            return false;
        }
        //starts when there are all the players
        if (players.size() == numPlayers) return true;
        return false;
    }

    public Player winner() {
        //TO TEST
        int[] scores = new int[players.size()];
        for (int i = 0; i < players.size(); i++) {
            scores[i] = players.get(i).getCGScore() + players.get(i).getPGScore() + players.get(i).getADJScore() + players.get(i).getFirstScore();
        }
        int max = 0;
        for (int i = 0; i < players.size(); i++)
            if (scores[i] >= scores[max]) max = i;
        setChanged();
        notifyObservers(players.get(max).getNickName());
        setChanged();
        notifyObservers(true);
        return players.get(max);
    }

    public ItemTile takeItem() {
        //TO TEST
        int rand = (int) (Math.random() * bag.size());
        ItemTile tmp = bag.get(rand);
        bag.remove(rand);
        return tmp;
    }

    public void setPlayersOrder() {
        ArrayList<Player> PLtemp = new ArrayList<>();
        PLtemp.addAll(players);
        int rand;

        for (int i = 0; i < players.size(); i++) {
            rand = (int) (Math.random() * PLtemp.size());
            players.set(i, PLtemp.get(rand));
            PLtemp.remove(rand);
            players.get(i).setPersonalGoal(personalGoals.get(0));
            personalGoals.remove(0);
        }
        players.get(0).setFirst(true);
        this.next = players.get(0);
    }

    //This method returns true to server if the match is ready to start (has all players)
    public boolean addPlayer(Player p) {
        players.add(p);

        if (players.size() == numPlayers) {
            setPlayersOrder();
            return true;
        }
        return false;
    }

    public void refill() {
        //TESTED
        ItemTile[][] map = this.map.getMap();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (map[i][j].getItem() != Item.NOTREACHABLE) {
                    if (map[i][j].getItem() == Item.EMPTY) map[i][j] = takeItem();
                }
            }
        }
        this.map.setMap(map);
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public CommonGoalCard[] getCommonGoals() {
        return commonGoals;
    }

    public void setCommonGoals(CommonGoalCard[] commonGoals) {
        this.commonGoals = commonGoals;
        setChanged();
        notifyObservers(this.getCommonGoals());
    }

    public void setNext(Player next) {
        this.next = next;
    }

    public ItemTile[] chooseTiles(int x, int y, int quantity, char direction) {
        //tested

        ItemTile[] chosen = new ItemTile[quantity];


        switch (direction) {
            case 'U':
                for (int i = 0; i < quantity; i++)
                    if ((x - quantity) < 0)
                        return null;
                    else {
                        if (this.map.isTakeable(x - i, y))
                            chosen[i] = this.map.getTileInMap(x - i, y);
                        else
                            return null;
                    }
                for (int i = 0; i < quantity; i++) {
                    map.setTileInMap(new ItemTile(Item.EMPTY), x - i, y);
                }
                return chosen;
            case 'D':
                for (int i = 0; i < quantity; i++)
                    if ((x + quantity) > 8) {
                        return null;
                    } else {
                        if (this.map.isTakeable(x + i, y))
                            chosen[i] = this.map.getTileInMap(x + i, y);
                        else {
                            return null;
                        }

                    }
                for (int i = 0; i < quantity; i++) {
                    map.setTileInMap(new ItemTile(Item.EMPTY), x + i, y);
                }
                return chosen;
            case 'L':
                for (int i = 0; i < quantity; i++)
                    if ((y - quantity) < 0)
                        return null;
                    else {
                        if (this.map.isTakeable(x, y - i))
                            chosen[i] = this.map.getTileInMap(x, y - i);
                        else
                            return null;
                    }
                for (int i = 0; i < quantity; i++) {
                    map.setTileInMap(new ItemTile(Item.EMPTY), x, y - i);
                }
                return chosen;
            case 'R':
                for (int i = 0; i < quantity; i++)
                    if ((y + quantity) > 8)
                        return null;
                    else {
                        if (this.map.isTakeable(x, y + i))
                            chosen[i] = this.map.getTileInMap(x, y + i);
                        else
                            return null;
                    }
                for (int i = 0; i < quantity; i++) {
                    map.setTileInMap(new ItemTile(Item.EMPTY), x, y + i);
                }
                return chosen;
            default:
                return null;
        }

    }

    public LRB getMap() {
        return map;
    }

    public void round(Player p) {
        if (map.needRefill()) {
            refill();
            setChanged();
            notifyObservers(this.getMap().getMap());
        }
        int placeables = p.getBookshelf().placeables();
        ItemTile[] handTiles;

        //utente sceglie tile

        Scanner sc = new Scanner(System.in);
        map.print();

        int y, x, quantity;
        char direction;
        do {
            System.out.println("Per selezionare le Tiles inserisci le coordinate della prima Tile nella riga che vuoi prendere:");
            System.out.print("X: ");
            x = sc.nextInt();
            System.out.print("Y: ");
            sc.reset();
            y = sc.nextInt();
            System.out.print("Quante Tiles vuoi prendere? ");
            sc.reset();


            do {
                quantity = sc.nextInt();
                if (quantity > placeables)
                    System.out.print("Non hai spazio sufficiente nella tua bookshelf per " + quantity + " tiles!\nRiprova, quante Tiles vuoi prendere? ");
            } while (quantity > placeables);
            handTiles = new ItemTile[quantity];

            System.out.println("Verso dove vuoi prendere?   (U per Up - D per Down - L per Left - R per Right)");
            sc.reset();

            direction = sc.next().charAt(0);
            handTiles = this.chooseTiles(x, y, quantity, direction);
            if (handTiles == null) {
                System.out.println("Hai selezionato una o più Tiles non prendibili, riprova:");
                map.print();
            }

        } while (handTiles == null);
        for (int i = 0; i < quantity; i++) {

        }

        //mostro la bookshelf e chiedo colonna in cui vuole inserire
        int column;
        p.getBookshelf().print();
        boolean retry;
        do {
            retry = false;
            System.out.println("In quale colonna vuoi inserire le tiles?");
            sc.reset();
            column = sc.nextInt();
            if (column < 0 || column > 4) {
                retry = true;
                System.out.println("Hai scelto una colonna inesistente");
            }
            if (p.getBookshelf().getAvailableSlots(column) < handTiles.length) {
                System.out.println("La colonna non ha sufficiente spazio, riprova:");
                retry = true;
            }
        } while (retry);

        //mostra tiles in mano
        System.out.println("Hai selezionato i seguenti Tiles:");
        for (int i = 0; i < handTiles.length; i++) {
            System.out.print(i + ": " + handTiles[i].toString() + "|");
        }

        //inserisci le tiles che hai in mano
        for (int i = 0; i < handTiles.length; i++) {
            System.out.println("Scegli una tile da inserire:");
            int selected;
            do {
                retry = false;
                selected = sc.nextInt();
                if (selected >= handTiles.length || selected < 0) {
                    System.out.println("Riprova:");
                    retry = true;
                } else {
                    if (handTiles[selected] == null) {
                        System.out.println("Riprova:");
                        retry = true;
                    }
                }
            } while (retry);
            p.getBookshelf().insertIT(handTiles[selected], column);
            handTiles[selected] = null;
            sc.reset();
        }

        //check if there is space in the player bookshelf
        if (p.getBookshelf().getFull()) {
            //p.getBookshelf().setFull(true);
            p.setFirstScore(1);
            this.ending = true;
        }

        //refresh scores
        p.setADJScore(p.getBookshelf().ADJScore());
        if (commonGoals[0].getGoal().instanceGoal(p))
            p.setCGScore(p.getCGScore() + CG1Scores.get(CG1Scores.size()) - 1);
        if (commonGoals[1].getGoal().instanceGoal(p))
            p.setCGScore(p.getCGScore() + CG2Scores.get(CG2Scores.size()) - 1);
        p.getPersonalGoal().checkScore(p);

        //set turn to next player
        int num;
        if (players.indexOf(p) == players.size() - 1)
            num = 0;
        else
            num = players.indexOf(p) + 1;
        next = players.get(num);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers();
    }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }

    public void print(String s) {
        System.out.println(s);
    }

    public boolean isEnding() {
        return ending;
    }

    public int getId() {
        return id;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
}


