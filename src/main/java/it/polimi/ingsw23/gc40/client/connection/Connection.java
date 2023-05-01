package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.netModel.TileData;

import java.rmi.Remote;

public interface Connection extends Remote {
    public void addPlayer(String name);

    TileData[] getTile(int x, int y, char direction, int qty);

    void startInserction(int column);
    void putTile(int id);

}

