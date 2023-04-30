package it.polimi.ingsw23.gc40.netModel;

import it.polimi.ingsw23.gc40.server.CommonGoalCard;
import it.polimi.ingsw23.gc40.server.ItemTile;
import it.polimi.ingsw23.gc40.server.LRB;

import java.io.Serializable;
import java.util.Observable;

public class PublicModelNetwork implements Serializable {
    private static final long serialVersionUID = 1345234;
    public ItemTile[] lrb;
    public int[] cgs;

    public PublicModelNetwork(ItemTile[] lrb,int[] cgs ){
        this.cgs = cgs;
        this.lrb = lrb;
    }



}
