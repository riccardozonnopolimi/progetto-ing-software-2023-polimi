package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;

import java.rmi.RemoteException;

public abstract class View {
    ConnectionController con;
    public View(ConnectionController connection) {
        this.con = connection;
    }

    public void addPlayer(String name) throws RemoteException, Exception{
        con.addPlayer(name);
    }

    public abstract void start() throws RemoteException, Exception;
}
