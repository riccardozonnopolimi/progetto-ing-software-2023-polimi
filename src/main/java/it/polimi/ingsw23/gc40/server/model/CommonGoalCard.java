package it.polimi.ingsw23.gc40.server.model;

public class CommonGoalCard extends Card {
    private Token token;
    private Goal goal;
    private String cardName;
    public CommonGoalCard(){

    }
    public CommonGoalCard(Goal g){

    }


    @Override
    public void checkScore(Player p) {
        //check if goal.instanceGoal(Player p) is reached or not
    }



}
