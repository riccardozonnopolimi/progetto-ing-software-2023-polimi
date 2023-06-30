package it.polimi.ingsw23.gc40.server.model;

import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateObserverObservable;

import java.util.Observable;

public class Player extends Observable {
    private boolean startedFirst;
    private final String nickName;
    private int CGScore;
    private int PGScore;
    private int ADJScore;
    private int firstScore;
    private Boolean CG1Reached;
    private Boolean CG2Reached;
    private final Boolean finishedFirst;
    private PersonalGoalCard personalGoal;
    private final Bookshelf bookshelf;
    private final PrivateObserverObservable observer = new PrivateObserverObservable(this);
    // scores[0] PGscore, scores[1] CGscores, scores[2] ADJscores, scores[3] firstScores
    private final int[] scores;

    /**
     * Player Constructor initializes the nickname player wrote by the client.
     * Notifies Observers about his/her nickname and about the bookshelf
     * @param nickName chosen by the client
     */
    public Player(String nickName) {
        this.nickName = nickName;
        this.CGScore = 0;
        this.CG1Reached = false;
        this.personalGoal = null;
        this.CG2Reached = false;
        this.startedFirst = false;
        this.finishedFirst = false;
        this.PGScore = 0;
        this.ADJScore = 0;
        this.bookshelf = new Bookshelf();
        this.scores = new int[]{0, 0, 0, 0};

        setChanged();
        notifyObservers(nickName);
        setChanged();
        notifyObservers(this.getBookshelf().getShelf());
        setChanged();
        notifyObservers(scores);
    }

    /**
     * @param index of the array used for different types of Scores you can gain during the match.
     * @return PGScore/CGScore/EndingScore/ADJScore
     */
    public int getScores(int index) {
        return this.scores[index];
    }

    /**
     * @param index of the array used for different types of Scores you can gain during the match.
     * @param points the number of points you got after achieving
     */
    private synchronized void setScores(int index, int points) {
        this.scores[index] = points;
        setChanged();
        notifyObservers(this.scores);
    }

    /**
     * @return the PrivateModelObserver previously assigned to the player
     */
    public PrivateObserverObservable getPrivateModelObserver() {
        return observer;
    }

    /**
     * @return true if the player has reached Common Goal Number 1 during the match
     */
    public Boolean getCG1Reached() {
        return CG1Reached;
    }

    /**
     * @return true if the player has reached Common Goal Number 2 during the match
     */
    public Boolean getCG2Reached() {
        return CG2Reached;
    }

    /**
     * @param CG1Reached inizialized false in costructor. It becomes true when a player achieves the goal number 1
     */
    public void setCG1Reached(Boolean CG1Reached) {
        this.CG1Reached = CG1Reached;
    }

    /**
     * @param CG2Reached inizialized false in costructor. It becomes true when a player achieves the goal number 2
     */
    public void setCG2Reached(Boolean CG2Reached) {
        this.CG2Reached = CG2Reached;
    }

    /**
     * @param firstScore set in the last index of scores array
     */
    public void setFirstScore(int firstScore) {
        this.firstScore = firstScore;
        setScores(3,firstScore);
    }

    /**
     * @return Personal Goal Score in case the player wants to see how many points got
     */
    public int getPGScore() {
        return PGScore;
    }

    /**
     * @return ADJ Score in case the player wants to see how many points got
     */
    public int getADJScore() {
        return ADJScore;
    }

    /**
     * @param adjScore set in the third index of scores array
     */
    public void setADJScore(int adjScore) {
        this.ADJScore = adjScore;
        setScores(2,adjScore);
    }

    /**
     * @param PGScore set in the first index of scores array
     */
    public void setPGScore(int PGScore) {
        this.PGScore = PGScore;
        setScores(0,PGScore);
    }

    /**
     * @return true if the player was the first of the match (has the chair when the match starts)
     */
    public boolean isStartedFirst() {
        return startedFirst;
    }

    /**
     * @return First Score in case the player wants to see if he got it
     */
    public int getFirstScore() {
        return firstScore;
    }

    /**
     * @return Common Goal Score in case the player wants to see how many points got
     */
    public int getCGScore() {
        return CGScore;
    }

    /**
     * @param CGScore set in the second index of scores array
     */
    public void setCGScore(int CGScore) {
        this.CGScore = CGScore;
        setScores(1,CGScore);
    }

    /**
     * @param startedFirst set true if the player is the first who get tile in the match
     */
    public void setStartedFirst(boolean startedFirst) {
        this.startedFirst = startedFirst;
    }

    /**
     * @return player bookshelf
     */
    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    /**
     * @return player Personal Goal
     */
    public PersonalGoalCard getPersonalGoal() {
        return personalGoal;
    }

    /**
     * @param personalGoal is randomly instantiated
     * in LobbyController and it is assigned to a player when the match starts
     */

    public void setPersonalGoal(PersonalGoalCard personalGoal) {
        this.personalGoal = personalGoal;
        setChanged();
        notifyObservers(this.getPersonalGoal().getId());
    }

    /**
     *
     * @return the nickname player of which it is instantiated
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @return the total score achieved during the match
     */
    public int totScore() {
        return CGScore+PGScore+ ADJScore+firstScore;
    }

    /**
     * Notifies observers about the item player took from the LRB and put in the Bookshelf
     * @param it is the item Tile chosen by the player
     * @param column player wants to fill with the tile/tiles chosen
     */
    public void insertIT(ItemTile it, int column){
        this.bookshelf.insertIT(it, column);
        setChanged();
        notifyObservers(this.getBookshelf().getShelf());
    }

    /**
     * @return player private model
     */
    //todo refactor private e public model observer
    public PrivateObserverObservable getObserver() {
        return observer;
    }


    public void setChangedAndNotify(Object obj){
        setChanged();
        notifyObservers(obj);
    }
}