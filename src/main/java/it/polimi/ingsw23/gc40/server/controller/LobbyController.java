package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.server.controller.GameController;
import it.polimi.ingsw23.gc40.server.model.Player;
import it.polimi.ingsw23.gc40.server.model.Score;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class LobbyController {
    private ArrayList<Player> onlinePlayers = new ArrayList<>();
    private ArrayList<GameController> activeGameControllers = new ArrayList<>();
    private GameController queueMatch;
    private ArrayList<Score> SavedScores= new ArrayList<>();
    public LobbyController()  {
    }

    public void addPlayer(String name) {
        Player p = new Player(name);
        onlinePlayers.add(p);
    }
    public GameController createNewMatch(int numPlayer) {
        queueMatch = new GameController(numPlayer);
        return queueMatch;
    }
    public GameController addToLastMatch(Player player) {
        //TO TEST
        //if true it means that the match has all the players, so it can start
        if (queueMatch.addPlayer(player)) {
            GameController m = queueMatch;
            activeGameControllers.add(m);
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
    public void endMatch(GameController gameController) {
        //TO TEST
        if(gameController.isEnding() && !gameController.playable()){
            ArrayList<Integer>  scores=new ArrayList<>();
            for(int i = 0; i< gameController.getPlayers().size(); i++) {
                Integer integer = Integer.valueOf(gameController.getPlayers().get(i).totScore());
                scores.add(integer);
            }
            Score s = new Score(gameController.getPlayers(), gameController.winner(), scores);
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

    public ArrayList<GameController> getActiveMatches() throws RemoteException {
        return null;
    }

}

