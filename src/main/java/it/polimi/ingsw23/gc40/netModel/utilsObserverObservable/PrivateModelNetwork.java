package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.controller.GameController;

import java.io.Serializable;

public class PrivateModelNetwork implements Serializable {
    private static final long serialVersionUID = 7823495;
    private int riferPersonalCard;
    private ItemTile[][] bookshelf;
    private GameController gameController;
    public GameController getMatch() {
        return gameController;
    }

    public void setMatch(GameController gameController) {
        this.gameController = gameController;
    }



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
