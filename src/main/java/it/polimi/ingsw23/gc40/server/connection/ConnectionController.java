package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.netModel.TileData;

import java.rmi.Remote;

//copia dei metodi dell'interfaccia nel client
public interface ConnectionController extends Remote {
    void addPlayer(String name);
    void extAddPlayer();
    TileData[] getTile(int x, int y, char direction, int qty);
    void startInserction(int column);
    void putTile(int id);



}
