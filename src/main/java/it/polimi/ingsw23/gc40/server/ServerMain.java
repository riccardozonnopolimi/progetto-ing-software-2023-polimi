package it.polimi.ingsw23.gc40.server;

import it.polimi.ingsw23.gc40.server.connection.Controller;
import it.polimi.ingsw23.gc40.server.connection.Host;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        Controller controller = new Controller();
        Host a = new Host(controller);
        a.rmiInit();
        a.socketInit();
    }
}
