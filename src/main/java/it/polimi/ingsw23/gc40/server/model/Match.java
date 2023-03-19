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
    private boolean ending;
    private Player turn;



    public Match(int numPlayers) {
        this.numPlayers = numPlayers;
        this.turn=null;
        this.ending=false;

        //create a map for n players
        this.map = new LRB(numPlayers);

        //add to the bag all the tiles
        for(int i=0; i<22;i++)
            bag.add(new ItemTile(Item.CATS));
        for(int i=0; i<22;i++)
            bag.add(new ItemTile(Item.BOOKS));
        for(int i=0; i<22;i++)
            bag.add(new ItemTile(Item.GAMES));
        for(int i=0; i<22;i++)
            bag.add(new ItemTile(Item.FRAMES));
        for(int i=0; i<22;i++)
            bag.add(new ItemTile(Item.TROPHIES));
        for(int i=0; i<22;i++)
            bag.add(new ItemTile(Item.PLANTS));

        //Creating all common goals
        ArrayList<Goal> CGs = new ArrayList<>();
        CGs.add(new CG1(2,6));
        CGs.add(new CG1(4,4));
        CGs.add(new CG2());
        CGs.add(new CG4());
        CGs.add(new CG5());
        CGs.add(new CG6());
        CGs.add(new CG7());
        CGs.add(new CG8());
        CGs.add(new CG9());
        CGs.add(new CG10());
        CGs.add(new CG11());
        CGs.add(new CG12());

        //choosing 2 common goals randomly
        int rand = (int)(Math.random()*(CGs.size()+1));
        commonGoals.add(new CommonGoalCard(CGs.get(rand)));
        CGs.remove(rand);

        rand = (int)(Math.random()*(CGs.size()+1));
        commonGoals.add(new CommonGoalCard(CGs.get(rand)));
        CGs.remove(rand);



        //import all existing personal goals from json
        Gson gson = new Gson();
        ArrayList<PersonalGoalCard> PGs;
        Type PersonalGoalCardType = new TypeToken<ArrayList<PersonalGoalCard>>() {}.getType();
        try {
           PGs = gson.fromJson(new FileReader("personalGoalCards.json"), PersonalGoalCardType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //choosing n personal goal randomly
        for (int i=0; i<numPlayers; i++) {
            rand = (int) (Math.random() * (PGs.size() + 1));
            personalGoals.add(PGs.get(rand));
            PGs.remove(rand);
        }


    }
    public Player getTurn() {
        return turn;
    }
    public void start() {
        //TO DO
    }
    public Player winner() {
        //TO DO
        return null;
    }
    public ItemTile takeItem() {
        //TO TEST
        int rand = (int)(Math.random()*(bag.size()+1));
        ItemTile tmp= bag.get(rand);
        bag.remove(rand);
        return tmp;
    }
    public void setPlayersOrder() {
        ArrayList<Player> PLtemp = players;
        int rand;

        for (int i=0; i<players.size();  i++){
            rand = (int)(Math.random()*(PLtemp.size()+1));
            players.set(i, PLtemp.get(rand));
            PLtemp.remove(rand);
            players.get(i).setPersonalGoal(personalGoals.get(0));
            personalGoals.remove(0);
        }
        players.get(0).setFirst(true);
    }
    public void addPlayer(Player p){
        players.add(p);

        if(players.size()==numPlayers)
        {
            setPlayersOrder();
        }
    }

    public void refill()
    {
        //TO TEST
        ItemTile[][] map = this.map.getMap();
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(map[i][j].getItem()!=Item.NOTREACHABLE) {
                    if (map[i][j].getItem() == Item.EMPTY)
                        map[i][j]=takeItem();
                }
            }
        }
        this.map.setMap(map);
    }

    public void round(Player p) {
        int placeables = p.getBookshelf().placeables();



        //utente sceglie tile
        map.takeables();


        //check if there is space in the player bookshelf
        if(p.getBookshelf().placeables()==0) {
            p.getBookshelf().setFull(true);
            this.ending=true;
        }
        //set turn to next player
        int num;
        if(players.indexOf(p)==players.size())
            num=0;
        else
            num=players.indexOf(p)+1;
        turn=players.get(num);
    }
}
