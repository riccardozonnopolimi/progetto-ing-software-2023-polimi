package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.connection.SocketHandler;
import it.polimi.ingsw23.gc40.server.model.*;

import java.io.*;
import java.util.Observable;
import java.util.Observer;

public class PrivateModelObservable extends Observable implements Observer {
    private PrivateModelNetwork data;
    public ItemTile[][] shelf;
    public int pgc;
    public String nickname;
    public ObjectOutputStream out;
    private SocketHandler sh;

    public SocketHandler getSH(){
        return sh;
    }

    public PrivateModelObservable(Player p) {
        p.addObserver(this);
        this.sh=null;
    }

    public void setSh(SocketHandler sh) {
        this.sh = sh;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ItemTile[][]) {
            shelf = (ItemTile[][]) arg;
            data = new PrivateModelNetwork(shelf, pgc, nickname);
        }
        if (arg instanceof PersonalGoalCard) {
            pgc = (int) arg;
            data = new PrivateModelNetwork(shelf, pgc, nickname);
        }
        if (arg instanceof String) {
            nickname = (String) arg;
            data = new PrivateModelNetwork(shelf, pgc, nickname);
        }
        try {
            if (sh != null)
                sh.sendObj("#private_object",data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //non so se funziona cosi
        //setChanged();
        //notifyObservers(data);

    }

    public PrivateModelNetwork getData() {
        return data;
    }
}