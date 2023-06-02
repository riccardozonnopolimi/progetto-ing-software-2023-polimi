package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
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

        //sostituire con wait todo
        while (this.value.equals("#")) {
            System.out.print("");
        }
        int number;
        number = (int) temp_obj;
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

        //sostituire con wait todo
        while (temp_obj == null) {
            System.out.print("");
        }
        ItemTile[] ht = (ItemTile[]) temp_obj;
        temp_obj = null;
        return ht;
    }

    @Override
    public int getAvailableSlots(String nickname, int column) throws RemoteException {
        outprint("#getAvailableSlots");
        outprint(nickname);
        outprint(column);

        //sostituire con wait todo
        while (this.value.equals("#")) {
            System.out.print("");
        }
        int number;
        number = (int) temp_obj;
        temp_obj = null;
        this.value = "#";
        return number;
    }

    public void outobject(Object obj){
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
    public Object getObj(String s, int n) {
        return null;
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
    public boolean addPlayer(String name) {
        outprint("#addPlayer");
        return check(name);
    }

    @Override
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        //sostituire con wait todo
        while (this.value.equals("#")) {
            System.out.print("");
        }
        int number;
        number = (int) temp_obj;
        temp_obj = null;
        this.value = "#";
        return number;
    }

    @Override
    public boolean hasQueueMatch() {
        return check("#hasQueueMatch");
    }

    @Override
    public boolean playable(int n) throws RemoteException {
        outprint("#playable");
        return check(n);
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
            //sostituire con wait todo
            while (this.value.equals("#")) {
                System.out.print("");
                //System.out.println("while dentro check");
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
            //sostituire con wait todo
            while (this.value.equals("#")) {
                System.out.print("");
                //System.out.println("while dentro check");
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
            while (true) {
                String line = ois.readUTF();
                switch (line) {
                    case "#private_object":
                        obj = ois.readObject();
                        view.updatePrivateData((PrivateModelNetwork) obj);
                        obj = null;
                        break;
                    case "#handTiles":
                        temp_obj = ois.readObject();
                        break;
                    case "#public_object":
                        obj = ois.readObject();
                        view.updatePublicData((PublicModelNetwork) obj);
                        obj = null;
                        break;
                    case "#int":
                        temp_obj = ois.readInt();
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