package it.polimi.ingsw23.gc40.server.connection;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Host {
    Controller controller;

    public Host(Controller controller) {
        this.controller = controller;

    }

    public void rmiInit() throws RemoteException {
        System.out.println("#metodo rmiInit del server");
        try {

            ConnectionController stub = (ConnectionController) UnicastRemoteObject.exportObject(controller, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Server", stub);
            System.out.println("#server rmi pronto");

        } catch (Exception e) {
            System.out.println("catch");
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }

    //imposti server socket e conseguenze
    public void socketInit() throws IOException {
        //creating threads for socket management
        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            ServerSocket serverSocket = new ServerSocket(6789);
            System.out.println("#server Socket pronto");

            /*
            DataInputStream in = new DataInputStream(socketClient.getInputStream());
            DataOutputStream out = new DataOutputStream(socketClient.getOutputStream());
            String line;
            */

            while (true) {
                Socket socketClient = serverSocket.accept();
                executor.submit(new SocketHandler(socketClient,controller));
                    /*
                    line = in.readUTF();
                    System.out.println("line received: "+line);
                    out.writeUTF("line received: "+line);
                    out.flush();
                     */
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}