package it.polimi.ingsw23.gc40.client.connection;

public class RMI implements Connection{
    ServerConn stub;
    public RMI(ServerRMI stub) {

        //creo stub rmi con server

        conn.addListener
                con.callmethod()
    }

    @Override
    public void addPlayer(String name) {

        //stub.addPlayer()
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
