package it.polimi.ingsw23.gc40.server.model;

import java.io.Serializable;

public class Player implements Serializable {
    private boolean first;
    private String nickName;
    private int CGScore;
    private int PGScore;
    private int ADJScore;
    private int firstScore;
    private Boolean CG1Reached;
    private Boolean CG2Reached;
    private PersonalGoalCard personalGoal;
    private Bookshelf bookshelf;

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

    public void setADJScore(int adJscore) {
        this.ADJScore = adJscore;

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

    public void setBookshelf(Bookshelf bookshelf) {
        this.bookshelf = bookshelf;
    }

    public boolean getCG1Reached() {
        return CG1Reached;
    }

    public void setCG1Reached(boolean CG1Reached) {
        this.CG1Reached = CG1Reached;
    }

    public boolean getCG2Reached() {
        return CG2Reached;
    }

    public void setCG2Reached(boolean CG2Reached) {
        this.CG2Reached = CG2Reached;
    }

    public PersonalGoalCard getPersonalGoal() {
        return personalGoal;
    }

    public void setPersonalGoal(PersonalGoalCard personalGoal) {
        this.personalGoal = personalGoal;
    }

    public String getNickName() {
        return nickName;
    }

    public int totScore() {

        return CGScore+PGScore+ ADJScore+firstScore;
    }
}
