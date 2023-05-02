package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.netModel.TileData;

import java.rmi.RemoteException;

public class Controller implements ConnectionController{
    public void addPlayer(String name){
        System.out.println(name);
        System.out.println("#classe controller del server - metodo addplayer");
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
