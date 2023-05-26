package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Scanner;

public class conRMI implements ConnectionController{
    ConnectionController stub;
    public conRMI(ConnectionController stub) throws RemoteException {
        this.stub = stub;
        print("#nuova connessione RMI");
    }

    @Override
    public boolean addPlayer(String name) throws RemoteException{
        return stub.addPlayer(name);
    }

    public boolean newMatch(String string) throws RemoteException {
        return stub.newMatch(string);
    }




    public boolean hasQueueMatch() throws RemoteException{
        return stub.hasQueueMatch();
    }

    @Override
    public boolean playable() throws RemoteException {
        return stub.playable();
    }

    @Override
    public int addToLastMatch(String name) throws RemoteException {
        return stub.addToLastMatch(name);
    }




    /* /@Override
    public void serverPrint(String s) throws RemoteException {
        stub.print(s);
    }
    */
    @Override
    public void close() throws RemoteException {
        this.stub = null;
    }

    @Override
    public void print(String s) throws RemoteException {
        stub.print(s);
    }


    @Override
    public ObjectInputStream getOIS() throws RemoteException {
        return null;
    }
}
