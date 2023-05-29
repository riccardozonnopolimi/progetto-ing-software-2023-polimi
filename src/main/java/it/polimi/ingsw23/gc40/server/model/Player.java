package it.polimi.ingsw23.gc40.server.model;

import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelObserver;

import java.util.Observable;

public class Player extends Observable {
    private boolean first;
    private final String nickName;
    private int CGScore;
    private int PGScore;
    private int ADJScore;
    private int firstScore;
    private Boolean CG1Reached;
    private Boolean CG2Reached;
    private PersonalGoalCard personalGoal;
    private Bookshelf bookshelf;
    private PrivateModelObserver observer = new PrivateModelObserver(this);





    public Player(String nickName) {
        this.nickName = nickName;
        this.CGScore = 0;
        this.CG1Reached = false;
        this.personalGoal = null;
        this.CG2Reached = null;
        this.first = false;
        this.PGScore = 0;
        this.ADJScore = 0;
        this.bookshelf = new Bookshelf();
        setChanged();
        notifyObservers(nickName);
        setChanged();
        notifyObservers(this.getBookshelf().getShelf());

    }

    public PrivateModelObserver getPrivateModelObserver() {
        return observer;
    }

    public void setFirstScore(int firstScore) {
        this.firstScore = firstScore;
    }
    public int getPGScore() {
        return PGScore;
    }

    public int getADJScore() {
        return ADJScore;
    }

    public void setADJScore(int adjScore) {
        this.ADJScore = adjScore;
    }

    public void setPGScore(int PGScore) {
        this.PGScore = PGScore;
    }

    public boolean isFirst() {
        return first;
    }

    public int getFirstScore() {
        return firstScore;
    }

    public int getCGScore() {
        return CGScore;
    }

    public void setCGScore(int CGScore) {
        this.CGScore = CGScore;
    }

    public void setFirst(boolean first) {
        this.first = first;
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
        notifyObservers(this.getBookshelf().getShelf());
    }
}
