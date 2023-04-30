package it.polimi.ingsw23.gc40.server;

import java.io.Serializable;

public abstract class Card implements Serializable {
    public abstract void checkScore(Player p);
}
