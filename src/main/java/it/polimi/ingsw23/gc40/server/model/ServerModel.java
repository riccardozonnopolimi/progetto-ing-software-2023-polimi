package it.polimi.ingsw23.gc40.server.model;

public class ServerModel {

    private  int newMatchPlayers;
    private  ArrayList<Players> onlinePlayers;
    private ArrayList<Match> activeMatches;
    private Match queueMatch;
    private ArrayList<Score> SavedScores;

    public ServerModel() {
    }

    public Match getAvailableMatch(){
        return null;
    }
    public Match createNewMatch(int numPlayer, Player player){
        return null;
    }
    public void addToLastMatch(Player player){

    }
    public Boolean checkExistingNickName(String nickName){
        return true;
    }
    public void endMatch(Match match){

    }
    public void setNewMatchPlayers(int num){
        newMatchPlayers=num;
    }
}

