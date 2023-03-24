package it.polimi.ingsw23.gc40.server.model;

import it.polimi.ingsw23.gc40.server.model.goals.Goal;

public class CommonGoalCard extends Card {
    private Goal goal;
    private String cardName;

    public CommonGoalCard() {

    }

    public CommonGoalCard(Goal g) {

    }

    public Goal getGoal() {
        return goal;
    }

    @Override
    public void checkScore(Player p) {
        //check if goal.instanceGoal(Player p) is true or false
    }


}
