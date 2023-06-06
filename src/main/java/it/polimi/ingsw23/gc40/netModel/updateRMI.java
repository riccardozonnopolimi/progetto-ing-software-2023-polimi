package it.polimi.ingsw23.gc40.netModel;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface updateRMI extends Remote {
    void update(Object obj) throws RemoteException;
}
