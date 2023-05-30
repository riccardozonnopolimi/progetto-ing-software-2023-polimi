package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.controller.GameController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.io.*;

public class PrivateModelNetwork implements Serializable {
    @Serial
    private static final long serialVersionUID = 7829136429001571165L;
    private final int pgc;
    private final ItemTile[][] bookshelf;
    private final String nickname;

    public PrivateModelNetwork( ItemTile[][] bookshelf, int pgc,String nickname) {
        this.bookshelf = bookshelf;
        this.pgc = pgc;
        this.nickname = nickname;
    }
    public ItemTile[][] getBookshelf() {
        return bookshelf;
    }
    public int getPgc() {
        return pgc;
    }
    public String getNickname() {
        return nickname;
    }
}