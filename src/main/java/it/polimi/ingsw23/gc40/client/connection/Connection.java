package it.polimi.ingsw23.gc40.client.connection;

public interface Connection {
    public void addPlayer(String name);

    TileData[] getTile(int x, int y, char direction, int qty);

    void startInserction(int column);
    void putTile(int id);

}

class TileData {
    String color;
    int num;
    int id;
}