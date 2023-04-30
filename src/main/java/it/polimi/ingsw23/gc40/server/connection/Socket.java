package it.polimi.ingsw23.gc40.server.connection;
//implementazione vera dei metodi dell'interfaccia tramite la classe astratta utilizzando metodi del model
public class Socket extends Connection{
    public Socket(ConnectionController controller) {
        super(controller);
    }
    public void addPlayer(String name) {
        controller.AddPlayer(name);

    }
}
