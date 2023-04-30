package it.polimi.ingsw23.gc40.server;

import it.polimi.ingsw23.gc40.server.goals.Goal;

public class CommonGoalCard extends Card {
    private Goal goal;
    private String cardName;

    public CommonGoalCard() {

    }

    public CommonGoalCard(Goal g) {
        this.goal=g;
    }

    public Goal getGoal() {
        return this.goal;
    }

    @Override
    public void checkScore(Player p) {
        //check if goal.instanceGoal(Player p) is true or false
        this.goal.instanceGoal(p);
    }


}
