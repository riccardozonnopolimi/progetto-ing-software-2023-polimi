package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.io.Serial;
import java.io.Serializable;

public class PrivateModelNetwork implements Serializable {
    @Serial
    private static final long serialVersionUID = 7829136429001571165L;
    private final int pgc;
    private final ItemTile[][] bookshelf;
    private final String nickname;
    private final int[] scores;


    /**
     * @param bookshelf player bookshelf
     * @param pgc player personal goal card
     * @param nickname player nickname
     * @param scores  player scores array ( personalgoal score, commongoal score, adj score, first score)
     */
    public PrivateModelNetwork(ItemTile[][] bookshelf, int pgc, String nickname, int[] scores) {
        this.bookshelf = bookshelf;
        this.pgc = pgc;
        this.nickname = nickname;
        this.scores = scores;
    }

    /**
     * @return player scores array ( personalgoal score, commongoal score, adj score, first score)
     */
    public int[] getScores() {
        return scores;
    }

    /**
     * @return player bookshelf
     */
    public ItemTile[][] getBookshelf() {
        return bookshelf;
    }

    /**
     * @return player personal goal
     */
    public int getPgc() {
        return pgc;
    }

    /**
     * @return player nickname
     */
    public String getNickname() {
        return nickname;
    }
}