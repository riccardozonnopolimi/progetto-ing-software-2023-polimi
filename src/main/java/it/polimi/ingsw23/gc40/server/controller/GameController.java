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
    private short moveNumber;
    private Player wasnext;
    private Player next;
    private final PublicModelObserver observer = new PublicModelObserver(this);
    private short[] points;
    private int pndf;

    public short[] getPoints() {
        return points;
    }

    public void setPoints(short[] points) {
        this.points = points;
    }

    public int getPndf() {
        return pndf;
    }

    public void setPndf(int pndf) {
        this.pndf = pndf;
    }

    /**
     * @return true if the match can start or restart in case remains just one player in the match
     */
    public boolean isPlayable() {
        return playable;
    }

    /**
     * @param playable set to true if if the match can start or restart
     *  false by default, it could be set to false if there is just one player remained in an active match
     */
    public void setPlayable(boolean playable) {
        this.playable = playable;
        setChanged();
        notifyObservers(playable);
    }

        //todo cancellare tutti i metodi non utilizzati alla fine
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

    /**
     * @return Public Model Observer
     */
    public PublicModelObserver getObserver() {
        return observer;
    }

    /**
     * GameController constructor. Initializes a lrb with empty tiles,creates a bag with 22 item tile for each tile
     * creates common goal cards and sets two of them randomly, sets scores based on numPlayers
     * @param numPlayers in the match
     * @param id matchid
     */
    public GameController(int numPlayers, int id) {
        if (numPlayers > 4 || numPlayers < 2) numPlayers = 2;
        this.numPlayers = numPlayers;
        this.next = null;
        this.ending = false;
        this.id = id;
        this.moveNumber = 0;
        this.activePlayers = 0;
        this.points = new short[numPlayers];
        this.pndf = 0;

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
            goalsFromFile = gson.fromJson(new FileReader("src/main/resources/personalGoals.json"), personalGoalsType);
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

    /**
     * @return the next player who plays the round
     */
    public Player getNext() {
        return this.next;
    }

    /**
     * @return the winner of the match
     */
    public void winner() {
        //TO TEST
        int[] scores = new int[players.size()];
        //calculating total score of each player
        for (int i = 0; i < players.size(); i++) {
            scores[i] = players.get(i).totScore();
        }
        int max = 0;
        //calculating the maximum score
        for (int i = 0; i < players.size(); i++) {
            if (scores[i] >= scores[max]) {
                max = i;
            }
        }
        moveNumber++;
        setChanged();
        notifyObservers(moveNumber);
        setChanged();
        notifyObservers(players.get(max).getNickName());
        setChanged();
        notifyObservers(getPoints());
    }

    /**
     * @return a randomly chosen item tile taken from the bag
     */
    public ItemTile takeItem() {
        //TO TEST
        int rand = (int) (Math.random() * bag.size());
        ItemTile tmp = bag.get(rand);
        bag.remove(rand);
        return tmp;
    }

    /**
     * set the order of the turn in a match when it starts
     */
    public void setPlayersOrder() {
        //initializing the array with all players
        ArrayList<Player> PLtemp = new ArrayList<>();
        PLtemp.addAll(players);
        int rand;
        //choosing one randomly, then sets it at the head of the list and remove it from the temp list
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

    /**
     * @param p is added to the players arrayList
     * @return true if the match is ready to start (has all players)
     */
    public boolean addPlayer(Player p) {
        players.add(p);
        activePlayers++;

        if (players.size() == numPlayers) {
            setPlayersOrder();
            setChanged();
            notifyObservers(this.getId());
            refill();
            setPlayable(true);
            String[] playersNick = new String[numPlayers];
            for (int i = 0; i<players.size();i++ ){
                playersNick[i] = players.get(i).getNickName();
            }
            setChanged();
            notifyObservers(playersNick);
            return true;
        }
        return false;
    }

    /**
     * refill the lrb when there is no tiles left in the map or when tiles left are free in all sides
     * by replacing Empty tiles with randomly taken items
     */
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

    /**
     * @return the number of players in the match
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * @return the Common Goal Cards
     */
    public CommonGoalCard[] getCommonGoals() {
        return commonGoals;
    }

    /**
     * @param commonGoals set in the match
     */
    public void setCommonGoals(CommonGoalCard[] commonGoals) {
        this.commonGoals = commonGoals;
    }

    /**
     * @param player is the next that makes the move
     */
    public void setNext(Player player) {
        if (player == null) {
            this.wasnext = this.next;

            this.next = player;
            setChanged();
            notifyObservers("###gamepaused###");
        } else {
            this.moveNumber++;
            setChanged();
            notifyObservers(moveNumber);

            this.next = player;
            setChanged();
            notifyObservers(next.getNickName());
        }
    }

    /**
     * @param x axis in the lrb
     * @param y allies in the lrb
     * @param quantity the number of tile to choose in your turn (1<=quantity<=3)
     * @param direction of the number of tiles chosen (Up,Down,Right,Left)
     * @return the array with the item tile chosen
     */
    public ItemTile[] chooseTiles(int x, int y, int quantity, char direction) {


        //tested
        try {
            if (quantity < 1 || quantity > 3) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            return null;

        }
        // initializing an item tile array with the size==quantity
        ItemTile[] chosen = new ItemTile[quantity];
        for (int i = 0; i < quantity; i++) {
            chosen[i] = new ItemTile(Item.EMPTY);
        }
        //choosing the direction of item tiles to take if they are takeable
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
                setChanged();
                notifyObservers(this.map.getMap());
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
                setChanged();
                notifyObservers(this.map.getMap());
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
                setChanged();
                notifyObservers(this.map.getMap());
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
                setChanged();
                notifyObservers(this.map.getMap());
                return chosen;
            default:
                return null;
        }

    }

    /**
     * @return the lrb map
     */
    public LRB getMap() {
        return map;
    }

    /**
     * @param o an observer to be added.
     */
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    /**
     * an observer to be deleted.
     */
    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers();
    }

    /**
     * @param arg any object to be notified
     */
    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }

    /**
     * @return if the match is ending, true if one player puts the last tile in the bookshelf
     */
    public boolean isEnding() {
        return ending;
    }

    /**
     * @return match id
     */
    public int getId() {
        return id;
    }

    /**
     * @return player array list of the match
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }
        //todo inutilizzato
    public PublicModelObserver getPublicModelObserver() {
        return observer;
    }

    /**
     * @param b is false by default, it is set to true when one player puts the last tile in the bookshelf
     */
    public void setEnding(boolean b) {
        this.ending = b;
    }

    /**
     * checks player score every end of the round, dividing partial scores (commongoal score, personalgoal score,
     * adjscore,firstplayerscore)
     *  @param p player in the match
     */
    public void checkScores(Player p) {
        p.setADJScore(p.getBookshelf().ADJScore());
        int cgscore = p.getCGScore();
        if (!p.getCG1Reached() && this.commonGoals[0].getGoal().instanceGoal(p)) {
            cgscore = cgscore + this.CG1Scores.get(this.CG1Scores.size() - 1);
            this.CG1Scores.remove(this.CG1Scores.size() - 1);
            p.setCG1Reached(true);
        }
        if (!p.getCG2Reached() && this.getCommonGoals()[1].getGoal().instanceGoal(p)) {
            cgscore = cgscore + p.getCGScore() + this.CG2Scores.get(this.CG2Scores.size() - 1);
            this.CG2Scores.remove(this.CG2Scores.size() - 1);
            p.setCG2Reached(true);
        }
        p.setCGScore(cgscore);
        p.getPersonalGoal().checkScore(p);

        points [players.indexOf(p)] = (short) p.totScore();
        //todo tolta da qui notify dei points
    }

    /**
     * @return the number of players connected in a match
     */
    public int getActive() {
        return activePlayers;
    }

    /**
     * @param i is the new number of active players in the match
     */
    public void setActive(int i) {
        this.activePlayers = i;
    }

    /**
     * @return wasnext player
     */
    public Player getWasNext() {
        return this.wasnext;
    }
}