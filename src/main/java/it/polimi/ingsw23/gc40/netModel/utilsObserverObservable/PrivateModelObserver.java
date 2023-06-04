package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.connection.SocketHandler;
import it.polimi.ingsw23.gc40.server.model.*;

import java.io.*;
import java.rmi.Remote;
import java.util.Observable;
import java.util.Observer;

public class PrivateModelObserver extends Observable implements Observer, Serializable, Remote {
    private static final long serialVersionUID = 7829136991241571165L;
    private PrivateModelNetwork data;
    public ItemTile[][] shelf;
    public int pgc = -1;
    public String nickname;
    public transient ObjectOutputStream out;
    private transient SocketHandler sh;
    private boolean hasNewData;

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ItemTile[][]) {
            shelf = (ItemTile[][]) arg;
            data = new PrivateModelNetwork(shelf, pgc, nickname);
        }
        if (arg instanceof Integer) {
            pgc = (int) arg;
            data = new PrivateModelNetwork(shelf, pgc, nickname);
        }
        if (arg instanceof String) {
            nickname = (String) arg;
            data = new PrivateModelNetwork(shelf, pgc, nickname);
        }
        setChanged();
        notifyObservers(data);
        System.out.println("server modifica dati correttamente");
       /* try {
            if (sh != null) sh.sendObj("#private_object", data);
            else setNewData(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }

    /*public SocketHandler getSH() {
        return sh;
    }*/

    public PrivateModelObserver(Player p) {
        p.addObserver(this);
        this.sh = null;
    }

    /*public void setSh(SocketHandler sh) {
        this.sh = sh;
    }*/

    public PrivateModelNetwork getData() {
        return data;
    }

   /* public void setNewData(boolean hasNewData) {
        this.hasNewData = hasNewData;
    }

    public boolean getNewData() {
        return hasNewData;
    }*/
}