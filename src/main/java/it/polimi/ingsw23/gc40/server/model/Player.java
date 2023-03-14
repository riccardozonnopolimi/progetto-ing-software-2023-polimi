package it.polimi.ingsw23.gc40.server.model;

public class Player {
    private String nickName;
    private Integer score;
    private Token endingToken;
    private Token scoreToken1;
    private Token scoreToken2;
    private Bookshelf shelf;
    private Boolean CG1Reached;
    private Boolean CG2Reached;
    private PersonalGoalCard personalGoal;

    public Player(String nickName) {
        this.nickName = nickName;
        this.score=0;
        this.CG1Reached=false;
        this.scoreToken1=null;
        this.personalGoal=null;
        this.endingToken=null;
        this.scoreToken2=null;
        this.CG2Reached=null;
        this.shelf=null;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        //TO DO calcolare punteggio finale
        this.score = score;
    }

    public Token getEndingToken() {
        return endingToken;
    }

    public void setEndingToken(Token endingToken) {
        this.endingToken = endingToken;
    }

    public Token getScoreToken1() {
        return scoreToken1;
    }

    public void setScoreToken1(Token scoreToken1) {
        this.scoreToken1 = scoreToken1;
    }

    public Token getScoreToken2() {
        return scoreToken2;
    }

    public void setScoreToken2(Token scoreToken2) {
        this.scoreToken2 = scoreToken2;
    }

    public Bookshelf getShelf() {
        return shelf;
    }

    public void setShelf(Bookshelf shelf) {
        this.shelf = shelf;
    }

    public Boolean getCG1Reached() {
        return CG1Reached;
    }

    public void setCG1Reached(Boolean CG1Reached) {
        this.CG1Reached = CG1Reached;
    }

    public Boolean getCG2Reached() {
        return CG2Reached;
    }

    public void setCG2Reached(Boolean CG2Reached) {
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
