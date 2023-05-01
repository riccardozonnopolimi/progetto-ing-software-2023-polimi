package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.client.connection.Connection;
import it.polimi.ingsw23.gc40.netModel.TileData;
import it.polimi.ingsw23.gc40.netModel.tmp;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public final class FactorySingleton implements Remote, ConnectionController {


    public FactorySingleton() throws Exception{

    }

    public void init() throws Exception {
        System.out.println("entra");
        try {
            ConnectionController stub = (ConnectionController) UnicastRemoteObject.exportObject(this, 0);
            System.out.println("2");

            Registry registry = LocateRegistry.createRegistry(15023);
            System.out.println("3");

            registry.bind("Server", stub);
            System.out.println("server pronto");

        } catch (Exception e) {
            System.out.println("catch");
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }


    public void addPlayer(String name){
        System.out.println("arrivo qui");

    }
    public void extAddPlayer(){

    }
    public TileData[] getTile(int x, int y, char direction, int qty){
        return null;
    }
    public void startInserction(int column){

    }
    public void putTile(int id) {

    }

}

