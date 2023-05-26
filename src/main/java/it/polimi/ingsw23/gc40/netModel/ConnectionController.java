package it.polimi.ingsw23.gc40.netModel;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;


//copia dei metodi dell'interfaccia nel client
public interface ConnectionController extends Remote {


    boolean addPlayer(String name) throws RemoteException;

    boolean hasQueueMatch() throws RemoteException;

    boolean newMatch(String string) throws RemoteException;

    int addToLastMatch(String string) throws RemoteException;

    boolean playable() throws RemoteException;

    void close() throws RemoteException;

    void print(String s) throws RemoteException;

    ObjectInputStream getOIS() throws RemoteException;
}
