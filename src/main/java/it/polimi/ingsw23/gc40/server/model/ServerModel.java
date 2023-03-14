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

    public Match getAvailableMatch(){
        //TO DO
        return null;
    }
    public Match createNewMatch(int numPlayer, Player player){
        //TO DO
        return null;
    }
    public void addToLastMatch(Player player){
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
}

