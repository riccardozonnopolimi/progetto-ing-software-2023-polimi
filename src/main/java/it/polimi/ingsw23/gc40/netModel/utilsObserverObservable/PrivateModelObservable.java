package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.model.*;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;

public class PrivateModelObservable extends Observable implements Observer {
    private PrivateModelNetwork data;
    public ItemTile[][] shelf;
    public int pgc;

    public PrivateModelObservable(Player p){

        p.addObserver(this);




        // TODO implement removelistener on destructor
    }

    @Override
    public void update(Observable o, Object arg) {
       //this.data.setBookshelf((Bookshelf) arg);
        if(o instanceof Bookshelf){
            shelf = (ItemTile[][]) arg;
            data = new PrivateModelNetwork(pgc,shelf);
            try {
                send(data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(o instanceof PersonalGoalCard){
            pgc = (int) arg;
            data = new PrivateModelNetwork(pgc,shelf);
        }

    }

    public void send(PrivateModelNetwork data) throws Exception{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        out = new ObjectOutputStream(bos);
        out.writeObject(data);
    }
}
