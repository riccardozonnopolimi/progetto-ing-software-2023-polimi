package it.polimi.ingsw23.gc40.server;

import it.polimi.ingsw23.gc40.server.connection.ProxyController;
import it.polimi.ingsw23.gc40.server.connection.ConnectionOpener;
import it.polimi.ingsw23.gc40.server.model.LobbyController;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        LobbyController lobbyController = new LobbyController();
        ProxyController proxyController = new ProxyController(lobbyController);
        ConnectionOpener a = new ConnectionOpener(proxyController);
        a.rmiInit();
        a.socketInit();
    }
}
