package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.netModel.TileData;

import java.rmi.Remote;
import java.rmi.RemoteException;

//copia dei metodi dell'interfaccia nel client
public interface ConnectionController extends Remote {
    void addPlayer(String name) throws RemoteException;
    void extAddPlayer() throws RemoteException;
    TileData[] getTile(int x, int y, char direction, int qty) throws RemoteException;
    void startInserction(int column) throws RemoteException;
    void putTile(int id) throws RemoteException;

}
