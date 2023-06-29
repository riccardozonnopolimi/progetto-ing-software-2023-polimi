package it.polimi.ingsw23.gc40.client.connection;


import it.polimi.ingsw23.gc40.client.view.CLI;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.*;


public class ConnectionStarter {
    /**
     * Connection Starter constructor
     */
    public ConnectionStarter() {
    }

    /**
     * This method starts connection between client and server after choosing Socket connection
     * @param port port
     * @param host hostname
     * @return created socket from client to server
     */
    //creating socket and trying to connect with serverSocket
    public Socket socketStart(int port, String host) {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            System.out.println(CLI.green +"Socket connection started successfully on port: " + port+CLI.resetStdColor);
        } catch (UnknownHostException e) {
            System.err.println("Unknown host");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return socket;
    }

    /**
     * This method starts connection between client and server after choosing RMI connection
     * @param name nickname
     * @param host hostname
     * @return the connection controller at which the player is connected
     * @throws Exception if remote communication fails (RemoteException),
     * if name is not currently bound (NotBoundException)
     *  if this registry is local and it denies the caller access to perform this operation (AccessException)
     *  if name is null (NullPointerException)
     */
    public ConnectionController rmi(String name, String host) throws Exception {
        Registry registry = LocateRegistry.getRegistry(host);
        ConnectionController stub = ((ConnectionController) registry.lookup(name));
        System.out.println(CLI.green +"RMI connection started successfully"+CLI.resetStdColor);
        return stub;
    }
}
