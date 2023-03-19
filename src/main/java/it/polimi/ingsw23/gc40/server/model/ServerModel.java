package it.polimi.ingsw23.gc40.server.model;

import java.util.ArrayList;

public class ServerModel {

    private  ArrayList<Player> onlinePlayers;
    private ArrayList<Match> activeMatches;
    private Match queueMatch;
    private ArrayList<Score> SavedScores;

    public ServerModel() {
        //TO DO
    }

    public boolean addPlayer(String name) {
        if(this.checkExistingNickName(name)){
                return false;
        } else {
            Player p =new Player(name);
          this.onlinePlayers.add(p);

          return true;
        }

    }

    public Match createNewMatch(int numPlayer, Player player){
        //TO TEST
        queueMatch = new Match(numPlayer);

        return queueMatch;

    }
    public Match addToLastMatch(Player player){
        //TO TEST
        //if true it means that the match has all the players, so it can start
        if(queueMatch.addPlayer(player)){
            Match m = queueMatch;
            activeMatches.add(m);
            queueMatch=null;
            return m;
        }
        return queueMatch;
    }
    public Boolean checkExistingNickName(String nickName){
        //TO TEST
        for (int i=0; i<onlinePlayers.size(); i++)
            if(onlinePlayers.get(i).getNickName().equals(nickName))
                return true;
        return false;
    }
    public void endMatch(Match match){
        //TO DO
    }


    public boolean hasQueueMatch() {
        return (queueMatch!=null);
    }

    public Player getPlayer(String nome) {
        for(int i=0; i<onlinePlayers.size(); i++){
            if(onlinePlayers.get(i).getNickName().equals(nome)) {
                return onlinePlayers.get(i);
            }
        }
        return null;
    }

}

