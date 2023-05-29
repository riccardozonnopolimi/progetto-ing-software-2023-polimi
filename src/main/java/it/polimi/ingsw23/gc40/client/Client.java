package it.polimi.ingsw23.gc40.client;

import it.polimi.ingsw23.gc40.client.connection.ConnectionStarter;
import it.polimi.ingsw23.gc40.client.connection.conRMI;
import it.polimi.ingsw23.gc40.client.connection.conSocket;
import it.polimi.ingsw23.gc40.client.view.CLI;
import it.polimi.ingsw23.gc40.client.view.GUI;
import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {


    public static void main(String[] args) throws RemoteException, NotBoundException, Exception {
        ConnectionController con;
        ConnectionStarter starter =new ConnectionStarter();
        View view;
        if (CLI.getChoice(new String[]{"RMI", "Socket"}, "Scegliere connessione:") == 0) {
            //rmi
            con = new conRMI(ConnectionStarter.rmi("Server", "localhost"));
        } else {
            //socket
            con = new conSocket(starter.socketStart(6789));
        }
        System.out.println("fine scelta connessione client");
        if (CLI.getChoice(new String[]{"CLI", "GUI"}, "Scegliere interfaccia di gioco:") == 0) {
            //CLI
            view = new CLI(con);
            con.setView(view);
        } else {
            //GUI
            view = new GUI(con);
        }
        view.init();
        }
    }