package it.polimi.ingsw23.gc40.server.goals;

import it.polimi.ingsw23.gc40.server.Player;

public interface Goal {
    boolean instanceGoal(Player p);
    void print();
}
