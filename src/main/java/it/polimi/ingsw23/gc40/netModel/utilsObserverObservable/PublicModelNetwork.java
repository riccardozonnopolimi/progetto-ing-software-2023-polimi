package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.io.Serializable;

public class PublicModelNetwork implements Serializable {
    private static final long serialVersionUID = 1345234;
    public ItemTile[] lrb;
    public int[] cgs;

    public PublicModelNetwork(ItemTile[] lrb,int[] cgs ){
        this.cgs = cgs;
        this.lrb = lrb;
    }



}
