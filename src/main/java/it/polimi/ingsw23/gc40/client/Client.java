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



        System.out.println("");


        //LET USER CHOICE IP SERVER
        System.out.println("Write the target (ip address or domain) of the server: "+CLI.green+"(You can press enter for localhost)"+CLI.resetStdColor);
        Scanner scanner = new Scanner(System.in);
        String host = scanner.nextLine();

        if (host.equals(""))
            host="localhost";

        System.out.println(CLI.green +"You chosen the target:"+CLI.resetStdColor +" "+host);
        System.out.println("");

        ConnectionStarter starter = new ConnectionStarter();

        View view;
        if (CLI.getChoice(new String[]{"RMI (Remote Method Invocation)", "Socket"}, "Choose a type of connection method: (if you don't know, is the same)") == 0) {
            //rmi
            con = new conRMI(starter.rmi("Server", host));
        } else {
            //socket
            con = new conSocket(starter.socketStart(6789, host));
        }
        System.out.println("");
        if (CLI.getChoice(new String[]{"CLI (Command Line Interface)", "GUI (Graphic User Interface)"}, "Choose the game interface:") == 0) {
            //CLI
            view = new CLI(con);
        } else {
            //GUI
            view = new GUI(con);
        }

        con.setView(view);
        view.init();
    }
}