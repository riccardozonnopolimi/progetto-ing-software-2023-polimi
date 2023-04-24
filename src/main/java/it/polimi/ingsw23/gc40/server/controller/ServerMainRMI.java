package it.polimi.ingsw23.gc40.server.controller;
import it.polimi.ingsw23.gc40.server.model.MatchModel;
import it.polimi.ingsw23.gc40.server.model.Player;
import it.polimi.ingsw23.gc40.server.model.Score;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerMainRMI implements Server {
    private ArrayList<Player> onlinePlayers = new ArrayList<>();
    private ArrayList<MatchRMI> activeMatches= new ArrayList<>();
    private MatchRMI queueMatch;
    private ArrayList<Score> SavedScores= new ArrayList<>();
    static int port  = 15001;
    public ServerMainRMI() throws RemoteException {
    }

    public static void main(String[] args) throws RemoteException {
        try {
        ServerMainRMI sMod = new ServerMainRMI();

        Server stub = (Server) UnicastRemoteObject.exportObject(sMod, 0);

           Registry registry = LocateRegistry.createRegistry(1099);

            registry.bind("Server", stub);
            System.out.println("server pronto");
        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }

    }
    @Override
    public void addPlayer(String name) {
        Player p = new Player(name);
        onlinePlayers.add(p);
    }
@Override
    public MatchRMI createNewMatch(int numPlayer) {
        queueMatch = new MatchRMI(numPlayer);
        return queueMatch;
    }
@Override
    public MatchRMI addToLastMatch(Player player) {
        //TO TEST
        //if true it means that the match has all the players, so it can start
        if (queueMatch.addPlayer(player)) {
            MatchRMI m = queueMatch;
            activeMatches.add(m);
            queueMatch = null;
            return m;
        }

        return queueMatch;
    }

    @Override
    public Boolean checkExistingNickName(String nickName) {
        //TO TEST

        if(onlinePlayers.isEmpty()) return false;
        for (int i = 0; i < onlinePlayers.size(); i++) {
            if (onlinePlayers.get(i).getNickName().equals(nickName)) {
                return true;
            }
        }
        return false;
    }
    public void endMatch(MatchRMI match) {
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
    public void print(String str){
        System.out.println(str);
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
    public ArrayList<MatchRMI> getActiveMatches() throws RemoteException {
        return null;
    }

    @Override
    public MatchModel getQueueMatch() throws RemoteException {
        return null;
    }
}

