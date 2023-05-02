package it.polimi.ingsw23.gc40.server;

import it.polimi.ingsw23.gc40.server.goals.Goal;

import java.io.Serializable;
import java.util.Observable;

public class CommonGoalCard implements Serializable {
    private Goal goal;
    private String cardName;
    private static final long serialVersionUID = 567789;

    public CommonGoalCard() {

    }

    public CommonGoalCard(Goal g) {
        this.goal=g;
    }

    public Goal getGoal() {
        return this.goal;
    }


    public void checkScore(Player p) {
        //check if goal.instanceGoal(Player p) is true or false
        this.goal.instanceGoal(p);
    }


}
