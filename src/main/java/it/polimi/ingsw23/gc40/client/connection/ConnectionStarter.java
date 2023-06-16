package it.polimi.ingsw23.gc40.client.connection;


import it.polimi.ingsw23.gc40.client.view.CLI;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.*;


public class ConnectionStarter {
    public ConnectionStarter() {
    }

    //creating socket and trying to connect with serverSocket
    public Socket socketStart(int port, String host) {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            System.out.println(CLI.green +"#Connessione Socket riuscita - porta: " + port+CLI.reset);
        } catch (UnknownHostException e) {
            System.err.println("Unknown host");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return socket;
    }

    //starting RMI connection
    public static ConnectionController rmi(String name, String host) throws Exception {
        Registry registry = LocateRegistry.getRegistry(host);
        ConnectionController stub = ((ConnectionController) registry.lookup(name));
        System.out.println(CLI.green +"#Connessione RMI riuscita"+CLI.reset);
        return stub;
    }
}
