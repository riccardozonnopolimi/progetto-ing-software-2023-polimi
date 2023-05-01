package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.client.connection.ClientRMI;

import java.rmi.Remote;

public interface ServerModel extends Remote {

    ServerRMI rmi();
}
