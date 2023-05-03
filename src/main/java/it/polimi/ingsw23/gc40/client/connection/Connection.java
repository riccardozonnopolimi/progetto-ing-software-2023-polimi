package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.TileData;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Connection extends Remote {
    public boolean addPlayer(String name) throws RemoteException, Exception;
    TileData[] getTile(int x, int y, char direction, int qty)throws RemoteException;
    void startInserction(int column)throws RemoteException;
    void putTile(int id)throws RemoteException;
void serverPrint(String s) throws RemoteException;
    boolean newMatch(String string) throws RemoteException;
    boolean addToLastMatch(String name) throws RemoteException;

    boolean hasQueueMatch() throws RemoteException;
    boolean playable() throws RemoteException;

    void close() throws RemoteException;

}

