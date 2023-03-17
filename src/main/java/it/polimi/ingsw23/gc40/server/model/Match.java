package it.polimi.ingsw23.gc40.server.model;

import java.util.ArrayList;
import java.util.Observable;

public class Match extends Observable {
    private int numPlayers=0;
    private ArrayList<ItemTile> bag = new ArrayList<ItemTile>();
    private CommonGoalCard[] commonGoals;
    private ArrayList<Player> players = new ArrayList<Player>();
    private LRB map;
    private boolean ended;

    public Match() {
        //TO DO
    }
    public Match(int numPlayers, ArrayList<ItemTile> bag, ArrayList<Player> players, LRB map, CommonGoalCard commonGoals) {
        this.numPlayers = numPlayers;
        this.bag = bag;
        this.players = players;
        this.map = map;
        this.commonGoals = new CommonGoalCard[2];
    }
    public void start() {
        //TO DO
    }
    public Player winner() {
        //TO DO
        return null;
    }
    public ItemTile takeItem() {
        //TO DO
        return null;
    }
    public void setPlayersOrder() {
        //TO DO
    }
    public void addPlayer(Player p){
        players.add(p);
        if(players.size()==numPlayers)
        {
            setPlayersOrder();
        }
    }

    public void round(Player p) {
        int placeables = p.getShelf().placeables();



        //utente sceglie tile
        map.takeables();

    }
}
