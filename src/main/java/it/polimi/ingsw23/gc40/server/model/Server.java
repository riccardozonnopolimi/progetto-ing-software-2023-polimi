package it.polimi.ingsw23.gc40.server.model;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Server {
    private ArrayList<Player> onlinePlayers = new ArrayList<>();
    private ArrayList<Match> activeMatches= new ArrayList<>();
    public Match queueMatch; //todo private
    private ArrayList<Score> SavedScores= new ArrayList<>();
    public Server()  {
    }

    public void addPlayer(String name) {
        Player p = new Player(name);
        onlinePlayers.add(p);
    }
    public Match createNewMatch(int numPlayer) {
        queueMatch = new Match(numPlayer);
        return queueMatch;
    }
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
    public void print(String str){
        System.out.println(str);
    }


    public boolean hasQueueMatch() {
        return (queueMatch != null);
    }
    public Player getPlayer(String nome) {
        for (int i = 0; i < onlinePlayers.size(); i++) {
            if (onlinePlayers.get(i).getNickName().equals(nome)) {
                return onlinePlayers.get(i);
            }
        }
        return null;
    }


    public ArrayList<Player> getOnlinePlayers() throws RemoteException {
        return null;
    }

    public ArrayList<Match> getActiveMatches() throws RemoteException {
        return null;
    }

}

