package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;


public class conSocket extends Thread implements ConnectionController {
    private Socket socket;
    private ObjectOutputStream out;
    public String value;
    private ObjectInputStream ois;
    private Object obj;
    private Object temp_obj;
    private View view;


    @Override
    public int startRound(String name, int mID) {
        outprint("#startRound");
        outprint(name);
        outprint(mID);

        while (this.value.equals("#")) {
            try {
                Thread.sleep(500);
                System.out.print("...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("");

        int number = (int) temp_obj;
        temp_obj = null;
        this.value = "#";
        return number;
    }

    @Override
    public ItemTile[] chooseTiles(int mid, int x, int y, int quantity, char direction) throws RemoteException {
        outprint("#chooseTiles");
        outprint(x);
        outprint(y);
        outprint(quantity);
        outprint(direction);

        while (temp_obj == null) {
            try {
                Thread.sleep(500);
                System.out.print("...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("");

        ItemTile[] ht = (ItemTile[]) temp_obj;
        temp_obj = null;
        return ht;
    }

    @Override
    public int getAvailableSlots(String nickname, int column) throws RemoteException {
        outprint("#getAvailableSlots");
        outprint(nickname);
        outprint(column);

        while (this.value.equals("#")) {
            try {
                Thread.sleep(500);
                System.out.print("...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("");

        int number = (int) temp_obj;
        temp_obj = null;
        this.value = "#";
        return number;
    }

    public void outobject(Object obj) {
        try {
            out.writeObject(obj);
            out.flush();
            out.reset();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void insertTile(String nickname, ItemTile handTile, int column) throws RemoteException {
        outprint("#insertTile");
        outprint(nickname);
        outobject(handTile);
        outprint(column);
    }

    @Override
    public void nextRound(String nickname, int matchId) throws RemoteException {
        outprint("#nextRound");
        outprint(nickname);
        outprint(matchId);
    }

    @Override
    public void addObserver(Remote aRemoteObj, String s, int n) throws RemoteException {
        outprint("#addObserver");
    }

    @Override
    public int reconnect(String nickname) throws RemoteException {
        outprint("#reconnect");
        outprint(nickname);

        while (this.value.equals("#")) {
            try {
                Thread.sleep(500);
                System.out.print("...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("");
        int number;
        number = (int) temp_obj;
        temp_obj = null;
        this.value = "#";
        return number;
    }


    public conSocket(Socket socket) {
        this.socket = socket;
        try {
            ois = new ObjectInputStream(socket.getInputStream());

            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            serverPrint("#nuova connessione Socket");
        } catch (IOException e) {
            System.out.println("catch: " + e);
            throw new RuntimeException(e);
        }
        this.value = "#";
        this.temp_obj = null;


        start();

    }

    @Override
    public boolean connect(String name) {
        outprint("#connect");
        return check(name);
    }

    @Override
    public void close(String nickname) {
        outprint("#close");
        outprint(nickname);
    }

    @Override
    public boolean newMatch(int n) {
        outprint("#newMatch");
        return check(n);

    }

    @Override
    public int addToLastMatch(String name) throws RemoteException {
        outprint("#addToLastMatch");
        outprint(name);

        while (this.value.equals("#")) {
            try {
                Thread.sleep(500);
                System.out.print("...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("");
        int number = (int) temp_obj;
        temp_obj = null;
        this.value = "#";
        return number;
    }

    @Override
    public boolean hasQueueMatch() {
        return check("#hasQueueMatch");
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
            }
            System.out.print("");
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

    private boolean check(int n) {
        outprint(n);
        try {
            while (this.value.equals("#")) {
                System.out.print("");
            }
            System.out.print("");
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
            System.out.println("#catch di un while-true socket lato client - metodo check");
            e.printStackTrace();
        }
        System.out.println("##errore check conSocket");
        return false;
    }

    private void outprint(String s) {
        try {
            out.writeUTF(s);
        } catch (IOException e) {
            System.out.println("Catch in conSocket outprint");

            throw new RuntimeException(e);
        }
        try {
            out.flush();
        } catch (IOException e) {
            System.out.println("Catch in conSocket outprint");
            throw new RuntimeException(e);
        }

    }

    private void outprint(int s) {
        try {
            out.writeInt(s);
        } catch (IOException e) {
            System.out.println("Catch in conSocket outprint");
            throw new RuntimeException(e);
        }
        try {
            out.flush();
        } catch (IOException e) {
            System.out.println("Catch in conSocket outprint");
            throw new RuntimeException(e);
        }
    }

    private void outprint(char c) {
        try {
            out.writeChar(c);
        } catch (IOException e) {
            System.out.println("Catch in conSocket outprint");
            throw new RuntimeException(e);
        }
        try {
            out.flush();
        } catch (IOException e) {
            System.out.println("Catch in conSocket outprint");
            throw new RuntimeException(e);
        }
    }

    public void serverPrint(String s) throws RemoteException {
        outprint("#serverPrint");
        outprint(s);
    }

    @Override
    public void run() {
        try {
            boolean online = true;
            while (online) {
                String line = ois.readUTF();
                switch (line) {

                    case "#handTiles":
                        temp_obj = ois.readObject();
                        break;
                    case "#update":
                        obj = ois.readObject();
                        if (obj instanceof PublicModelNetwork) {
                            view.updatePublicData((PublicModelNetwork) obj);
                        }
                        if (obj instanceof PrivateModelNetwork)
                            view.updatePrivateData((PrivateModelNetwork) obj);
                        obj = null;
                        break;
                    case "#int":
                        temp_obj = ois.readInt();
                        this.value = "#int";
                        break;
                    case "#quit":

                        socket.close();
                        online = false;
                        break;

                    default:
                        //System.out.println("consocket riceve " + line);
                        this.value = line;
                        break;
                }

            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}