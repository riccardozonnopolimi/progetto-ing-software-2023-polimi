package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.connection.SocketHandler;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;

public class PrivateModelObserver extends Observable implements Observer {
    private static final long serialVersionUID = 7829136991241571165L;
    private PrivateModelNetwork data;
    private ItemTile[][] shelf;
    public int pgc = -1;
    private String nickname;
    private int[] scores;
    private  ObjectOutputStream out;
    private  SocketHandler sh;
    private boolean hasNewData;

    /**
     * PrivateModelObserver constructor. It sets this as player observer
     * @param p player
     */
    public PrivateModelObserver(Player p) {
        p.addObserver(this);
    }

    /**
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     *            method.
     */
    @Override
    public void update(Observable o, Object arg) {
        //updating bookshelf
        if (arg instanceof ItemTile[][]) {
            shelf = (ItemTile[][]) arg;
            data = new PrivateModelNetwork(shelf, pgc, nickname, scores);
        }
        //updating personal goal card
        if (arg instanceof Integer) {
            pgc = (int) arg;
            data = new PrivateModelNetwork(shelf, pgc, nickname, scores);
        }
        //updating nickname
        if (arg instanceof String) {
            nickname = (String) arg;
            data = new PrivateModelNetwork(shelf, pgc, nickname, scores);
        }
        //updating player scores
        if (arg instanceof int[]) {
            scores = (int[]) arg;
        }
        setChanged();
        notifyObservers(data);
    }


    /**
     * Notifies data (Private Model Network)
     * @param o an observer to be added.
     */
    @Override
    public void addObserver(Observer o) {

        super.addObserver(o);
        setChanged();
        notifyObservers(data);

    }

//todo cancellare?

    /*    public PrivateModelNetwork getData() {
        return data;
    }*/


/*   public void newObserver(Observer o){
       addObserver(o);
       setChanged();
       notifyObservers(data);
   }*/
}