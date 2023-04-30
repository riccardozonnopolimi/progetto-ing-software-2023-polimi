package it.polimi.ingsw23.gc40.client.factory;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIFactory extends Factory{
    Servercazz stub;
    public RMIFactory() throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost");
        System.out.println("bella");
        this.stub = (Server) registry.lookup("Server");
    }
}
