package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.controller.GameController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PrivateModelNetwork implements Serializable {
    private static final long serialVersionUID = 7823495;
    private int pgc;
    private ItemTile[][] bookshelf;
    private String nickname;




    public PrivateModelNetwork( ItemTile[][] bookshelf, int pgc,String nickname) {
       // this.riferPersonalCard = riferPersonalCard;
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
