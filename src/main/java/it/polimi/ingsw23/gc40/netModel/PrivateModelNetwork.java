package it.polimi.ingsw23.gc40.netModel;

import it.polimi.ingsw23.gc40.server.ItemTile;

import java.io.Serializable;

public class PrivateModelNetwork implements Serializable {
    private static final long serialVersionUID = 7823495;
    private int riferPersonalCard;
    private ItemTile[][] bookshelf;

    public ItemTile[][] getBookshelf() {
        return bookshelf;
    }

    public void setBookshelf(ItemTile[][] bookshelf) {
        this.bookshelf = bookshelf;
    }

    public int getRiferPersonalCard() {
        return riferPersonalCard;
    }

    public void setRiferPersonalCard(int riferPersonalCard) {
        this.riferPersonalCard = riferPersonalCard;
    }
}
