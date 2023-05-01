package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.netModel.TileData;
//copia dei metodi dell'interfaccia nel client
public interface ConnectionController {
    void AddPlayer(String name);
    void extAddPlayer();
    TileData[] getTile(int x, int y, char direction, int qty);
    void startInserction(int column);
    void putTile(int id);
}
