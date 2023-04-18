package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.server.ServerModel;
import it.polimi.ingsw23.gc40.server.model.Match;
import it.polimi.ingsw23.gc40.server.model.Player;
import it.polimi.ingsw23.gc40.server.model.Score;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerMainRMI implements Server {
    private ArrayList<Player> onlinePlayers;
    private ArrayList<Match> activeMatches;
    private Match queueMatch;
    private ArrayList<Score> SavedScores;
    static int port;
    protected ServerMainRMI() throws RemoteException {
    }

    public static void main(String args) throws RemoteException {
        Server stub = null;
        ServerModel sMod = new ServerModel();
        Registry registry = null;
        try{
                stub = (Server) UnicastRemoteObject.exportObject(sMod, port);

        }catch(RemoteException e){ e.printStackTrace();}
         try{
             registry = LocateRegistry.createRegistry(port);
         }catch(RemoteException e){e.printStackTrace();}
         try{
             registry.bind("Server", stub);
         }catch(RemoteException e){e.printStackTrace();} catch (AlreadyBoundException e) {
             throw new RuntimeException(e);
         }

    }
    @Override
    public void addPlayer(String name) {
        Player p = new Player(name);
        onlinePlayers.add(p);
    }
@Override
    public Match createNewMatch(int numPlayer) {
        //TO TEST
        queueMatch = new Match(numPlayer);

        return queueMatch;

    }
@Override
    public Match addToLastMatch(Player player) {
        //TO TEST
        //if true it means that the match has all the players, so it can start
        if (queueMatch.addPlayer(player)) {
            Match m = queueMatch;
            activeMatches.add(m);
            queueMatch = null;
            return m;
        }
        return queueMatch;
    }

    @Override
    public Boolean checkExistingNickName(String nickName) {
        //TO TEST
        for (int i = 0; i < onlinePlayers.size(); i++) {
            if (onlinePlayers.get(i).getNickName().equals(nickName)) {
                return true;
            }
        }
        return false;
    }
    public void endMatch(Match match) {
        //TO TEST
        if(match.isEnding() && !match.playable()){
            ArrayList<Integer>  scores=new ArrayList<>();
            for(int i=0; i<match.getPlayers().size(); i++) {
                Integer integer = Integer.valueOf(match.getPlayers().get(i).totScore());
                scores.add(integer);
            }
            Score s = new Score(match.getPlayers(), match.winner(), scores);
            SavedScores.add(s);
        }

    }

@Override
    public boolean hasQueueMatch() {
        return (queueMatch != null);
    }
@Override
    public Player getPlayer(String nome) {
        for (int i = 0; i < onlinePlayers.size(); i++) {
            if (onlinePlayers.get(i).getNickName().equals(nome)) {
                return onlinePlayers.get(i);
            }
        }
        return null;
    }


    @Override
    public ArrayList<Player> getOnlinePlayers() throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<Match> getActiveMatches() throws RemoteException {
        return null;
    }

    @Override
    public Match getQueueMatch() throws RemoteException {
        return null;
    }
}

