package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.server.connection.ServerModel;
import it.polimi.ingsw23.gc40.server.connection.ServerRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Factory {
    ServerModel stub;
    public Factory() {


    }

    public Socket socket() {
        //socket
        return  null;
    }


    public static Connection rmi(String name, String host) throws Exception{
        Registry registry = LocateRegistry.getRegistry(host);
        System.out.println("Connesso");
        return new ClientRMI((((ServerModel) registry.lookup(name)).rmi()));
    }
}
