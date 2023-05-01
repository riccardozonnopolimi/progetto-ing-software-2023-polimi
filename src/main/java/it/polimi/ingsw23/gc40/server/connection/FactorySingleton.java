package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.netModel.TileData;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public final class FactorySingleton implements ConnectionController {


    public FactorySingleton(){

    }

    public void init() throws RemoteException {
        System.out.println("entra");
        try {

            ConnectionController stub = (ConnectionController) UnicastRemoteObject.exportObject(this, 0);
            System.out.println("2");

            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("3");

            registry.bind("Server", stub);
            System.out.println("server pronto");

        } catch (Exception e) {
            System.out.println("catch");
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }
    //imposti server socket e conseguenze


    public void addPlayer(String name){
        System.out.println("arrivo qui");
        //todo
    }
    public void extAddPlayer(){
        //todo
    }
    public TileData[] getTile(int x, int y, char direction, int qty){
        return null; //todo
    }
    public void startInserction(int column){
//todo
    }
    public void putTile(int id) {
//todo
    }

}

