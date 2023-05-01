package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.netModel.TileData;

public interface Connection {
    public void addPlayer(String name);

    TileData[] getTile(int x, int y, char direction, int qty);

    void startInserction(int column);
    void putTile(int id);

}

