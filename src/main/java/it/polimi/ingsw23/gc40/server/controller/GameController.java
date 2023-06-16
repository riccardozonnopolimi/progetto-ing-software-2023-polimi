package it.polimi.ingsw23.gc40.server.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelObserver;
import it.polimi.ingsw23.gc40.server.model.*;
import it.polimi.ingsw23.gc40.server.model.goals.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class GameController extends Observable {
    private final Integer id;
    private final int numPlayers;
    public int activePlayers;
    private ArrayList<ItemTile> bag = new ArrayList<>();
    private CommonGoalCard[] commonGoals = new CommonGoalCard[2];
    private ArrayList<Integer> CG1Scores = new ArrayList<>();
    private ArrayList<Integer> CG2Scores = new ArrayList<>();
    private ArrayList<PersonalGoalCard> personalGoals = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private LRB map;
    private boolean ending;
    private boolean playable;
    private short turnNumber;

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
        setChanged();
        notifyObservers(playable);
    }

    private Player next;

    public ArrayList<ItemTile> getBag() {
        return bag;
    }

    public void setBag(ArrayList<ItemTile> bag) {
        this.bag = bag;
    }

    public ArrayList<Integer> getCG1Scores() {
        return CG1Scores;
    }

    public void setCG1Scores(ArrayList<Integer> CG1Scores) {
        this.CG1Scores = CG1Scores;
    }

    public ArrayList<Integer> getCG2Scores() {
        return CG2Scores;
    }

    public void setCG2Scores(ArrayList<Integer> CG2Scores) {
        this.CG2Scores = CG2Scores;
    }

    public ArrayList<PersonalGoalCard> getPersonalGoals() {
        return personalGoals;
    }

    public void setPersonalGoals(ArrayList<PersonalGoalCard> personalGoals) {
        this.personalGoals = personalGoals;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setMap(LRB map) {
        this.map = map;
    }

    public PublicModelObserver getObserver() {
        return observer;
    }

    private final PublicModelObserver observer = new PublicModelObserver(this);


    public GameController(int numPlayers, int id) {
        if (numPlayers > 4 || numPlayers < 2) numPlayers = 2;
        this.numPlayers = numPlayers;
        this.next = null;
        this.ending = false;
        this.id = id;
        this.turnNumber = 0;
        this.activePlayers = 0;


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
        int rand = (int) (Math.random() * CGs.size());
        commonGoals[0] = new CommonGoalCard(CGs.get(rand));
        CGs.remove(rand);
        rand = (int) (Math.random() * CGs.size());
        commonGoals[1] = new CommonGoalCard(CGs.get(rand));
        CGs.remove(rand);

        //setting CG n.8 to debug
        //commonGoals[0] = new CommonGoalCard(new G5_G8_G9_G10(3, 4, false, 8));

        int[] cgsids = new int[]{commonGoals[0].getGoal().getId(), commonGoals[1].getGoal().getId()};


        setChanged();
        notifyObservers(id);

        setChanged();
        notifyObservers(cgsids);

        setChanged();
        notifyObservers(map.getMap());


        //import all existing personal goals from json
        Gson gson = new Gson();

        ArrayList<ItemTile[][]> goalsFromFile = new ArrayList<>();

        Type personalGoalsType = new TypeToken<ArrayList<ItemTile[][]>>() {
        }.getType();
        try {
            goalsFromFile = gson.fromJson(new FileReader("personalGoals.json"), personalGoalsType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        //choosing n personal goal randomly
        for (int i = 0; i < numPlayers; i++) {

            rand = (int) (Math.random() * goalsFromFile.size());

            personalGoals.add(new PersonalGoalCard(goalsFromFile.get(rand), rand + 1));
            goalsFromFile.remove(rand);
        }
    }

    public Player getNext() {
        return this.next;
    }

  /*  public boolean playable() {
        //Tested
        //starts when there are all the players
        if (players.size() == numPlayers){
            setPlayable(true);
            return true;
        }


        // if a player has filled his bookshelf and nextPlayer is first player I can not play
        if (next != null) if (ending && next.isFirst()) {
            winner();
            return false;
        }
        return false;
    }*/

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
        players.get(0).setStartedFirst(true);
        setNext(players.get(0));
    }

    //This method returns true to server if the match is ready to start (has all players)
    public boolean addPlayer(Player p) {
        players.add(p);
        activePlayers++;

        if (players.size() == numPlayers) {
            setPlayersOrder();
            setChanged();
            notifyObservers(this.getId());
            refill();
            setPlayable(true);

            return true;
        }
        return false;
    }

    public void refill() {
        //TESTED
        ItemTile[][] temp_map = this.map.getMap();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (temp_map[i][j].getItem() != Item.NOTREACHABLE) {
                    if (temp_map[i][j].getItem() == Item.EMPTY) temp_map[i][j] = takeItem();
                }
            }
        }
        this.map.setMap(temp_map);
        map.print();
        setChanged();
        notifyObservers(this.map.getMap());
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public CommonGoalCard[] getCommonGoals() {
        return commonGoals;
    }

    public void setCommonGoals(CommonGoalCard[] commonGoals) {
        this.commonGoals = commonGoals;
    }

    public void setNext(Player next) {
        this.next = next;
        this.turnNumber++;
        setChanged();
        notifyObservers(turnNumber);
        setChanged();
        notifyObservers(next.getNickName());
    }


    public ItemTile[] chooseTiles(int x, int y, int quantity, char direction) {
        //tested
        try {
            if (quantity < 1 || quantity > 3) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            return null;

        }
        ItemTile[] chosen = new ItemTile[quantity];
        for (int i = 0; i < quantity; i++) {
            chosen[i] = new ItemTile(Item.EMPTY);
        }

        switch (direction) {
            case 'U':
                for (int i = 0; i < quantity; i++)
                    if ((x - quantity + 1) < 0) return chosen;
                    else {
                        if (this.map.isTakeable(x - i, y))
                            chosen[i] = this.map.getTileInMap(x - i, y);
                        else return chosen;
                    }
                for (int i = 0; i < quantity; i++) {
                    map.setTileInMap(new ItemTile(Item.EMPTY), x - i, y);
                }
                return chosen;
            case 'D':
                for (int i = 0; i < quantity; i++)
                    if ((x + quantity - 1) > 8) {
                        return chosen;
                    } else {
                        if (this.map.isTakeable(x + i, y))
                            chosen[i] = this.map.getTileInMap(x + i, y);
                        else {
                            return chosen;
                        }

                    }
                for (int i = 0; i < quantity; i++) {
                    map.setTileInMap(new ItemTile(Item.EMPTY), x + i, y);
                }
                return chosen;
            case 'L':
                for (int i = 0; i < quantity; i++)
                    if ((y - quantity + 1) < 0)
                        return chosen;
                    else {
                        if (this.map.isTakeable(x, y - i))
                            chosen[i] = this.map.getTileInMap(x, y - i);
                        else
                            return chosen;
                    }
                for (int i = 0; i < quantity; i++) {
                    map.setTileInMap(new ItemTile(Item.EMPTY), x, y - i);
                }
                return chosen;
            case 'R':
                for (int i = 0; i < quantity; i++)
                    if ((y + quantity - 1) > 8) return chosen;
                    else {
                        if (this.map.isTakeable(x, y + i))
                            chosen[i] = this.map.getTileInMap(x, y + i);
                        else
                            return chosen;
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

    public boolean isEnding() {
        return ending;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public PublicModelObserver getPublicModelObserver() {
        return observer;
    }

    public void setEnding(boolean b) {
        this.ending = b;
    }

    public void checkScores(Player p) {
        p.setADJScore(p.getBookshelf().ADJScore());
        System.out.println("punteggio adiacenze: " + p.getADJScore() + " user: " + p.getNickName());
        int cgscore = p.getCGScore();
        System.out.println("cgscore attuale: " + cgscore);
        if (!p.getCG1Reached() && this.commonGoals[0].getGoal().instanceGoal(p)) {
            cgscore = cgscore + this.CG1Scores.get(this.CG1Scores.size() - 1);
            this.CG1Scores.remove(this.CG1Scores.size() - 1);
            System.out.println("CG1 reached by " + p.getNickName());
            p.setCG1Reached(true);
        }
        if (!p.getCG2Reached() && this.getCommonGoals()[1].getGoal().instanceGoal(p)) {
            cgscore = cgscore + p.getCGScore() + this.CG2Scores.get(this.CG2Scores.size() - 1);
            this.CG2Scores.remove(this.CG2Scores.size() - 1);
            System.out.println("CG2 reached by " + p.getNickName());
            p.setCG2Reached(true);
        }

        p.setCGScore(cgscore);
        p.getPersonalGoal().checkScore(p);
    }

    public int getActive() {
        return activePlayers;
    }

    public void setActive(int i) {
        this.activePlayers = i;
    }
}