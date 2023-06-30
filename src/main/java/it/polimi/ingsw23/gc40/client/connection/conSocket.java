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
    private final Socket socket;
    private final ObjectOutputStream out;
    public String value;
    private final ObjectInputStream ois;
    private Object obj;
    private Object temp_obj;
    private View view;


    /**
     * @param name player nickname who is going to start the round
     * @param mID at which the player belongs
     * @return the max number of item tiles which the player can take by checking empty slots in its bookshelf
     */
    @Override
    public int startRound(String name, int mID) {
        outprint("#startRound");
        outprint(name);
        outprint(mID);

        //waiting until recive a command
        while (this.value.equals("#")) {
            try {
                Thread.sleep(100);
                //System.out.print("...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();

        int number = (int) temp_obj;
        temp_obj = null;
        this.value = "#";
        return number;
    }

    /**
     * @param nickname player nickname who can make the move
     * @param mid match id of that player
     * @param x axis in map of the tile chosen
     * @param y allies in map of the tile chosen
     * @param quantity the number of tiles the player decided to take
     * @param direction the direction of the tile to take
     * @return item tile array with the tiles chosen
     * @throws RemoteException if remote communication fails
     */
    @Override
    public ItemTile[] chooseTiles(String nickname, int mid, int x, int y, int quantity, char direction) throws RemoteException {

        outprint("#chooseTiles");
        outprint(x);
        outprint(y);
        outprint(quantity);
        outprint(direction);


        while (temp_obj == null) {
            try {
                Thread.sleep(100);
                //System.out.print("...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();

        ItemTile[] ht = (ItemTile[]) temp_obj;
        temp_obj = null;
        return ht;
    }

    /**
     * @param nickname player nickname
     * @param column bookshelf column
     * @return the number of empty slots player bookshelf column has
     * @throws RemoteException if remote communication fails
     */
    @Override
    public int getAvailableSlots(String nickname, int column) throws RemoteException {
        outprint("#getAvailableSlots");
        outprint(nickname);
        outprint(column);

        while (this.value.equals("#")) {
            try {
                Thread.sleep(100);
                //System.out.print("...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();

        int number = (int) temp_obj;
        temp_obj = null;
        this.value = "#";
        return number;
    }

    /**
     * @param obj object sent in a socketmessage
     */
    public void outobject(Object obj) {
        try {
            out.writeObject(obj);
            out.flush();
            out.reset();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @param nickname player nickname
     * @param handTile tile taken from the lrb
     * @param column player decides to put handtile
     * @return true after calling the method in model that insert handtile in bookshelf
     * @throws RemoteException if remote communication fails
     */
    @Override
    public boolean insertTile(String nickname, ItemTile handTile, int column) throws RemoteException {
        outprint("#insertTile");
        outobject(handTile);
        return check(column);
    }

    /**
     *updates player score, refills lrb if needed,checks if the match is completed,
     * sets the next player round
     * @param nickname player nickname
     * @param matchId id match
     * @throws RemoteException if remote communication fails
     */
    @Override
    public void nextRound(String nickname, int matchId) throws RemoteException {
        outprint("#nextRound");
        outprint(nickname);
        outprint(matchId);
    }

    /**
     * @param aRemoteObj Remote objects sent in cnnection
     * @param s player nickname
     * @param n matchid
     * @throws RemoteException if remote communication fails
     */
    @Override
    public void addObserver(Remote aRemoteObj, String s, int n) throws RemoteException {
        outprint("#addObserver");
    }

    /**
     * @param nickname player nickname
     * @return match id in which the player that crashed will be reconnected , -1 if it's a new player
     * @throws RemoteException if remote communication fails
     */
    @Override
    public int reconnect(String nickname) throws RemoteException {
        outprint("#reconnect");
        outprint(nickname);

        while (this.value.equals("#")) {
            try {
                Thread.sleep(100);
                //System.out.print("...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
        int number;
        number = (int) temp_obj;
        temp_obj = null;
        this.value = "#";
        return number;
    }


    /**
     * conSocket constructor. It takes a client socket and starts the connection with the server
     * @param socket
     */
    public conSocket(Socket socket) {
        this.socket = socket;
        try {
            ois = new ObjectInputStream(socket.getInputStream());

            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
        } catch (IOException e) {
            System.out.println("catch: " + e);
            throw new RuntimeException(e);
        }
        this.value = "#";
        this.temp_obj = null;


        start();

    }

    /**
     * @param name nickname written by the client
     * @return true if there is no other player connected with that nickname
     */
    @Override
    public boolean connect(String name) {
        outprint("#connect");
        return check(name);
    }

    /**
     * closes player connection and deletes it from observers
     * @param nickname player nickname
     */
    @Override
    public void close(String nickname) {
        outprint("#close");
        outprint(nickname);
    }

    /**
     * @param n number of players chosen in match
     * @return true if the match has successfully been created
     */
    @Override
    public boolean newMatch(int n) {
        outprint("#newMatch");
        return check(n);

    }

    /**
     * @param name player nickname added to the match
     * @return match id where name is added
     * @throws RemoteException if remote communication fails
     */
    @Override
    public int addToLastMatch(String name) throws RemoteException {
        outprint("#addToLastMatch");
        outprint(name);

        while (this.value.equals("#")) {
            try {
                Thread.sleep(100);
                //System.out.print("...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
        int number = (int) temp_obj;
        temp_obj = null;
        this.value = "#";
        return number;
    }

    /**
     * @return true if there is one match which is filling that is not started yet
     */
    @Override
    public boolean hasQueueMatch() {
        return check("#hasQueueMatch");
    }


    /**
     * @param view client view
     * @throws RemoteException if remote communication fails
     */
    @Override
    public void setView(View view) throws RemoteException {
        this.view = view;
    }

    /**
     * send string to server and wait the server response
     * @param string
     * @return return true or false depending on server answer
     */
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

    /**
     * send int to socket server
     * @param n
     * @return return true or false depending on server answer
     */
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

    /**
     * @param s print string to server from client
     */
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

    /**
     * @param s print to server int from client
     */
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

    /**
     * @param c print to server char from client
     */
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

    /**
     * a new Thread run when a socket client connection starts
     */
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