package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.MatchModel;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MatchRMI  implements Match, Serializable {
    MatchModel model;
    static int port;
    public MatchRMI(int numPlayer) {
       this.model = new MatchModel(numPlayer);
    }

    public ArrayList<Player> getPlayers() {
        return model.getPlayers();
    }

    public boolean isEnding() {
        return model.isEnding();
    }

    public Player getNext() {
        return model.getNext();
    }

    public boolean playable() {
        //Tested
        //starts when there are all the players
        if (model.getPlayers().size() == model.getNumPlayers()) return true;
        //if a player has filled his bookshelf and nextPlayer is first player i can not play
        if (model.isEnding() && model.getNext().isFirst()) return false;
        return false;
    }

    public Player winner() {
        //TO TEST
        int[] scores = new int[model.getPlayers().size()];
        for (int i = 0; i < model.getPlayers().size(); i++) {
            scores[i] = model.getPlayers().get(i).getCGScore() + model.getPlayers().get(i).getPGScore() + model.getPlayers().get(i).getADJScore();
            if (model.getPlayers().get(i).isFirst()) scores[i]++;
        }
        int max = 0;
        for (int i = 0; i < model.getPlayers().size(); i++)
            if (scores[i] > scores[max]) max = i;
        return model.getPlayers().get(max);
    }

    public ItemTile takeItem() {
        //TO TEST
        int rand = (int) (Math.random() * model.getBag().size());
        ItemTile tmp = model.getBag().get(rand);
        model.getBag().remove(rand);
        return tmp;
    }

    public void setPlayersOrder() {
        ArrayList<Player> PLtemp = new ArrayList<Player>();
        for (Player pl : model.getPlayers()) {
            PLtemp.add(pl);
        }
        int rand;
        for (int i = 0; i < model.getPlayers().size(); i++) {
            rand = (int) (Math.random() * PLtemp.size());
            model.getPlayers().set(i, PLtemp.get(rand));
            PLtemp.remove(rand);
            model.getPlayers().get(i).setPersonalGoal(model.getPersonalGoals().get(0));
            model.getPersonalGoals().remove(0);
        }
        model.getPlayers().get(0).setFirst(true);
    }

    //This method returns true to server if the match is ready to start (has all players)
    public boolean addPlayer(Player p) {
        model.getPlayers().add(p);

        if (model.getPlayers().size() == model.getNumPlayers()) {
            setPlayersOrder();
            return true;
        }
        return false;
    }

    public void refill() {
        //TO TEST
        // manca il controllo di evereyTilesCompletelyFree di LRB
        if (this.model.getMap().everyTileCompletelyFree()) {
            ItemTile[][] map = this.model.getMap().getMap();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (map[i][j].getItem() != Item.NOTREACHABLE) {
                        if (map[i][j].getItem() == Item.EMPTY) map[i][j] = takeItem();
                    }
                }
            }
            this.model.getMap().setMap(map);
        }
    }

    public void round(Player p) {
        int placeables = p.getBookshelf().placeables();
        //utente sceglie tile
        //refresh scores
        p.setADJScore(p.getBookshelf().ADJScore());
        if (model.getCommonGoals().get(0).getGoal().instanceGoal(p))
            p.setCGScore(p.getCGScore() + model.getCG1Scores().get(model.getCG1Scores().size()) - 1);
        if (model.getCommonGoals().get(1).getGoal().instanceGoal(p))
            p.setCGScore(p.getCGScore() + model.getCG2Scores().get(model.getCG2Scores().size()) - 1);
        p.getPersonalGoal().checkScore(p);
        //check if there is space in the player bookshelf
        if (p.getBookshelf().placeables() == 0) {
            p.getBookshelf().setFull(true);
            p.setFirstScore(1);
            this.model.setEnding(true);
        }
        //set turn to next player
        int num;
        if (model.getPlayers().indexOf(p) == model.getPlayers().size() - 1) num = 0;
        else num = model.getPlayers().indexOf(p) + 1;
        model.setNext(model.getPlayers().get(num));
    }

    public static void main(String args) throws RemoteException {
        MatchRMI stub = null;
        MatchModel sMod = new MatchModel(2);
        Registry registry = null;
        try {
            stub = (MatchRMI) UnicastRemoteObject.exportObject(sMod, port);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            registry = LocateRegistry.createRegistry(port);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            registry.bind("Match", stub);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            throw new RuntimeException(e);
        }
    }
}

