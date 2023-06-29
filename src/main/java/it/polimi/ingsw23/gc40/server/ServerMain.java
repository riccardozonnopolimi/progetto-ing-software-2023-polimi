package it.polimi.ingsw23.gc40.server;

import it.polimi.ingsw23.gc40.client.view.CLI;
import it.polimi.ingsw23.gc40.server.connection.ConnectionOpener;
import it.polimi.ingsw23.gc40.server.controller.LobbyController;
import it.polimi.ingsw23.gc40.server.controller.ProxyController;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        //LET USER CHOICE IP SERVER
        System.out.println("Write the target (ip address or domain) of the server: " + CLI.green + "(You can press enter for localhost)" + CLI.resetStdColor);
        Scanner scanner = new Scanner(System.in);
        String host = scanner.nextLine();

        if (host.equals(""))
            host = "localhost";

        LobbyController lobbyController = new LobbyController();
        ProxyController proxyController = new ProxyController(lobbyController);
        ConnectionOpener a = new ConnectionOpener(proxyController, host);
        a.rmiInit();
        a.start();

    }
}
