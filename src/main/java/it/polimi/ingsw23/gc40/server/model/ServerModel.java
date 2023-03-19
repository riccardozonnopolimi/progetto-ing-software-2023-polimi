package it.polimi.ingsw23.gc40.server.model;

import java.util.ArrayList;

public class ServerModel {

    private  int newMatchPlayers;
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

    public Match getAvailableMatch(){
        //TO DO
        return null;
    }
    public Match createNewMatch(int numPlayer, Player player){
        //TO DO
        return null;
    }
    public Match addToLastMatch(Player player){
        return null;

        //TO DO

    }
    public Boolean checkExistingNickName(String nickName){
        //TO DO
        return true;
    }
    public void endMatch(Match match){
        //TO DO
    }
    public void setNewMatchPlayers(int num){
        //TO DO
        newMatchPlayers=num;
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

