package it.polimi.ingsw23.gc40.client.connection;


import it.polimi.ingsw23.gc40.server.connection.ConnectionController;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ConnectionStarter {

    public ConnectionStarter() {


    }

    public conSocket socket() {
        //socket
        return  null;
    }


    public static ConnectionController rmi(String name, String host) throws Exception{
        Registry registry = LocateRegistry.getRegistry(host);
        System.out.println("Connesso");
        ConnectionController fac = ((ConnectionController) registry.lookup(name));
        return fac;
    }
}
