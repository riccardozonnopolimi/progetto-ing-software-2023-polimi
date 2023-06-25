package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.controller.GameController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PublicModelObserver extends Observable implements Observer {
    private static final long serialVersionUID = 7829136881241571165L;
    private PublicModelNetwork data;
    public ItemTile[][] lrb;
    public int[] cgs;
    public int idMatch;
    public Boolean playable;
    public String winner;
    public String current;
    private final GameController match;
    private final ArrayList<String> downloaded = new ArrayList<>();
    private short turnNumber;


    /**
     * Constructor. It sets PublicModelObserver object as GameController observer
     *  @param gameController
     */
    public PublicModelObserver(GameController gameController) {
        gameController.addObserver(this);
        match = gameController;
        playable = false;
    }


    /**
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     *            method.
     */
    @Override
    public void update(Observable o, Object arg) {
       //updating lrb
        if (arg instanceof ItemTile[][]) {
            lrb = (ItemTile[][]) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null,turnNumber);
        }
        //updating turnNumber
        if (arg instanceof Short) {
            turnNumber = (short) arg;
            data = new PublicModelNetwork(lrb,cgs,idMatch,current,playable,null,turnNumber);
        }
        //sending commongoals id
        if (arg instanceof int[]) {
            cgs = (int[]) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null, turnNumber);
        }
        //updating idMatch
        if (arg instanceof Integer) {
            idMatch = (int) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null, turnNumber);
        }
        //updating winner and current
        if (arg instanceof String) {
            winner = (String) arg;
            current = (String) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null, turnNumber);
        }
       //updating match status (playable or not)
        if (arg instanceof Boolean) {
            playable = (Boolean) arg;
            if (playable)
                data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, null, turnNumber);
            else
                data = new PublicModelNetwork(lrb, cgs, idMatch, current, playable, winner, turnNumber);

        }
        setChanged();
        notifyObservers(data);
        //todo cancellare eventualmente
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

   //todo inutilizzato
    public Object getData() {
        return data;
    }

    public void newObserver(Observer o) {
        addObserver(o);
        setChanged();
        notifyObservers(data);
    }

    /**
     * Notifies data (PublicModelNetwork)
     * @param o an observer to be added.
     */
    @Override
    public void addObserver(Observer o) {

        super.addObserver(o);
        setChanged();
        notifyObservers(data);

    }


}
