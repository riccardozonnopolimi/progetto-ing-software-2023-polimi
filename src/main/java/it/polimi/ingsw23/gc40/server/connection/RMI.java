package it.polimi.ingsw23.gc40.server.connection;

public class RMI extends Connection{

        public RMI(ConnectionController controller) {
            super(controller);
    }
    public void addPlayer(String name) {
        controller.AddPlayer(name);

    }
}
