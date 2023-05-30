package it.polimi.ingsw23.gc40.server;

import it.polimi.ingsw23.gc40.server.connection.ConnectionOpener;
import it.polimi.ingsw23.gc40.server.controller.LobbyController;
import it.polimi.ingsw23.gc40.server.controller.ProxyController;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class ServerMain {
    public static void main(String[] args) throws Exception {

        //CHANGE HERE HOST WHITH SERVER IP
        String host = "localhost";

        LobbyController lobbyController = new LobbyController();
        ProxyController proxyController = new ProxyController(lobbyController);
        ConnectionOpener a = new ConnectionOpener(proxyController, host);
        a.rmiInit();
        a.start();

    }
}
