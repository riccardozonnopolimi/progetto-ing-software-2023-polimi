package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.netModel.TileData;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.io.*;
import java.net.*;

public final class FactorySingleton implements ConnectionController {


    public FactorySingleton(){

    }

    public void init() throws RemoteException {
        System.out.println("entra");
        try {

            ConnectionController stub = (ConnectionController) UnicastRemoteObject.exportObject(this, 0);
            System.out.println("2");

            Registry registry = LocateRegistry.createRegistry(1098);
            System.out.println("3");

            registry.bind("Server", stub);
            System.out.println("server pronto");

        } catch (Exception e) {
            System.out.println("catch");
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }
    //imposti server socket e conseguenze
    public void socketInit(){

        try {
            //creating serverSocket
            ServerSocket serverSocket = new ServerSocket(6799);
            System.out.println("serverSocket pronto");

            //creating Socket for single Client
            Socket socketClient = serverSocket.accept();


            DataInputStream in = new DataInputStream(socketClient.getInputStream());
            DataOutputStream out = new DataOutputStream(socketClient.getOutputStream());

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }







    }



    public void addPlayer(String name){
        System.out.println("arrivo qui");
        //todo
    }
    public void extAddPlayer(){
        //todo
    }
    public TileData[] getTile(int x, int y, char direction, int qty){
        return null; //todo
    }
    public void startInserction(int column){
//todo
    }
    public void putTile(int id) {
//todo
    }

}

