package it.polimi.ingsw23.gc40.netModel;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.rmi.Remote;
import java.rmi.RemoteException;


//copia dei metodi dell'interfaccia nel client
public interface ConnectionController extends Remote {
    boolean connect(String name) throws RemoteException;

    boolean hasQueueMatch() throws RemoteException;

    boolean newMatch(int n) throws RemoteException;

    int addToLastMatch(String string) throws RemoteException;

    void close(String nickname) throws RemoteException;

    void setView(View view) throws RemoteException;

    int startRound(String nickname, int matchID) throws RemoteException;

    ItemTile[] chooseTiles(int mid, int x, int y, int quantity, char direction) throws RemoteException;

    int getAvailableSlots(String nickname, int column) throws RemoteException;

    void insertTile(String nickname, ItemTile handTile, int column) throws RemoteException;

    void nextRound(String nickname, int matchId) throws RemoteException;

    void addObserver(Remote aRemoteObj, String name, int matchid) throws RemoteException;

    int reconnect(String nickname) throws RemoteException, InterruptedException;
}
