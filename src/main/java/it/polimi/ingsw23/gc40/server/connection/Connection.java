package it.polimi.ingsw23.gc40.server.connection;

public abstract class Connection {
    // Observable;
    ConnectionController controller;

    public Connection(ConnectionController controller) {
        this.controller=controller;
    }

}
