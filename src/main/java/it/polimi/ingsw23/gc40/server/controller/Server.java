package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.server.model.Match;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Server extends Remote {
    ArrayList<Player> getOnlinePlayers() throws RemoteException;

    ArrayList<Match> getActiveMatches() throws RemoteException;

    Match getQueueMatch() throws RemoteException;
    //lista di funzioni da implementare
    Player getPlayer(String nome) throws RemoteException;
    boolean hasQueueMatch() throws RemoteException;
    Boolean checkExistingNickName(String nickName)throws RemoteException;
    Match addToLastMatch(Player player) throws RemoteException;
    Match createNewMatch(int numPlayer) throws RemoteException;
    void addPlayer(String name) throws  RemoteException;
}
