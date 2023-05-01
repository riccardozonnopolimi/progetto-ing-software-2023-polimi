package it.polimi.ingsw23.gc40.server;

import it.polimi.ingsw23.gc40.server.connection.ConnectionController;

import it.polimi.ingsw23.gc40.server.connection.FactorySingleton;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMain {
    public static void main(String[] args) throws Exception {
       FactorySingleton a = new FactorySingleton();
       a.init();

/*
        try{
            ServerMain sMod = new ServerMain();
            ConnectionController stub = (ConnectionController) UnicastRemoteObject.exportObject(sMod, 0);
            Registry registry = LocateRegistry.createRegistry(1099);

            registry.bind("ConnectionController", stub);
        }catch (Exception e){
            System.out.println("catch del main");
            e.printStackTrace();
        }

    }
    */

}}
