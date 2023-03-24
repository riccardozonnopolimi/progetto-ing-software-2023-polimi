package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.server.model.Player;
import it.polimi.ingsw23.gc40.server.model.ServerModel;

public class ServerController {
    private ServerModel s;

    public ServerController(){
        this.s = new ServerModel();
    }
    public boolean newPlayer(String name){
        if(s.checkExistingNickName(name))
            return false;
        else {s.addPlayer(name); return true;}
    }
    public Player getPlayer(String name){
        return s.getPlayer(name);
    }

    public boolean hasQueueMatch() {
        return s.hasQueueMatch();
    }
}
