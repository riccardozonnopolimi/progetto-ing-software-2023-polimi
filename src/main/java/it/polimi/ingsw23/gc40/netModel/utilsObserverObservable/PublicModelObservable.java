package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.controller.GameController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
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

    private PrintWriter printer;

    public PublicModelObservable(GameController gameController){
        gameController.addObserver(this);
        // todo implement remove listener or destructor
        Socket s = null;
        try {
            s = new Socket("localhost", 9001);
            printer = new PrintWriter(s.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
        //todo trying to use socket and not file
        printer.print(obj);
        System.out.println("obj inviato");
    }
}
