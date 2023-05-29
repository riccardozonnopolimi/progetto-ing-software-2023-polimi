package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.server.controller.GameController;
import it.polimi.ingsw23.gc40.server.model.Player;
import it.polimi.ingsw23.gc40.server.model.Score;

import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class LobbyController extends Thread {
    private ArrayList<Player> onlinePlayers = new ArrayList<>();
    private ArrayList<GameController> activeGameControllers = new ArrayList<>();
    private GameController queueMatch;
    private ArrayList<Score> SavedScores = new ArrayList<>();
    private int numMatch ;

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
        queueMatch = new GameController(numPlayer, numMatch + 1);
        numMatch++;
        return queueMatch;
    }

    public GameController addToLastMatch(Player player) {
        //TO TEST
        //if true it means that the match has all the players, so it can start
        System.out.println("@@lobbycontroller->addtolastmatch");
        if (queueMatch.addPlayer(player)) {
            //System.out.println("@@if");

            GameController m = queueMatch;
            activeGameControllers.add(m);
           // this.start();
            queueMatch = null;
            return m;
        }

        return queueMatch;
    }

    public Boolean checkExistingNickName(String nickName) {
        //TO TEST

        if (onlinePlayers.isEmpty()) return false;
        for (int i = 0; i < onlinePlayers.size(); i++) {
            if (onlinePlayers.get(i).getNickName().equals(nickName)) {
                return true;
            }
        }
        return false;
    }

    public void endMatch(GameController gameController) {
        //TO TEST
        if (gameController.isEnding() && !gameController.playable()) {
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


    public ArrayList<Player> getOnlinePlayers() throws RemoteException {
        return null;
    }

    public ArrayList<GameController> getActiveMatches() throws RemoteException {
        return null;
    }

    @Override
    public void run() {
        GameController match = this.activeGameControllers.get(this.activeGameControllers.size() - 1);
        while (match.playable()) {
            System.out.println("E' il turno di " + match.getNext().getNickName());
            match.round(match.getNext());
        }

    }


    public GameController getMatchById(int n) {
        if(queueMatch!=null)
            if(queueMatch.getId()==n)
                return queueMatch;

        for (int i=0; i<activeGameControllers.size(); i++)
            if(activeGameControllers.get(i).getId()==n)
                return activeGameControllers.get(i);
        return null;
    }
}