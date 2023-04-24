package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.server.model.*;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observer;

public interface Match extends Remote {
    void round(Player p)throws RemoteException;
    void refill()throws RemoteException;
    boolean addPlayer(Player p)throws RemoteException;
    void setPlayersOrder()throws RemoteException;
    ItemTile takeItem()throws RemoteException;
    ArrayList<Player> getPlayers()throws RemoteException;
    void deleteObservers()throws RemoteException;
    void removeObserver(Observer observer) throws RemoteException;
    void notifyObservers()throws RemoteException;
    void addObserver(Observer o) throws RemoteException;
    Player winner()throws RemoteException;
    boolean playable()throws RemoteException;
    Player getNext()throws RemoteException;
    boolean isEnding()throws RemoteException;
    int getNumPlayers() throws RemoteException;
    void setNumPlayers(int numPlayers) throws RemoteException;
    ArrayList<ItemTile> getBag() throws RemoteException;
    void setBag(ArrayList<ItemTile> bag) throws RemoteException;
    ArrayList<CommonGoalCard> getCommonGoals() throws RemoteException;
    void setCommonGoals(ArrayList<CommonGoalCard> commonGoals) throws RemoteException;
    ArrayList<Integer> getCG1Scores() throws RemoteException;
    public void print(String s) throws RemoteException;
    void setCG1Scores(ArrayList<Integer> CG1Scores) throws RemoteException;
    ArrayList<Integer> getCG2Scores() throws RemoteException;
    void setCG2Scores(ArrayList<Integer> CG2Scores) throws RemoteException;
    ArrayList<PersonalGoalCard> getPersonalGoals() throws RemoteException;
    void setPersonalGoals(ArrayList<PersonalGoalCard> personalGoals) throws RemoteException;
    void setPlayers(ArrayList<Player> players) throws RemoteException;
    LRB getMap() throws RemoteException;
    void setMap(LRB map) throws RemoteException;
    void setEnding(boolean ending) throws RemoteException;
    void setNext(Player next) throws RemoteException;





}
