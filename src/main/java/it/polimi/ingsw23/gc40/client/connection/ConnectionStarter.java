package it.polimi.ingsw23.gc40.client.connection;


import it.polimi.ingsw23.gc40.server.connection.ConnectionController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.net.*;
public class ConnectionStarter {

    public ConnectionStarter() {


    }

    public conSocket socket() {

        try {
            Socket personalSocket = new Socket(InetAddress.getLocalHost(), 6789);
            System.out.println("socketConnected");
            DataInputStream in = new DataInputStream(personalSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(personalSocket.getOutputStream());
        }catch (UnknownHostException e){
            System.err.println("Unknown host");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    public static ConnectionController rmi(String name, String host) throws Exception{
        Registry registry = LocateRegistry.getRegistry(host);
        System.out.println("Connesso");
        ConnectionController fac = ((ConnectionController) registry.lookup(name));
        return fac;
    }
}
