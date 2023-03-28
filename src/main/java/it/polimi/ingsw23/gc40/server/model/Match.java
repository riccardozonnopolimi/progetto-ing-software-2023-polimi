package it.polimi.ingsw23.gc40.server.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw23.gc40.server.model.goals.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Observable;


public class Match extends Observable {
    private int numPlayers = 0;
    private ArrayList<ItemTile> bag = new ArrayList<ItemTile>();
    private ArrayList<CommonGoalCard> commonGoals = new ArrayList<>();
    private ArrayList<Integer> CG1Scores = new ArrayList<>();
    private ArrayList<Integer> CG2Scores = new ArrayList<>();
    private ArrayList<PersonalGoalCard> personalGoals = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private LRB map;
    private boolean ending;
    private Player next;


    public Match(int numPlayers) {
        this.numPlayers = numPlayers;
        this.next = null;
        this.ending = false;

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
        CGs.add(new G1_G3(2, 6));
        //create CG3
        CGs.add(new G1_G3(4, 4));
        CGs.add(new G2_G7_G12(2));
        CGs.add(new G4());
        //create CG5
        CGs.add(new G5_G8_G9_G10(3, 3, true));
        //create CG8
        CGs.add(new G5_G8_G9_G10(3, 4, false));
        //create CG9
        CGs.add(new G5_G8_G9_G10(6, 2, true));
        //create CG10
        CGs.add(new G5_G8_G9_G10(5, 2, false));
        CGs.add(new G6());
        CGs.add(new G2_G7_G12(7));
        CGs.add(new G11());
        CGs.add(new G2_G7_G12(12));

        //intialize common goals scores arrays
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
        int rand = (int) (Math.random() * (CGs.size() + 1));
        commonGoals.add(new CommonGoalCard(CGs.get(rand)));
        CGs.remove(rand);

        rand = (int) (Math.random() * (CGs.size() + 1));
        commonGoals.add(new CommonGoalCard(CGs.get(rand)));
        CGs.remove(rand);


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
            rand = (int) (Math.random() * (PGs.size() + 1));
            personalGoals.add(PGs.get(rand));
            PGs.remove(rand);
        }


    }

    public Player getNext() {
        return next;
    }

    public boolean playable() {
        //quando ci sono tutti i giocatori posso iniziare
        if (players.size() == numPlayers) return true;

        //se un giocatore ha riempito la shelf ed il prossimo giocatore sarebbe colui che ha iniziato, non posso giocare
        if (ending && next.isFirst()) return false;

        return false;
    }

    public Player winner() {
        int[] scores = new int[players.size()];
        for (int i = 0; i < players.size(); i++) {
            scores[i] = players.get(i).getCGscore() + players.get(i).getPGscore() + players.get(i).getADJscore();
            if (players.get(i).isFirst()) scores[i]++;
        }

        int max = 0;
        for (int i = 0; i < players.size(); i++)
            if (scores[i] > scores[max]) max = i;

        return players.get(max);
    }

    public ItemTile takeItem() {
        //TO TEST
        int rand = (int) (Math.random() * (bag.size() + 1));
        ItemTile tmp = bag.get(rand);
        bag.remove(rand);
        return tmp;
    }

    public void setPlayersOrder() {
        ArrayList<Player> PLtemp = players;
        int rand;

        for (int i = 0; i < players.size(); i++) {
            rand = (int) (Math.random() * (PLtemp.size() + 1));
            players.set(i, PLtemp.get(rand));
            PLtemp.remove(rand);
            players.get(i).setPersonalGoal(personalGoals.get(0));
            personalGoals.remove(0);
        }
        players.get(0).setFirst(true);
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
        //TO TEST
        // manca il controllo di evereyTilesCompletelyFree di LRB
        if(this.map.everyTileCompletelyFree()) {
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
    }

    public void round(Player p) {
        int placeables = p.getBookshelf().placeables();


        //utente sceglie tile


        //refresh scores
        p.setADJscore(p.getBookshelf().ADJscore());

        if (commonGoals.get(0).getGoal().instanceGoal(p))
            p.setCGscore(p.getCGscore() + CG1Scores.get(CG1Scores.size()) - 1);
        if (commonGoals.get(1).getGoal().instanceGoal(p))
            p.setCGscore(p.getCGscore() + CG2Scores.get(CG2Scores.size()) - 1);

        p.getPersonalGoal().checkScore(p);


        //check if there is space in the player bookshelf
        if (p.getBookshelf().placeables() == 0) {
            p.getBookshelf().setFull(true);
            this.ending = true;
        }
        //set turn to next player
        int num;
        if (players.indexOf(p) == players.size() - 1) num = 0;
        else num = players.indexOf(p) + 1;
        next = players.get(num);
    }
}
