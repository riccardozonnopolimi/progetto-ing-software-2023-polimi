package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.netModel.Factory;
import it.polimi.ingsw23.gc40.netModel.tmp;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public final class FactorySingleton implements Factory {
    private static ConnectionController controller;
    private static FactorySingleton INSTANCE;

    private FactorySingleton(ConnectionController controller) throws Exception{
        FactorySingleton.controller = controller;
    }

    public static void init(ConnectionController controller) throws Exception {
        if (INSTANCE == null) {
            INSTANCE = new FactorySingleton( controller);
            System.out.println("prova");

            try {
                ServerModel stub = (ServerModel) UnicastRemoteObject.exportObject(FactorySingleton.INSTANCE, 0);
                System.out.println("2");
                Registry registry = LocateRegistry.createRegistry(1099);

                System.out.println("3");
                registry.bind("Server", stub);
                System.out.println("server pronto");
            } catch (Exception e) {
                System.err.println(e.toString());
                e.printStackTrace();
            }
        }
    }

    public ServerRMI rmi() {
        return new ServerRMI(controller);
    }
}

