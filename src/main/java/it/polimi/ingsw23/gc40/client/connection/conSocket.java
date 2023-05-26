package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.sql.SQLOutput;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class conSocket extends Thread implements ConnectionController {
    private Socket socket;
    private PrintWriter out;
    public String value;
    private ObjectInputStream ois;


    public conSocket(Socket socket) {
        System.out.println("inizio costruttore consocket");
        this.socket = socket;
        try {
            System.out.println("ok");
            ois = new ObjectInputStream(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            serverPrint("#nuova connessione Socket");
        } catch (IOException e) {
            System.out.println("catch: "+e);
            throw new RuntimeException(e);
        }
        this.value = "#";

        System.out.println("prima di entrare in run consocket");

        this.start();

        System.out.println("fine costruttore consocket");
    }

    @Override
    public boolean addPlayer(String name) {
        //out corrisponde al serversocket in ascolto
        outprint("#addPlayer");
        return check(name);
    }

    @Override
    public void close() {
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
    public int addToLastMatch(String name) throws RemoteException {
        outprint("#addToLastMatch");
        while (this.value.equals("#")) {
            this.value = "#";
            System.out.print("");
        }
        int number;
        number = Integer.parseInt(this.value);
        this.value = "#";
        return number;
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
        //todo implementation
    }


    @Override
    public ObjectInputStream getOIS() throws RemoteException {
        return ois;
    }

    //send string to socket server; return true or false depending on server answer
    private boolean check(String string) {
        outprint(string);
        try {
            while (this.value.equals("#")) {
                this.value = "#";
                System.out.print("");
                System.out.println("while dentro check\n");

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
        System.out.println("entro in outprint consocket");
        out.println(s);
        out.flush();
    }

    @Override
    public void run() {
        System.out.println("entro nella run conSocket");

        try {
            while (true) {
                System.out.println("entro nel while run consocket");
                String line = ois.readLine();

                if (line.equals("#quit")) break;


                this.value = line;


            }
            socket.close();
        } catch (IOException e) {
            System.out.println("#runSocket class - run method - catch error:");
            e.printStackTrace();
        }
    }

}
