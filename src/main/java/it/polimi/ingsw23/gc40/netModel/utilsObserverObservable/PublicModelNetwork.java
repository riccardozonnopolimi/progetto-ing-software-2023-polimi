package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.io.Serializable;

public class PublicModelNetwork implements Serializable {
    private static final long serialVersionUID = 7829136428001571165L;
    private final ItemTile[][] lrb;
    private final int[] cgs;
    private final int idMatch;
    private final boolean ended;
    private final String winner;
    private final String current;


    public boolean isEnded() {
        return ended;
    }

    public String getWinner() {
        return winner;
    }

    public String getCurrent() {
        return current;
    }

    public PublicModelNetwork(ItemTile[][] lrb, int[] cgs, int idMatch, String current){
        this.cgs = cgs;
        this.lrb = lrb;
        this.idMatch = idMatch;
        this.current = current;
        this.ended=false;
        this.winner=null;
    }
    public PublicModelNetwork(boolean ended, String winner, int idMatch){
        this.ended = ended;
        this.winner = winner;
        this.idMatch = idMatch;
        this.current = null;
        this.lrb = null;
        this.cgs = null;

    }
    public ItemTile[][] getLrb() {
        return lrb;
    }
    public int[] getCgs() {
        return cgs;
    }
    public int getIdMatch() {
        return idMatch;
    }
}