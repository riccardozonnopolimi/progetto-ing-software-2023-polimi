package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.io.Serializable;

public class PublicModelNetwork implements Serializable {
    private static final long serialVersionUID = 1345234;
    public ItemTile[][] lrb;
    public int[] cgs;
    public int idMatch;
    public boolean ended;
    public String winner;
    public String current;

    public PublicModelNetwork(ItemTile[][] lrb,int[] cgs, int idMatch, String current){
        this.cgs = cgs;
        this.lrb = lrb;
        this.idMatch = idMatch;
        this.current = current;
    }
    public PublicModelNetwork(boolean ended, String winner, int idMatch){
        this.ended = ended;
        this.winner = winner;
        this.idMatch = idMatch;
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