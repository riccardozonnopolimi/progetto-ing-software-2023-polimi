package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.server.model.Player;
import it.polimi.ingsw23.gc40.server.model.Score;

import java.util.ArrayList;
import java.util.Observer;

public class LobbyController {
    private ArrayList<Player> onlinePlayers = new ArrayList<>();
    private ArrayList<Player> offlinePlayers = new ArrayList<>();
    private ArrayList<GameController> activeGameControllers = new ArrayList<>();
    private GameController queueMatch;
    private final ArrayList<Score> SavedScores = new ArrayList<>();
    private int numMatch;

    //todo metodi inutilizzati
    public void setOnlinePlayers(ArrayList<Player> onlinePlayers) {
        this.onlinePlayers = onlinePlayers;
    }

    public ArrayList<Player> getOfflinePlayers() {
        return offlinePlayers;
    }

    public void setOfflinePlayers(ArrayList<Player> offlinePlayers) {
        this.offlinePlayers = offlinePlayers;
    }

    /**
     * @return the list of current active matches
     */
    public ArrayList<GameController> getActiveMatches() {
        return activeGameControllers;
    }

    public void setActiveGameControllers(ArrayList<GameController> activeGameControllers) {
        this.activeGameControllers = activeGameControllers;
    }

    /**
     * LobbyController constructor. At first there are no match inside
     */
    public LobbyController() {
        this.numMatch = 0;
    }

    //todo inutilizzato
    public GameController getQueueMatch() {
        return this.queueMatch;
    }

    /**
     * @param name player nickname added to online players list
     */
    public void addPlayer(String name) {
        Player p = new Player(name);
        onlinePlayers.add(p);
    }

    /**
     * @param numPlayer of the new match to create.
     * @return the GameController if the match is succesfully created, null if the numPlayers is not allowed or if
     * there is a queuematch
     */
    public GameController createNewMatch(int numPlayer) {
        if (numPlayer > 4 || numPlayer < 2 || hasQueueMatch()) return null;
        else {
            queueMatch = new GameController(numPlayer, numMatch + 1);
            numMatch++;
            return queueMatch;
        }
    }

    /**
     * @param name nickname of the player who was playing that crashed
     */
    public void setPlayerOffline(String name) {
        Player p = getPlayer(name);
        onlinePlayers.remove(p);
        offlinePlayers.add(p);
    }

    /**
     * @param name player nickname
     * @return the match in which there is the player with that nickname
     */
    public GameController matchByPlayer(String name) {

        //checking if effectively the player exists and returning the match
        for (int i = 0; i < activeGameControllers.size(); i++) {
            ArrayList<Player> ps = activeGameControllers.get(i).getPlayers();
            for (int j = 0; j < ps.size(); j++) {
                if (ps.get(j).getNickName().equals(name)) {
                    return activeGameControllers.get(i);
                }
            }
        }
        //if there is not this nickname
        return null;
    }

    /**
     * set the player online if reconnected
     *
     * @param name of player that was offline
     */
    public void setPlayerOnline(String name) {
        Player p = getOfflinePlayer(name);
        onlinePlayers.add(p);
        offlinePlayers.remove(p);
    }

    /**
     * @param name player nickname that is offline
     * @return the player that crashed from offline player list
     */
    private Player getOfflinePlayer(String name) {
        for (int i = 0; i < offlinePlayers.size(); i++) {
            if (offlinePlayers.get(i).getNickName().equals(name)) {
                return offlinePlayers.get(i);
            }
        }
        return null;
    }

    /**
     * @param player
     * @return the match in which player is added, if it is the last player to start the match
     * queue match becomes null
     */
    public GameController addToLastMatch(Player player) {
        if (queueMatch.addPlayer(player)) {

            GameController m = queueMatch;
            activeGameControllers.add(m);
            queueMatch = null;
            return m;
        }

        return queueMatch;
    }

    /**
     * @param nickName of the player
     * @return true if the nicknames exists in one match
     */
    public boolean checkOnlinePlayers(String nickName) {

        if (onlinePlayers.isEmpty())
            return false;

        for (int i = 0; i < onlinePlayers.size(); i++) {
            if (onlinePlayers.get(i).getNickName().equals(nickName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param nickName of the player
     * @return true if the offline players arraylist contains the player with that nickname
     */
    public boolean checkOfflinePlayers(String nickName) {
        if (!offlinePlayers.isEmpty())
            for (int i = 0; i < offlinePlayers.size(); i++) {
                if (offlinePlayers.get(i).getNickName().equals(nickName)) {
                    return true;
                }
            }
        return false;
    }

    /**
     * Called when the last round of the match has been played
     * ( last round after one player has filled the whole bookshelf)
     * @param m GameController of almost-ended match
     */
    //todo commentare
    public void endMatch(GameController m) {
        //TO TEST TODO
        if (m.isEnding() && !m.isPlayable()) {
            /*ArrayList<Integer> scores = new ArrayList<>();
            for (int i = 0; i < gameController.getPlayers().size(); i++) {
                Integer integer = Integer.valueOf(gameController.getPlayers().get(i).totScore());
                scores.add(integer);
            }
            Score s = new Score(gameController.getPlayers(), gameController.winner(), scores);
            SavedScores.add(s);*/
            m.winner();
        }
    }

    /**
     *It takes the game controller, deletes all of his observers and removes from online player list and offline
     * player list all players linked to m
     *  @param m GameController of ended match
     * @param observer
     */
    public void destroyMatch(GameController m, Observer observer){
        m.getObserver().deleteObserver(observer);

        m.setPndf(m.getPndf() + 1);
        if (m.getPndf()== m.getActive()) {
            for (int i = 0; i < m.getPlayers().size(); i++) {
                if (checkOnlinePlayers(m.getPlayers().get(i).getNickName()))
                    onlinePlayers.remove(m.getPlayers().get(i));
                else
                    offlinePlayers.remove(m.getPlayers().get(i));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            m.getPlayers().clear();
            activeGameControllers.remove(m);
        }
    }

    //todo ??
    public void print(String str) {
        System.out.println(str);
    }

    /**
     * @return true if queuematch is != null
     */
    public boolean hasQueueMatch() {
        return (queueMatch != null);
    }

    /**
     * @param name  nickname of the player
     * @return player with name as nickname
     */
    public Player getPlayer(String name) {
        for (int i = 0; i < onlinePlayers.size(); i++) {
            if (onlinePlayers.get(i).getNickName().equals(name)) {
                return onlinePlayers.get(i);
            }
        }

        return getOfflinePlayer(name);
    }

    /**
     * @param n id of the match passed by parameter
     * @return the queue match if its id is == to n,activeGame controller if is one of the list
     */
    public GameController getMatchById(int n) {
        if (queueMatch != null) if (queueMatch.getId() == n) return queueMatch;

        for (int i = 0; i < activeGameControllers.size(); i++)
            if (activeGameControllers.get(i).getId() == n) return activeGameControllers.get(i);
        return null;
    }
}