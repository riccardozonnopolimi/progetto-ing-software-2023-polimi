package it.polimi.ingsw23.gc40.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientMain extends UnicastRemoteObject implements Client {
    static int port;
    protected ClientMain(int port) throws RemoteException {
        super(port);
    }
    public static void main(String args) {

    }
  }
