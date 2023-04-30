package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.server.Bookshelf;
import it.polimi.ingsw23.gc40.server.Match;

//implementazione vera dei metodi dell'interfaccia tramite la classe astratta utilizzando metodi del model
public class RMI extends Connection{
    public RMI(ConnectionController controller, Bookshelf bookshelf, Match match) {
            super(controller, bookshelf, match);

            this.privateModel.addObserver((o, arg) -> {
                // Cose specifiche di RMI

            });
    }
    public void addPlayer(String name) {
        controller.AddPlayer(name);
    }

    static Connection createConnection(ConnectionController controller, Bookshelf bookshelf, Match match){
        return new RMI( controller,  bookshelf,  match);
    }
}

public class SERVERMAIN(){
    static RMI addToGame(){
        // Check last empty game

        // Create RMI Virtual Conn

        // Return RMI Virtual Conn to client

        return RMI.createConnection(controller, bookshelf, match);
    }
}