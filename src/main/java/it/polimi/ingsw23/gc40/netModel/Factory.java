package it.polimi.ingsw23.gc40.netModel;

import it.polimi.ingsw23.gc40.server.connection.ServerRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Factory extends Remote {
    public ServerRMI rmi() throws RemoteException;
}
