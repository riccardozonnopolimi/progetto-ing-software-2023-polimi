package it.polimi.ingsw23.gc40.server;

import it.polimi.ingsw23.gc40.server.model.Match;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Server extends Remote {
    public ArrayList<Player> getOnlinePlayers() throws RemoteException;
    public ArrayList<Match> getActiveMatches() throws RemoteException;
    public Match getQueueMatch() throws RemoteException;
    //lista di funzioni da implementare

}
