package it.polimi.ingsw23.gc40.client.connection;


import it.polimi.ingsw23.gc40.netModel.ConnectionController;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.net.*;
import java.util.Scanner;

public class ConnectionStarter {

    public ConnectionStarter() {


    }

    public Socket socketStart(int port) {
        Socket socket = null;
        try {
           socket  = new Socket("localhost", port);
            System.out.println("#Connessione Socket riuscita - porta: "+port);
        } catch (UnknownHostException e) {
            System.err.println("Unknown host");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("arrivo qui");
        return socket;
    }


    public static ConnectionController rmi(String name, String host) throws Exception{
        Registry registry = LocateRegistry.getRegistry(host);
        ConnectionController fac = ((ConnectionController) registry.lookup(name));
        System.out.println("#Connessione RMI riuscita");
        return fac;
    }
}
