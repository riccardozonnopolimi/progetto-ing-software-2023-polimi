package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.server.model.MatchModel;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Server extends Remote {
    ArrayList<Player> getOnlinePlayers() throws RemoteException;

    ArrayList<MatchModel> getActiveMatches() throws RemoteException;

    MatchModel getQueueMatch() throws RemoteException;
    //lista di funzioni da implementare
    Player getPlayer(String nome) throws RemoteException;
    boolean hasQueueMatch() throws RemoteException;
    Boolean checkExistingNickName(String nickName)throws RemoteException;
    MatchRMI addToLastMatch(Player player) throws RemoteException;
    MatchRMI createNewMatch(int numPlayer) throws RemoteException;
    void endMatch(MatchModel match) throws RemoteException;
    void addPlayer(String name) throws  RemoteException;
    void print(String name) throws  RemoteException;
}
