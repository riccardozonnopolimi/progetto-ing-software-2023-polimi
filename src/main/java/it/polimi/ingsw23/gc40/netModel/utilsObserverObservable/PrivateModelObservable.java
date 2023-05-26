package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.model.*;

import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class PrivateModelObservable extends Observable implements Observer {
    private PrivateModelNetwork data;
    public ItemTile[][] shelf;
    public int pgc;
    public String nickname;
    public PrintWriter printer;

      public PrivateModelObservable(Player p) {
        p.addObserver(this);
          Socket s;
          try {
              s = new Socket("localhost", 9000);
              printer = new PrintWriter(s.getOutputStream(), true);
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
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

    public void serialize(Object obj) {
        System.out.println("entro");
        //todo trying to use socket and not file
        printer.print(obj);
        System.out.println("obj inviato");

    }
}