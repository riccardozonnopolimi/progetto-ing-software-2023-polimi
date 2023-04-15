package it.polimi.ingsw23.gc40.client;

import it.polimi.ingsw23.gc40.server.controller.Server;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ClientMainRMI extends UnicastRemoteObject implements Client {
    static int port;
    private static Player p;

    protected ClientMainRMI(int port) throws RemoteException {
        super(port);
    }

    public static void main(String args) {
        // creare player con nick tramite server

        try {
            Registry registry = LocateRegistry.getRegistry(port);
            Server stub = (Server) registry.lookup("Server");
            Scanner inp = new Scanner(System.in);
            String nickname;

            do {
                System.out.println("Insert nickname");
                nickname = inp.nextLine();
                if (stub.checkExistingNickName(nickname))
                    System.out.println("Nickname already used");
            } while (stub.checkExistingNickName(nickname));
            stub.addPlayer(nickname);
            p = stub.getPlayer(nickname);
            if (stub.hasQueueMatch())
                stub.addToLastMatch(p);
            else {
                int i;
                System.out.println("How many Players in this new Match?");
                i = inp.nextInt();
                stub.createNewMatch(i);
                stub.addToLastMatch(p);
            }


        } catch (Exception e) {

        }
    }
}
