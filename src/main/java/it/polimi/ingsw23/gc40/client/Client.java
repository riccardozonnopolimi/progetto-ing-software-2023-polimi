package it.polimi.ingsw23.gc40.client;

import it.polimi.ingsw23.gc40.client.connection.Connection;
import it.polimi.ingsw23.gc40.client.connection.RMI;
import it.polimi.ingsw23.gc40.client.connection.Socket;
import it.polimi.ingsw23.gc40.client.factory.Factory;
import it.polimi.ingsw23.gc40.client.factory.RMIFactory;
import it.polimi.ingsw23.gc40.client.view.CLI;
import it.polimi.ingsw23.gc40.client.view.GUI;
import it.polimi.ingsw23.gc40.client.view.View;

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

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Connection con;
        switch (getChoice(new String[]{"RMI", "Socket"}, "Scegliere connessione:")) {
            case 0:
                //rmi
                Factory fac = new RMIFactory();
                con = fac.con();
                break;
            default:
                //socket
                con = new Socket();
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
    }
}
