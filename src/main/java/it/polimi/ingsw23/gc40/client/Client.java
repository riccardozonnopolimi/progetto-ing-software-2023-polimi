package it.polimi.ingsw23.gc40.client;

import it.polimi.ingsw23.gc40.client.connection.ConnectionStarter;
import it.polimi.ingsw23.gc40.client.connection.conRMI;
import it.polimi.ingsw23.gc40.client.connection.conSocket;
import it.polimi.ingsw23.gc40.client.view.CLI;
import it.polimi.ingsw23.gc40.client.view.GUI;
import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    public static int getChoice(String[] choices, String question) {
        Scanner sc = new Scanner(System.in);
        int n;


        do {
            System.out.println(question);
            for (int i = 0; i < choices.length; i++)
                System.out.println(i + ": " + choices[i]);
            System.out.println("Digitare un numero per effettuare la scelta: ");


            n = 0;
            sc.reset();
            n = sc.nextInt();

            if (n < 0 || n >= choices.length)
                System.out.println("Scelta errata, riprova.");

        } while (n < 0 || n >= choices.length);
        return n;
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, Exception {
        ConnectionController con;
        ConnectionStarter starter;

        switch (getChoice(new String[]{"RMI", "Socket"}, "Scegliere connessione:")) {
            case 0:
                //rmi
                starter = new ConnectionStarter();
                con = new conRMI(starter.rmi("Server","localhost"));
                break;
            default:
                //socket
                starter = new ConnectionStarter();
                con = new conSocket(starter.socketStart());
                break;
        }
        View view;
        switch (getChoice(new String[]{"CLI", "GUI"}, "Scegliere interfaccia di gioco:")) {
            case 0:
                //CLI
                view = new CLI(con);
                break;
            default:
                //GUI
                view = new GUI(con);
                break;
        }

        view.start();

        ItemTile[][] prova = (ItemTile[][]) view.deserializza("bookshelf");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(prova[i][j].toString() + "|");
            }
            System.out.println();

        }








    }
}
