package it.polimi.ingsw23.gc40.server.connection;


import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.server.controller.ProxyController;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ConnectionOpener extends Thread {
    ProxyController proxyController;
    String host;

    public ConnectionOpener(ProxyController proxyController, String host) {
        this.proxyController = proxyController;
        this.host = host;

    }

    public void rmiInit() throws RemoteException {
        System.setProperty("java.rmi.server.hostname", host);
        try {

            ConnectionController stub = (ConnectionController) UnicastRemoteObject.exportObject(proxyController, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Server", stub);
            System.out.println("#server RMI pronto");

        } catch (Exception e) {
            System.out.println("catch");
            System.err.println(e.toString());
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            ServerSocket serverSocket = new ServerSocket(6789);


            System.out.println("#server Socket pronto");
            while (true) {
                executor.submit(new SocketHandler(serverSocket.accept(), proxyController));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}