package it.polimi.ingsw23.gc40.server;

import it.polimi.ingsw23.gc40.server.connection.Controller;
import it.polimi.ingsw23.gc40.server.connection.ConnectionOpener;
import it.polimi.ingsw23.gc40.server.model.Server;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        Controller controller = new Controller(server);
        ConnectionOpener a = new ConnectionOpener(controller);
        a.rmiInit();
        a.socketInit();
    }
}
