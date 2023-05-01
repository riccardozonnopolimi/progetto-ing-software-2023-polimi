package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.netModel.TileData;
import it.polimi.ingsw23.gc40.server.connection.ServerRMI;

public class ClientRMI implements Connection{
     ServerRMI stub;
    public ClientRMI(ServerRMI stub) {
        this.stub = stub;
    }

    @Override
    public void addPlayer(String name) {

        stub.addPlayer(name);
       // throw ExecutionControl.NotImplementedException;
    }

    @Override
    public TileData[] getTile(int x, int y, char direction, int qty) {
        //return stub.getTile(x,y,dir,qty);
        return null;
    }

    @Override
    public void startInserction(int column) {
        //stub.start....

    }

    @Override
    public void putTile(int id) {
    //stub.put....
    }

}
