package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.controller.GameController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;


import java.io.Serializable;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PublicModelObserver extends Observable implements Observer, Serializable, Remote {
    private static final long serialVersionUID = 7829136881241571165L;
    private PublicModelNetwork data;
    public ItemTile[][] lrb;
    public int[] cgs;
    public int idMatch;
    public Boolean playable;
    public String winner;
    public String current;
    private transient GameController match;
    private transient ArrayList<String> downloaded = new ArrayList<>();


    public PublicModelObserver(GameController gameController) {
        gameController.addObserver(this);
        match = gameController;
        playable = false;
    }


    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ItemTile[][]) {
            lrb = (ItemTile[][]) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null);
        }

        if (arg instanceof int[]) {
            cgs = (int[]) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null);
        }
        if (arg instanceof Integer) {
            idMatch = (int) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null);
        }
        if (arg instanceof String) {
            winner = (String) arg;
            current = (String) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null);
        }
        if (arg instanceof Boolean) {
            playable = (Boolean) arg;
            if (playable)
                data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null);
            else
                data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, winner);

        }
        setChanged();
        notifyObservers(data);
        //System.out.println("server notifica public dati correttamente");
       /* try {

            System.out.println("public update");
            for (int i = 0; i < match.getPlayers().size(); i++) {
                if (match.getPlayers().get(i).getPrivateModelObserver().getSH() != null) {
                    System.out.println("public update in socket: " + match.getPlayers().get(i).getNickName());
                    match.getPlayers().get(i).getPrivateModelObserver().getSH().sendObj("#public_object", data);
                } else {
                    downloaded.add(match.getPlayers().get(i).getNickName());
                    System.out.println("public update in rmi: " + match.getPlayers().get(i).getNickName());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/


    }

/*
    public ArrayList<String> getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(ArrayList<String> downloaded) {
        this.downloaded = downloaded;
    }
*/

    public Object getData() {
        return data;
    }

    public void newObserver(Observer o) {
        addObserver(o);
        setChanged();
        notifyObservers(data);
    }

    @Override
    public void addObserver(Observer o) {

        super.addObserver(o);
        setChanged();
        notifyObservers(data);

    }


}
