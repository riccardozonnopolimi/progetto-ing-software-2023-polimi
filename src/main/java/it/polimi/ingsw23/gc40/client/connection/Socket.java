package it.polimi.ingsw23.gc40.client.connection;

public class Socket implements Connection{
    public Socket() {
        //creo connessione socket con il server
    }

    @Override
    public void addPlayer(String name) {
        //invio in socket il comando e il nome utente poi vediamo come
       // throw ExecutionControl.NotImplementedException;
    }

    @Override
    public TileData[] getTile(int x, int y, char direction, int qty) {

        return null;
    }

    @Override
    public void startInserction(int column) {

    }

    @Override
    public void putTile(int id) {

    }

}
