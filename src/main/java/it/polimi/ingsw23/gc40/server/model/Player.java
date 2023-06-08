package it.polimi.ingsw23.gc40.server.model;

import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelObserver;

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
    private Boolean finishedFirst;
    private PersonalGoalCard personalGoal;
    private Bookshelf bookshelf;
    private PrivateModelObserver observer = new PrivateModelObserver(this);
    // scores[0] PGscore, scores[1] CGscores, scores[2] ADJscores, scores[3] firstScores
    private int[] scores;





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

    public int getScores(int index) {
        return this.scores[index];
    }

    private synchronized void setScores(int index, int points) {
        this.scores[index] = points;
        setChanged();
        notifyObservers(this.scores);
    }

    public PrivateModelObserver getPrivateModelObserver() {
        return observer;
    }
    public Boolean getCG1Reached() {
        return CG1Reached;
    }

    public Boolean getCG2Reached() {
        return CG2Reached;
    }

    public void setCG1Reached(Boolean CG1Reached) {
        this.CG1Reached = CG1Reached;
    }

    public void setCG2Reached(Boolean CG2Reached) {
        this.CG2Reached = CG2Reached;
    }
    public void setFirstScore(int firstScore) {
        this.firstScore = firstScore;
        setScores(3,firstScore);
    }
    public int getPGScore() {
        return PGScore;
    }

    public int getADJScore() {
        return ADJScore;
    }

    public void setADJScore(int adjScore) {
        this.ADJScore = adjScore;
        setScores(2,adjScore);

    }

    public void setPGScore(int PGScore) {
        this.PGScore = PGScore;
        setScores(0,PGScore);
    }

    public boolean isStartedFirst() {
        return startedFirst;
    }

    public int getFirstScore() {
        return firstScore;
    }

    public int getCGScore() {
        return CGScore;
    }

    public void setCGScore(int CGScore) {
        this.CGScore = CGScore;
        setScores(1,CGScore);
    }

    public void setStartedFirst(boolean startedFirst) {
        this.startedFirst = startedFirst;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    public PersonalGoalCard getPersonalGoal() {
        return personalGoal;
    }

    public void setPersonalGoal(PersonalGoalCard personalGoal) {
        this.personalGoal = personalGoal;
        setChanged();
        notifyObservers(this.getPersonalGoal().getId());
    }

    public String getNickName() {
        return nickName;
    }

    public int totScore() {
        return CGScore+PGScore+ ADJScore+firstScore;
    }

    public void insertIT(ItemTile it, int column){
        this.bookshelf.insertIT(it, column);
        setChanged();
        notifyObservers(this.getBookshelf().getShelf());
    }

    public PrivateModelObserver getObserver() {
        return observer;
    }
}
