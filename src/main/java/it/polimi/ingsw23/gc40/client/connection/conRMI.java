package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelObserver;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelObserver;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;

public class conRMI implements ConnectionController {
    ConnectionController stub;
    private View view;

    public conRMI(ConnectionController stub) throws RemoteException {
        this.stub = stub;
        print("#nuova connessione RMI");


    }

    @Override
    public void setView(View view) throws RemoteException {
        this.view = view;
        stub.addObserver(new conUpdateRMI(view));
    }

    @Override
    public int startRound(String name, int m) throws RemoteException {
        return stub.startRound(name, m);
    }

    @Override
    public ItemTile[] chooseTiles(int mid, int x, int y, int quantity, char direction) throws RemoteException {

        ItemTile[] ht = stub.chooseTiles(mid, x, y, quantity, direction);
        return ht;
    }

    @Override
    public int getAvailableSlots(String nickname, int column) throws RemoteException {
        return stub.getAvailableSlots(nickname, column);
    }

    @Override
    public void insertTile(String nickname, ItemTile handTile, int column) throws RemoteException {
        stub.insertTile(nickname, handTile, column);
    }

    @Override
    public boolean addPlayer(String name) throws RemoteException {
        return stub.addPlayer(name);
    }

    public boolean newMatch(int n) throws RemoteException {
        return stub.newMatch(n);
    }

    public boolean hasQueueMatch() throws RemoteException {
        return stub.hasQueueMatch();
    }


    @Override
    public int addToLastMatch(String name) throws RemoteException {
        return stub.addToLastMatch(name);
    }


    @Override
    public void close() throws RemoteException {
        this.stub = null;
    }

    @Override
    public void print(String s) throws RemoteException {
        stub.print(s);
    }


    @Override
    public void nextRound(String nickname, int matchId) throws RemoteException {
        stub.nextRound(nickname, matchId);
    }

    @Override
    public void addObserver(Remote aRemoteObj) throws RemoteException {

    }

}