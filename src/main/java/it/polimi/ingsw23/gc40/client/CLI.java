package it.polimi.ingsw23.gc40.client;

import it.polimi.ingsw23.gc40.server.controller.Match;
import it.polimi.ingsw23.gc40.server.controller.MatchRMI;
import it.polimi.ingsw23.gc40.server.controller.Server;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class CLI extends UnicastRemoteObject implements Client, Observer {
    static int port;
    private static Player p;

    protected CLI(int port) throws RemoteException {
      //  super(port);

    }

    @Override
    public void update(Observable o, Object arg) {
        //aggiornamento view
    }

    public void rmi() {

        //DEBUGGING TEST
        System.out.println("### clirmi--ok");


        Scanner inp = new Scanner(System.in);
        MatchRMI m = null;
        String nickname;
        // creare player con nick tramite server

        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            System.out.println("bella");
            Server stub = (Server) registry.lookup("Server");
            stub.print("ciao");




            do {
                System.out.println("Insert nickname");

                nickname = inp.nextLine();


                if (stub.checkExistingNickName(nickname))
                    System.out.println("Nickname already used");
            } while (stub.checkExistingNickName(nickname));

            stub.addPlayer(nickname);


            p = stub.getPlayer(nickname);

            if (stub.hasQueueMatch()) {
                //cugola says this
                stub.addToLastMatch(p);
                System.out.println("arrivo qui");
            }
            else {
                int i;
                System.out.println("How many Players in this new Match?");

                i = inp.nextInt();
                while(i<2 || i>4){
                    System.out.println("The match can have from 2 to 4 players, try again: ");
                    i = inp.nextInt();

                }
                m = stub.createNewMatch(i);
                System.out.println(p.getNickName());
                stub.addToLastMatch(p);
            }

        m.connnection();

            System.out.println("1");
            Match stubMatch = (Match) registry.lookup("Match");
            System.out.println("2");


            //stubMatch.addObserver(this);


        } catch (Exception e) {
            System.out.println("errore cli-rmi : "+e);
            e.printStackTrace();

        }
    }
}
