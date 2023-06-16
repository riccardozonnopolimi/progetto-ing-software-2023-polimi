package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.rmi.Remote;
import java.rmi.RemoteException;

public class conRMI implements ConnectionController {
    ConnectionController stub;
    private View view;

    public conRMI(ConnectionController stub) throws RemoteException {
        this.stub = stub;
    }

    //calling all method on server using stub

    @Override
    public void setView(View view) throws RemoteException {
        this.view = view;
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
    public boolean connect(String name) throws RemoteException {
        return stub.connect(name);
    }

    public boolean newMatch(int n) throws RemoteException {
        return stub.newMatch(n);
    }

    public boolean hasQueueMatch() throws RemoteException {
        return stub.hasQueueMatch();
    }


    @Override
    public int addToLastMatch(String name) throws RemoteException {
        int n = stub.addToLastMatch(name);
        return n;
    }


    @Override
    public void close(String nickname) throws RemoteException {
        stub.close(nickname);
        this.stub = null;
    }


    @Override
    public void nextRound(String nickname, int matchId) throws RemoteException {

        stub.nextRound(nickname, matchId);
    }

    @Override
    public void addObserver(Remote aRemoteObj, String s, int n) throws RemoteException {
        stub.addObserver(new conUpdateRMI(view), s, n);
    }

    @Override
    public int reconnect(String nickname) throws RemoteException, InterruptedException {
        return stub.reconnect(nickname);
    }

}