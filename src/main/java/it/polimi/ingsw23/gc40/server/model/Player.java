package it.polimi.ingsw23.gc40.server.model;

public class Player {
    private boolean first;
    private String nickName;
    private int CGscore;
    private int PGscore;
    private int ADJscore;

    private Boolean CG1Reached;
    private Boolean CG2Reached;
    private PersonalGoalCard personalGoal;
    private Bookshelf bookshelf;
    public Player(String nickName) {
        this.nickName = nickName;
        this.CGscore = 0;
        this.CG1Reached = false;
        this.personalGoal = null;
        this.CG2Reached = null;
        this.first = false;
        this.PGscore = 0;
        this.ADJscore = 0;
        this.bookshelf = new Bookshelf();

    }

    public int getPGscore() {
        return PGscore;
    }

    public int getADJscore() {
        return ADJscore;
    }

    public void setADJscore(int adJscore) {
        this.ADJscore = adJscore;

    }

    public void setPGscore(int PGscore) {
        this.PGscore = PGscore;
    }

    public boolean isFirst() {
        return first;
    }

    public int getCGscore() {
        return CGscore;
    }

    public void setCGscore(int CGscore) {
        this.CGscore = CGscore;
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
}
