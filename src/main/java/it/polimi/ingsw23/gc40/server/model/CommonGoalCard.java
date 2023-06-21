package it.polimi.ingsw23.gc40.server.model;

import it.polimi.ingsw23.gc40.server.model.goals.Goal;

public class CommonGoalCard {
    private Goal goal;
    private String cardName; //todo ??

    public CommonGoalCard(Goal g) {
        this.goal=g;
    }

    public Goal getGoal() {
        return this.goal;
    }

}