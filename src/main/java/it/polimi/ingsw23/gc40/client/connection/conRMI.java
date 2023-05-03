package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.TileData;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;

import java.rmi.RemoteException;

public class conRMI implements Connection{
    ConnectionController stub;
    public conRMI(ConnectionController stub) {
        this.stub = stub;
    }

    @Override
    public boolean addPlayer(String name) throws RemoteException{
        return stub.addPlayer(name);
    }

    public boolean newMatch(String string) throws RemoteException {
        return stub.newMatch(string);
    }

    @Override
    public TileData[] getTile(int x, int y, char direction, int qty)  {
        //return stub.getTile(x,y,dir,qty);
        return null;
    }


    public boolean hasQueueMatch() throws RemoteException{
        return stub.hasQueueMatch();
    }

    @Override
    public boolean playable() throws RemoteException {
        return stub.playable();
    }

    @Override
    public boolean addToLastMatch(String name) throws RemoteException {
        return stub.addToLastMatch(name);
    }

    @Override
    public void startInserction(int column) {
        //stub.start....

    }

    @Override
    public void putTile(int id) {
        //stub.put....
    }

    @Override
    public void close() throws RemoteException {
        this.stub = null;
    }
}
