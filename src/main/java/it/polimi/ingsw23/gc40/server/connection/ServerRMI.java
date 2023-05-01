package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.server.Bookshelf;
import it.polimi.ingsw23.gc40.server.Match;

//implementazione vera dei metodi dell'interfaccia tramite la classe astratta utilizzando metodi del model
public class ServerRMI extends Connection{
    public ServerRMI(ConnectionController controller) {
            super(controller);

            this.privateModel.addObserver((o, arg) -> {
                // Cose specifiche di RMI

            });
    }
    public void addPlayer(String name) {
        controller.AddPlayer(name);
    }

    static Connection createConnection(ConnectionController controller, Bookshelf bookshelf, Match match){
        return new ServerRMI(controller);
    }
}