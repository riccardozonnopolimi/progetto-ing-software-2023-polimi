package it.polimi.ingsw23.gc40.server.model.goals;

import it.polimi.ingsw23.gc40.server.model.Player;

public interface Goal {
    boolean instanceGoal(Player p);
    void print();
}
