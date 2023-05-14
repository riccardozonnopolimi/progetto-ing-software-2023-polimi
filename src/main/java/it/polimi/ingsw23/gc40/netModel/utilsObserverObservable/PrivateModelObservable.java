package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.model.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;

public class PrivateModelObservable extends Observable implements Observer {
    private PrivateModelNetwork data;
    public ItemTile[][] shelf;
    public int pgc;
    public String nickname;
      public PrivateModelObservable(Player p) {
        p.addObserver(this);
        // todo implement remove listener or destructor
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ItemTile[][]) {
            shelf = (ItemTile[][]) arg;
            data = new PrivateModelNetwork(shelf,pgc,nickname);
        }
        if (arg instanceof PersonalGoalCard) {
            pgc = (int) arg;
            data = new PrivateModelNetwork(shelf, pgc,nickname);
        }
        if (arg instanceof String){
            nickname = (String) arg;
            data = new PrivateModelNetwork(shelf,pgc,nickname);
        }
            try {
                serialize(data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        setChanged();
        notifyObservers(data);
        //todo capire se servono o usare un thread
        // che deserializza di continuo sul client
        // e non serve che questa sia observable
    }

    public void serialize(Object obj) throws IOException {
        System.out.println("entro");
        FileOutputStream fileOut = new FileOutputStream("PrivateModelNetwork");
        System.out.println("file ok");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        System.out.println("obj ok");
        out.writeObject(obj);
        out.flush();
        System.out.println("obj inviato");
        out.close();
        fileOut.close();

    }
}