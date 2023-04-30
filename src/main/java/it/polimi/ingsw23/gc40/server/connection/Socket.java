package it.polimi.ingsw23.gc40.server.connection;

public class Socket extends Connection{
    public Socket(ConnectionController controller) {
        super(controller);
    }
    public void addPlayer(String name) {
        controller.AddPlayer(name);

    }
}
