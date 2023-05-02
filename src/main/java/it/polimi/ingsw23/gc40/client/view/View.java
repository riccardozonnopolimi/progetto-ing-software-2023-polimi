package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.client.connection.Connection;

import java.rmi.RemoteException;

public abstract class View {
    Connection con;
    public View(Connection connection) {
        this.con = connection;
    }

    public void addPlayer(String name) throws RemoteException, Exception{
        con.addPlayer(name);
    }

    public abstract void start() throws RemoteException, Exception;
}
