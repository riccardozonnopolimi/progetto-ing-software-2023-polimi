package it.polimi.ingsw23.gc40.server.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.lang.reflect.Type;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Match extends Observable {
    private int numPlayers=0;
    private ArrayList<ItemTile> bag = new ArrayList<ItemTile>();
    private ArrayList<CommonGoalCard> commonGoals = new ArrayList<>();
    private ArrayList<PersonalGoalCard> personalGoals = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private LRB map;
    private boolean ended;

    public Match() {
        //TO DO
    }
    public Match(int numPlayers, ArrayList<ItemTile> bag, ArrayList<Player> players, LRB map, ArrayList<CommonGoalCard> commonGoals) {
        this.numPlayers = numPlayers;
        this.bag = bag;
        this.players = players;
        this.map = map;
        this.commonGoals = commonGoals;

        //importo i personal goal da file json
        Gson gson = new Gson();
        Type PersonalGoalCardType = new TypeToken<ArrayList<PersonalGoalCard>>() {}.getType();
        try {
           personalGoals = gson.fromJson(new FileReader("personalGoalCards.json"), PersonalGoalCardType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //fine importazione da json

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
