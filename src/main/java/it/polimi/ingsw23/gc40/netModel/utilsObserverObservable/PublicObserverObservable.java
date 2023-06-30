package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.controller.GameController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.util.*;

public class PublicObserverObservable extends Observable implements Observer {
    private static final long serialVersionUID = 7829136881241571165L;
    private PublicModelNetwork data;
    public ItemTile[][] lrb;
    public int[] cgs;
    public int idMatch;
    public Boolean playable;
    public String winner;
    public String current;
    private final GameController match;
    private short turnNumber;
    private short[] points;
    private String[] playersNick;


    /**
     * Constructor. It sets PublicModelObserver object as GameController observer
     *
     * @param gameController
     */
    public PublicObserverObservable(GameController gameController) {
        gameController.addObserver(this);
        match = gameController;
        playable = false;
        points = null;
    }


    /**
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     *            method.
     */
    @Override
    public void update(Observable o, Object arg) {
        //updating player's name
        if (arg instanceof String[]) {
            playersNick = (String[]) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null, turnNumber, points, playersNick);
        }
        //updating points
        if (arg instanceof short[]) {
            points = (short[]) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, winner, turnNumber, points, playersNick);
        }
        //updating lrb
        if (arg instanceof ItemTile[][]) {
            lrb = (ItemTile[][]) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null, turnNumber, points, playersNick);
        }
        //updating turnNumber
        if (arg instanceof Short) {
            turnNumber = (short) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null, turnNumber, points, playersNick);
        }
        //sending commongoals id
        if (arg instanceof int[]) {
            cgs = (int[]) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null, turnNumber, points, playersNick);
        }
        //updating idMatch
        if (arg instanceof Integer) {
            idMatch = (int) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null, turnNumber, points, playersNick);
        }
        //updating winner and current
        if (arg instanceof String) {
            current = (String) arg;
            if (!playable) {
                winner = (String) arg;
                System.out.println("sono in pmo update");
                data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, winner, turnNumber, points, playersNick);
            }
            else {
                data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null, turnNumber, points, playersNick);
            }
        }
        //updating match status (playable or not)
        if (arg instanceof Boolean) {
            playable = (Boolean) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null, turnNumber, points, playersNick);

        }

        setChanged();
        notifyObservers(data);
    }


    /**
     * Notifies data (PublicModelNetwork)
     *
     * @param o an observer to be added.
     */
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(data);
    }
}