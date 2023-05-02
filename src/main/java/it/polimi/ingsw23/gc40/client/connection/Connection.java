package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.netModel.TileData;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Connection extends Remote {
    public void addPlayer(String name) throws RemoteException, Exception;
    TileData[] getTile(int x, int y, char direction, int qty)throws RemoteException;
    void startInserction(int column)throws RemoteException;
    void putTile(int id)throws RemoteException;

    void close() throws RemoteException, Exception;
}

