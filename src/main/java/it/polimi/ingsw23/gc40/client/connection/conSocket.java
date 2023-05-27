package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;

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
    private Object obj;
    private View view;



    @Override
    public Object getObj(String s, int n){
        return null;
    }

    public conSocket(Socket socket) {
        //System.out.println("inizio costruttore consocket");
        this.socket = socket;
        try {
            System.out.println("ok");
            ois = new ObjectInputStream(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            serverPrint("#nuova connessione Socket");
        } catch (IOException e) {
            System.out.println("catch: " + e);
            throw new RuntimeException(e);
        }
        this.value = "#";

        //System.out.println("prima di entrare in run consocket");

        start();

        //System.out.println("fine costruttore consocket");
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
        outprint(name);
        while (this.value.equals("#")) {
            System.out.print("");
            //System.out.print("broh");

        }
        int number;
        number = (int) obj;
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
    public void setView(View view) throws RemoteException {
        this.view = view;
    }

    //send string to socket server; return true or false depending on server answer
    private boolean check(String string) {
        outprint(string);
        try {
            while (this.value.equals("#")) {
                System.out.print("");
                //System.out.println("while dentro check");
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
            this.value = "#";
        } catch (NoSuchElementException e) {
            System.out.println("#catch del while-true socket lato client");
            e.printStackTrace();
        }
        System.out.println("##errore addPlayer conSocket");
        return false;
    }

    private void outprint(String s) {
        //System.out.println("entro in outprint consocket");
        out.println(s);
        out.flush();
        System.out.println("@consocket: Ho inviato: "+s);

    }

    @Override
    public void run() {
        //System.out.println("entro nella run conSocket");

        try {
            while (true) {

                //System.out.println("entro nel while run consocket");
                String line = ois.readUTF();
                System.out.println("@@consocket: ricevo: "+line);
                switch (line) {
                    case "#private_object":
                        System.out.println("ho letto private object");
                        obj = ois.readObject();
                        System.out.println("ho letto oggetto");
                        view.updatePrivateData((PrivateModelNetwork) obj);
                        break;
                    case "#public_object":
                        obj = ois.readObject();
                        view.updatePublicData((PublicModelNetwork) obj);
                        break;
                    case "#int":
                        obj = ois.readInt();
                        this.value = "#int";
                        break;
                    default:
                        if (line.equals("#quit")) {
                            obj = ois.readObject();
                            view.updatePublicData((PublicModelNetwork) obj);
                            socket.close();
                            break;
                        }
                        this.value = line;
                        break;
                }

                //aggiorna dati publici solo se il match è giusto
                //todo: printare aggiornamenti commonGoalCard


            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }




    }
}


