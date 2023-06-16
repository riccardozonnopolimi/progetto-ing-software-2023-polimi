package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.server.model.Player;
import it.polimi.ingsw23.gc40.server.model.Score;

import java.util.ArrayList;

public class LobbyController {
    private ArrayList<Player> onlinePlayers = new ArrayList<>();
    private ArrayList<Player> offlinePlayers = new ArrayList<>();
    private ArrayList<GameController> activeGameControllers = new ArrayList<>();
    private GameController queueMatch;
    private ArrayList<Score> SavedScores = new ArrayList<>();
    private int numMatch;
    public void setOnlinePlayers(ArrayList<Player> onlinePlayers) {
        this.onlinePlayers = onlinePlayers;
    }

    public ArrayList<Player> getOfflinePlayers() {
        return offlinePlayers;
    }

    public void setOfflinePlayers(ArrayList<Player> offlinePlayers) {
        this.offlinePlayers = offlinePlayers;
    }

    public ArrayList<GameController> getActiveMatches() {
        return activeGameControllers;
    }

    public void setActiveGameControllers(ArrayList<GameController> activeGameControllers) {
        this.activeGameControllers = activeGameControllers;
    }

    public LobbyController() {
        this.numMatch = 0;
    }

    public GameController getQueueMatch() {
        return this.queueMatch;
    }

    public void addPlayer(String name) {
        Player p = new Player(name);
        onlinePlayers.add(p);
    }

    public GameController createNewMatch(int numPlayer) {
        if (numPlayer > 4 || numPlayer < 2 || hasQueueMatch()) return null;
        else {
            queueMatch = new GameController(numPlayer, numMatch + 1);
            numMatch++;
            return queueMatch;
        }
    }

    public void killPlayer(String name) {
        Player p = getPlayer(name);
        onlinePlayers.remove(p);
        offlinePlayers.add(p);

    }

    public GameController matchByPlayer(String name){

        for (int i = 0; i < activeGameControllers.size(); i++) {
            ArrayList<Player> ps = activeGameControllers.get(i).getPlayers();
            for (int j = 0; j < ps.size(); j++) {
                if (ps.get(j).getNickName().equals(name)) {
                    return activeGameControllers.get(i);
                }
            }
        }
        return null;
    }

    public void reBorn(String name){
        Player p = getOfflinePlayer(name);

        onlinePlayers.add(p);
        offlinePlayers.remove(p);

        if (matchByPlayer(name).getActive() == 1) {

            matchByPlayer(name).setNext(getPlayer(name));
            System.out.println("reborn ok");
        }

        matchByPlayer(name).setActive(matchByPlayer(name).getActive() + 1);


    }

    public Player getOfflinePlayer(String name){
        Player p = null;
        for (int i = 0; i < offlinePlayers.size(); i++) {
            if (offlinePlayers.get(i).getNickName().equals(name)) {
                p = offlinePlayers.get(i);
            }
        }
        return p;
    }

    public GameController addToLastMatch(Player player) {
        if (queueMatch.addPlayer(player)) {

            GameController m = queueMatch;
            activeGameControllers.add(m);
            queueMatch = null;
            return m;
        }

        return queueMatch;
    }

    public boolean checkOnlinePlayers(String nickName) {

        if (onlinePlayers.isEmpty()) return false;
        for (int i = 0; i < onlinePlayers.size(); i++) {
            if (onlinePlayers.get(i).getNickName().equals(nickName)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkOfflinePlayers(String nickName) {
        if (!offlinePlayers.isEmpty())
            for (int i = 0; i < offlinePlayers.size(); i++) {
                if (offlinePlayers.get(i).getNickName().equals(nickName)) {
                    return true;
                }
            }
        return false;
    }

    public void endMatch(GameController gameController) {
        //TO TEST TODO
        if (gameController.isEnding() && !gameController.isPlayable()) {
            ArrayList<Integer> scores = new ArrayList<>();
            for (int i = 0; i < gameController.getPlayers().size(); i++) {
                Integer integer = Integer.valueOf(gameController.getPlayers().get(i).totScore());
                scores.add(integer);
            }
            Score s = new Score(gameController.getPlayers(), gameController.winner(), scores);
            SavedScores.add(s);
        }

    }

    public void print(String str) {
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

    public GameController getMatchById(int n) {
        if (queueMatch != null) if (queueMatch.getId() == n) return queueMatch;

        for (int i = 0; i < activeGameControllers.size(); i++)
            if (activeGameControllers.get(i).getId() == n) return activeGameControllers.get(i);
        return null;
    }
}