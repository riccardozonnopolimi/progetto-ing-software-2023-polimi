package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class conSocket extends Thread implements ConnectionController  {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    public String value;

    public conSocket(Socket socket) {
        this.socket = socket;
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            serverPrint("#nuova connessione Socket");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.value = "#";
        this.start();
    }

    @Override
    public boolean addPlayer(String name) {
        //out corrisponde al serversocket in ascolto
        outprint("#addPlayer");
        return check(name);
    }


    @Override
    public void close() {
        in.close();
        out.close();
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //@Override
    public void serverPrint(String s) throws RemoteException {
        outprint("#serverPrint");
        outprint(s);
    }

    @Override
    public boolean newMatch(String string) {
        outprint("#newMatch");
        return check(string);
    }

    @Override
    public boolean addToLastMatch(String name) throws RemoteException {
        outprint("#addToLastMatch");

        return check(name);
    }

    @Override
    public boolean hasQueueMatch() {
        return check("#hasQueueMatch");
    }

    @Override
    public boolean playable() throws RemoteException {
        return check("#playable");
    }

    @Override
    public void print(String s) throws RemoteException {
        //todo
    }

    //send string to socket server; return true or false depending on server answer
    private boolean check(String string) {
        outprint(string);
        try {

            while (this.value.equals("#")) {
                this.value = "#";
                System.out.print("");
            }
            System.out.println("");
            if (this.value.equals("#false")) {
                this.value = "#";
                return false;
            }
            if (this.value.equals("#true")) {
                this.value = "#";
                return true;
            }
        } catch (NoSuchElementException e) {
            System.out.println("#catch del while-true socket lato client");
            e.printStackTrace();
        }
        System.out.println("##errore addPlayer conSocket");
        return false;
    }

    private void outprint(String s) {
        out.println(s);
        out.flush();
    }

    public void run() {
        try {
            
            while (true) {
                String line = in.nextLine();

                if (line.equals("#quit")) break;
                this.value = line;
            }
            in.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("#runSocket class - run method - catch error:");
            e.printStackTrace();
        }
    }
}
