package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.io.Serializable;

public class PublicModelNetwork implements Serializable {
    private static final long serialVersionUID = 7829136428001571165L;
    private final ItemTile[][] lrb;
    private final int[] cgs;
    private final int idMatch;
    private final boolean playable;
    private final String winner;
    private final String current;
    private final short turnNumber;


    /**
     * @return true if the match can start or if it can restart
     */
    public boolean isPlayable() {
        return playable;
    }

    public String getWinner() {
        return winner;
    }

    /**
     * @return player nickname that is currently playing the turn
     */
    public String getCurrent() {
        return current;
    }

    /**
     * PublicModelNetwork constructor. It just sets value passed by parameters
     * @param lrb map
     * @param cgs commongoals array
     * @param idMatch match id
     * @param current current player, playing round
     * @param playable match status
     * @param winner nickname of the winner
     * @param turnNumber turn number
     */
    public PublicModelNetwork(ItemTile[][] lrb, int[] cgs, int idMatch, String current, boolean playable, String winner, short turnNumber) {
        this.cgs = cgs;
        this.lrb = lrb;
        this.idMatch = idMatch;
        this.current = current;
        this.playable = playable;
        this.winner = winner;
        this.turnNumber = turnNumber;
    }


    /**
     * @return map
     */
    public ItemTile[][] getLrb() {
        return lrb;
    }

    /**
     * @return common goals
     */
    public int[] getCgs() {
        return cgs;
    }

    /**
     * @return match id
     */
    public int getIdMatch() {
        return idMatch;
    }

    /**
     * @return current turn number
     */
    public int getTurnNumber() {
        return turnNumber;
    }
}