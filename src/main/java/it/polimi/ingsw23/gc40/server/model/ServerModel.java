package it.polimi.ingsw23.gc40.server.model;

import java.util.ArrayList;

public class ServerModel {

    private ArrayList<Player> onlinePlayers;
    private ArrayList<Match> activeMatches;
    private Match queueMatch;
    private ArrayList<Score> SavedScores;

    public ServerModel() {
        onlinePlayers = new ArrayList<>();
        activeMatches = new ArrayList<>();
    }

    public void addPlayer(String name) {
        Player p = new Player(name);
        onlinePlayers.add(p);
    }

    public Match createNewMatch(int numPlayer, Player player) {
        //TO TEST
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
        for (int i = 0; i < onlinePlayers.size(); i++) {
            if (onlinePlayers.get(i).getNickName().equals(nickName)) {
                return true;
            }
        }
        return false;
    }

    public void endMatch(Match match) {
        if(match.isEnding() && !match.playable()){
            ArrayList<Integer>  scores=new ArrayList<>();
            for(int i=0; i<match.getPlayers().size(); i++)
                scores.add(new Integer(match.getPlayers().get(i).totScore()));
            Score s = new Score(match.getPlayers(), match.winner(), scores);
            SavedScores.add(s);
        }

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

}

