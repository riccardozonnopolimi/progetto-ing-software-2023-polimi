package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.controller.GameController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;


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
    private GameController match;


    public PublicModelObservable(GameController gameController) {
        gameController.addObserver(this);
        // todo implement remove listener or destructor
        match = gameController;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ItemTile[][]) {
            lrb = (ItemTile[][]) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current);
        }
        if (arg instanceof int[]) {
            cgs = (int[]) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current);
        }
        if (arg instanceof Integer) {
            idMatch = (int) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current);
        }
        if (arg instanceof String) {
            winner = (String) arg;
            current = (String) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current);
        }
        if (arg instanceof Boolean) {
            ended = (Boolean) arg;
            data = new PublicModelNetwork(ended, winner);
        }

        try {


            for (int i = 0; i < match.getPlayers().size(); i++){
                if(match.getPlayers().get(i).getPrivateModelObservable().getSH()!=null)
                    match.getPlayers().get(i).getPrivateModelObservable().getSH().sendObj("#public_object",data);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setChanged();
        notifyObservers(data);
    }

    public PublicModelNetwork getData() {
        return data;
    }
}
