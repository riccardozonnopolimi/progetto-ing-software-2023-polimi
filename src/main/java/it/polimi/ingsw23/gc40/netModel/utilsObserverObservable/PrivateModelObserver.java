package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.connection.SocketHandler;
import it.polimi.ingsw23.gc40.server.model.*;

import java.io.*;
import java.util.Observable;
import java.util.Observer;

public class PrivateModelObserver implements Observer {
    private PrivateModelNetwork data;
    public ItemTile[][] shelf;
    public int pgc = -1;
    public String nickname;
    public ObjectOutputStream out;
    private SocketHandler sh;
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
        try {
            if (sh != null)
                sh.sendObj("#private_object",data);
            else
                setNewData(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public SocketHandler getSH(){
        return sh;
    }
    public PrivateModelObserver(Player p) {
        p.addObserver(this);
        this.sh=null;
    }
    public void setSh(SocketHandler sh) {
        this.sh = sh;
    }
    public PrivateModelNetwork getData() {
        return data;
    }
    public void setNewData(boolean hasNewData) {
        this.hasNewData = hasNewData;
    }
    public boolean getNewData(){
        return hasNewData;
    }
}