package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.ArrayList;

public interface Match extends Remote {
    void round(Player p);
    void refill();
    boolean addPlayer(Player p);
    void setPlayersOrder();
    ItemTile takeItem();
    Player winner();
    boolean playable();
    Player getNext();
    boolean isEnding();
    ArrayList<Player> getPlayers();
}
