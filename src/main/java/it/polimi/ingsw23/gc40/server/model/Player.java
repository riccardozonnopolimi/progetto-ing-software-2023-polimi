package it.polimi.ingsw23.gc40.server.model;

public class Player {
    private boolean first;
    private String nickName;
    private int score;
    private Bookshelf bookshelf;
    private Boolean CG1Reached;
    private Boolean CG2Reached;
    private PersonalGoalCard personalGoal;

    public Player(String nickName) {
        this.nickName = nickName;
        this.score=0;
        this.CG1Reached=false;
        this.personalGoal=null;
        this.CG2Reached=null;
        this.bookshelf =null;
        this.first=false;
    }

    public boolean isFirst() {
        return first;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        //TO DO calcolare punteggio finale
        this.score = score;
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
