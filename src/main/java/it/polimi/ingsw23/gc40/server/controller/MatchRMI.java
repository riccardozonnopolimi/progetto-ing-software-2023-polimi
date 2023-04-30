package it.polimi.ingsw23.gc40.server.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw23.gc40.server.model.*;
import it.polimi.ingsw23.gc40.server.model.goals.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class MatchRMI extends Observable implements Match, Serializable {
    MatchModel model;
    static int port;
    ArrayList<Observer> observers = new ArrayList<>();



    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }

    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers();
    }

    public void removeObserver(Observer observer) throws RemoteException {
        observers.remove(observer);
    }
    private int numPlayers;
    private ArrayList<ItemTile> bag = new ArrayList<>();
    private ArrayList<CommonGoalCard> commonGoals = new ArrayList<>();
    private ArrayList<Integer> CG1Scores = new ArrayList<>();
    private ArrayList<Integer> CG2Scores = new ArrayList<>();
    private ArrayList<PersonalGoalCard> personalGoals = new ArrayList<>();

    public ArrayList<Player> getPlayers() {
        return players;
    }

    private ArrayList<Player> players = new ArrayList<>();
    private LRB map;

    public boolean isEnding() {
        return ending;
    }

    private boolean ending;
    private Player next;

    Match stub;

public void connnection(){
    try {
        stub = (Match) UnicastRemoteObject.exportObject(this, 0);
        Registry registry = LocateRegistry.getRegistry(1099);
        registry.bind("Match", stub);
    } catch (Exception e) {
        System.err.println(e.toString());
        e.printStackTrace();
    }

}
public void print(String s){
    System.out.println(s);
}
    public MatchRMI(int numPlayers)  {
        this.numPlayers = numPlayers;
        this.next = null;
        this.ending = false;
       /* try {
            stub = (Match) UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.bind("Match", stub);
            System.out.println("match collegato");
        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }

        System.out.println("match");
*/
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
        CGs.add(new G2());
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
        CGs.add(new G7());
        CGs.add(new G11());
        CGs.add(new G12());

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
        commonGoals.add(new CommonGoalCard(CGs.get(rand)));
        CGs.remove(rand);

        rand = (int) (Math.random() * CGs.size());
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
            rand = (int) (Math.random() * PGs.size());
            personalGoals.add(PGs.get(rand));
            PGs.remove(rand);
        }
    }
    public Player getNext() {
        return next;
    }
    public boolean playable() {
        //Tested
        // if a player has filled his bookshelf and nextPlayer is first player i can not play
        if (ending && next.isFirst()) return false;
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
            if (scores[i] > scores[max]) max = i;

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

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public ArrayList<ItemTile> getBag() {
        return bag;
    }

    public void setBag(ArrayList<ItemTile> bag) {
        this.bag = bag;
    }

    public ArrayList<CommonGoalCard> getCommonGoals() {
        return commonGoals;
    }

    public void setCommonGoals(ArrayList<CommonGoalCard> commonGoals) {
        this.commonGoals = commonGoals;
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

    public LRB getMap() {
        return map;
    }

    public void setMap(LRB map) {
        this.map = map;
    }

    public void setEnding(boolean ending) {
        this.ending = ending;
    }

    public void setNext(Player next) {
        this.next = next;
    }

    public ItemTile[] chooseTiles(int x,int y,int quantity, char direction){
        ItemTile[] chosen;
        //to test
        for(int i = 0; i < quantity;i++){
            switch (){
                case "U"
            }
        }
        this.map.print(map.takeables());

        if(map.isTakeable(x,y))

            map.getTileInMap(x,y);
        else{
            System.out.println("Non prendibile.");
            return null;
        }
    }

    public void round(Player p) {
        int placeables = p.getBookshelf().placeables();

        //utente sceglie tile

        ItemTile[] chosen=null;
        do {
            System.out.println("Per selezionare le Tiles inserisci le coordinate della prima Tile nella riga che vuoi prendere:");
            Scanner sc = new Scanner(System.in);
            System.out.println("X: ");
            int x = sc.nextInt();
            System.out.println("Y: ");
            sc.reset();
            int y = sc.nextInt();
            System.out.println("Quante tile vuoi prendere?");
            sc.reset();
            int quantity = sc.nextInt();
            System.out.println("Verso dove vuoi prendere?   (U per Up - D per Down - L per Left - R per Right)");
            sc.reset();
            char direction = sc.nextLine().toCharArray()[0];
            chosen = this.chooseTiles(x,y,quantity, direction);
        }while(chosen==null);


        //refresh scores


        p.setADJScore(p.getBookshelf().ADJScore());
        if (commonGoals.get(0).getGoal().instanceGoal(p))
            p.setCGScore(p.getCGScore() + CG1Scores.get(CG1Scores.size()) - 1);
        if (commonGoals.get(1).getGoal().instanceGoal(p))
            p.setCGScore(p.getCGScore() + CG2Scores.get(CG2Scores.size()) - 1);
        p.getPersonalGoal().checkScore(p);
        //check if there is space in the player bookshelf
        if (p.getBookshelf().placeables() == 0) {
            p.getBookshelf().setFull(true);
            p.setFirstScore(1);
            this.ending = true;
        }
        //set turn to next player
        int num;
        if (players.indexOf(p) == players.size() - 1) num = 0;
        else num = players.indexOf(p) + 1;
        next = players.get(num);
    }


}


