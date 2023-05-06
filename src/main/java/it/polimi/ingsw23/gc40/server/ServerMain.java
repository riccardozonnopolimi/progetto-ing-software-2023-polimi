package it.polimi.ingsw23.gc40.server;

import it.polimi.ingsw23.gc40.server.controller.ProxyController;
import it.polimi.ingsw23.gc40.server.connection.ConnectionOpener;
import it.polimi.ingsw23.gc40.server.controller.LobbyController;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        LobbyController lobbyController = new LobbyController();
        ProxyController proxyController = new ProxyController(lobbyController);
        ConnectionOpener a = new ConnectionOpener(proxyController);
        a.rmiInit();
        a.socketInit();
    }
}
