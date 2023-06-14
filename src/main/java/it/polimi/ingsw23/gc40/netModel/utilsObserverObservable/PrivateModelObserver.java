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
    private ItemTile[][] shelf;
    public int pgc = -1;
    private String nickname;
    private int[] scores;
    private transient ObjectOutputStream out;
    private transient SocketHandler sh;
    private boolean hasNewData;

    public PrivateModelObserver(Player p) {
        p.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ItemTile[][]) {
            shelf = (ItemTile[][]) arg;
            data = new PrivateModelNetwork(shelf, pgc, nickname, scores);
        }
        if (arg instanceof Integer) {
            pgc = (int) arg;
            data = new PrivateModelNetwork(shelf, pgc, nickname, scores);
        }
        if (arg instanceof String) {
            nickname = (String) arg;
            data = new PrivateModelNetwork(shelf, pgc, nickname, scores);
        }
        if (arg instanceof int[]) {
            scores = (int[]) arg;
        }
        setChanged();
        notifyObservers(data);
    }


    @Override
    public void addObserver(Observer o) {

        super.addObserver(o);
        setChanged();
        notifyObservers(data);

    }


/*    public PrivateModelNetwork getData() {
        return data;
    }*/


/*   public void newObserver(Observer o){
       addObserver(o);
       setChanged();
       notifyObservers(data);
   }*/
}