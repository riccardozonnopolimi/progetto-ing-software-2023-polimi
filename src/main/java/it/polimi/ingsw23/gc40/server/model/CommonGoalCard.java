package it.polimi.ingsw23.gc40.server.model;

import it.polimi.ingsw23.gc40.server.model.goals.Goal;

public class CommonGoalCard {
    private final Goal goal;

    /**
     * constructor of CommonGoalCard
     * @param g the goal of the CGC
     */
    public CommonGoalCard(Goal g) {
        this.goal=g;
    }

    /**
     * @return the goal of the common goal card
     */
    public Goal getGoal() {
        return this.goal;
    }

}