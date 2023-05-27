package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Scanner;

public class conRMI extends Thread implements ConnectionController {
    ConnectionController stub;
    private View view;

    @Override
    public void setView(View view) throws RemoteException {
        this.view = view;
        start();
    }

    public conRMI(ConnectionController stub) throws RemoteException {
        this.stub = stub;
        print("#nuova connessione RMI");
    }

    @Override
    public boolean addPlayer(String name) throws RemoteException {
        return stub.addPlayer(name);
    }

    public boolean newMatch(String string) throws RemoteException {
        return stub.newMatch(string);
    }


    public boolean hasQueueMatch() throws RemoteException {
        return stub.hasQueueMatch();
    }

    @Override
    public boolean playable() throws RemoteException {
        return stub.playable();
    }

    @Override
    public int addToLastMatch(String name) throws RemoteException {
        return stub.addToLastMatch(name);
    }


    /* /@Override
    public void serverPrint(String s) throws RemoteException {
        stub.print(s);
    }
    */
    @Override
    public void close() throws RemoteException {
        this.stub = null;
    }

    @Override
    public void print(String s) throws RemoteException {
        stub.print(s);
    }


    @Override
    public void run() {


        System.out.println("parte thread conrmi");
        while (true) {
            try {
                if(view!=null){
                    if (view.matchId != -1)
                        view.updatePublicData((PublicModelNetwork) getObj("", view.matchId));
                    if (view.player != "temp")
                        view.updatePublicData((PublicModelNetwork) getObj(view.player, -1));
                }


            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Object getObj(String s, int n) throws RemoteException {
        return stub.getObj(s, n);
    }


}
