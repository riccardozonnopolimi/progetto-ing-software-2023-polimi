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
        LobbyController lobbyController = new LobbyController();
        ProxyController proxyController = new ProxyController(lobbyController);
        ConnectionOpener a = new ConnectionOpener(proxyController);
        a.rmiInit();
        a.start();
/*
        privateSocketListener p = new privateSocketListener(lobbyController);
        p.start();




        Thread publicSocketListener = new Thread(() -> {
            try {
                ServerSocket publicSocket = new ServerSocket(6791);
                System.out.println("#Connessione PublicSocket riuscita");
                PrintWriter publicOut = new PrintWriter(publicSocket.accept().getOutputStream(), true);

                lobbyController.setPublicSocket(publicOut);

            } catch (
                    UnknownHostException e) {
                System.err.println("Unknown host");
            } catch (
                    IOException e) {
                throw new RuntimeException(e);
            }
        });
        publicSocketListener.start();
        */



        System.out.println("ciao");
    }
}
