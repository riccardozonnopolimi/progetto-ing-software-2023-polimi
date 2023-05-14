package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.controller.GameController;
import it.polimi.ingsw23.gc40.server.model.Bookshelf;
import it.polimi.ingsw23.gc40.server.model.ItemTile;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;

public class PublicModelObservable extends Observable implements Observer {
    private PublicModelNetwork data;
    public ItemTile[][] lrb;
    public int[] cgs;
    public int idMatch;
    public Boolean ended;
    public String winner;
    public String current;

    public PublicModelObservable(GameController gameController){
        gameController.addObserver(this);
        // todo implement remove listener or destructor
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ItemTile[][]) {
            lrb = (ItemTile[][])  arg;
            data = new PublicModelNetwork(lrb,cgs,idMatch,current);
        }
        if (arg instanceof int[]) {
            cgs = (int[]) arg;
            data = new PublicModelNetwork(lrb,cgs,idMatch,current);
        }
        if (arg instanceof Integer){
            idMatch = (Integer) arg;
            data = new PublicModelNetwork(lrb,cgs,idMatch,current);
        }
        if (arg instanceof String){
            winner = (String) arg;
            current = (String) arg;
            data = new PublicModelNetwork(lrb,cgs,idMatch,current);
        }
        if (arg instanceof Boolean){
            ended = (Boolean) arg;
            data = new PublicModelNetwork(ended,winner);
        }

        try {
            serialize(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //todo capire se servono o usare un thread
        // che deserializza di continuo sul client
        // e non serve che questa sia observable
        setChanged();
        notifyObservers(data);
    }
    public void serialize(Object obj) throws IOException {
        System.out.println("entro");
        FileOutputStream fileOut = new FileOutputStream("PublicModelNetwork");
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
