package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.updateRMI;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelObserver;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelObserver;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public class ProxyController extends Thread implements ConnectionController {
    private final LobbyController lobbyController;
    private final HashMap<String, Observer> mapObservers = new HashMap<>();
    private final HashMap<String, Remote> mapRemote = new HashMap<>();

    /**
     *ProxyController constructor
     *  @param lobbyController sets lobbyController to this
     */
    public ProxyController(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
        start();
    }

    /**
     * @return lobbyController
     */
    public LobbyController getLobbyController() {
        return lobbyController;
    }

    /**
     * @param name nickname written by the client
     * @return true if there is no other player connected with that nickname
     * @throws RemoteException if remote communication fails
     */
    public boolean connect(String name) throws RemoteException {
        if (lobbyController.checkOnlinePlayers(name)) {
            System.out.println("@PC: utente già esistente: " + name);
            return false;
        } else {
            if (lobbyController.checkOfflinePlayers(name)) {
                System.out.println("@PC: utente riconnesso: " + name);
            } else {
                System.out.println("@PC: creo utente: " + name);
                lobbyController.addPlayer(name);
                //todo fil ??
                //lobbyController.getPlayer(name).getObserver().addObserver(this);
            }
        }
        return true;
    }

    /**
     *Inside this run there is a thread that pings every RMI user every second to check if it has not crashed,
     * if the ping fails, the player crashed
     */
    @Override
    public void run() {
        // thread that ping every user with RMI connection
        while (true) {
            mapRemote.forEach((nickname, remote) -> {

                try {
                    ((updateRMI) remote).ping();
                } catch (RemoteException e) {
                    //if ping fails it means that the player lose his connection, so remove it from active players
                    try {
                        close(nickname);
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            //set thread to sleep because no need to check constantly
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * @param player player nickname who is going to start the round
     * @param matchId at which the player belongs
     * @return the max number of item tiles which the player can take by checking empty slots in its bookshelf
     *
     */
    public int startRound(String player, int matchId) {
        return lobbyController.getPlayer(player).getBookshelf().placeables();
    }

    /**
     * @param nickname player nickname who can make the move
     * @param mid match id of that player
     * @param x axis in map of the tile chosen
     * @param y allies in map of the tile chosen
     * @param quantity the number of tiles the player decided to take
     * @param direction the direction of the tile to take
     * @return item tile array with the tiles chosen
     * @throws RemoteException if remote communication fails
     */
    @Override
    public ItemTile[] chooseTiles(String nickname, int mid, int x, int y, int quantity, char direction) throws RemoteException {
        GameController m = lobbyController.getMatchById(mid);
        //deny the move if the player that calls the method is not allowed to do the move

        if (m.getNext() != null) {
            if (!m.getNext().getNickName().equals(nickname)) {

                return null;
            }
        } else {

            return null;
        }
        //calls the method in the model that take tiles from LRB
        return m.chooseTiles(x, y, quantity, direction);
    }

    /**
     * @param nickname player nickname
     * @param column bookshelf column
     * @return the number of empty slots player bookshelf column has
     * @throws RemoteException if remote communication fails
     */
    @Override
    public int getAvailableSlots(String nickname, int column) throws RemoteException {
        return lobbyController.getPlayer(nickname).getBookshelf().getAvailableSlots(column);
    }

    /**
     * @param nickname player nickname
     * @param handTile tile taken from the lrb
     * @param column player decides to put handtile
     * @return true after calling the method in model that insert handtile in bookshelf
     * @throws RemoteException if remote communication fails
     */
    @Override
    public boolean insertTile(String nickname, ItemTile handTile, int column) throws RemoteException {
        GameController m = lobbyController.matchByPlayer(nickname);
        //deny the move if the player that calls the method is not allowed to do the move
        if (m.getNext() != null) {
            if (!m.getNext().getNickName().equals(nickname)) {
                return false;
            }
        } else {
            return false;
        }
        //calls the method in the model that insert tiles in Bookshelf of Player nickname
        lobbyController.getPlayer(nickname).insertIT(handTile, column);
        return true;
    }

    /**
     * nextround updates player score, refills lrb if needed,checks if the match is completed,
     * sets the next player round
     * @param nickname player nickname
     * @param match id match
     * @throws RemoteException if remote communication fails
     */
    @Override
    public void nextRound(String nickname, int match) throws RemoteException {
        //check if there is space in the player bookshelf
        Player p=lobbyController.getPlayer(nickname);
        GameController m = lobbyController.getMatchById(match);
        if (p.getBookshelf().isFull()) {
            p.setFirstScore(1);
            m.setEnding(true);
        }
        //refresh scores
        m.checkScores(p);
        //refill map if needed
        if (lobbyController.getMatchById(match).getMap().needRefill()) {
            lobbyController.getMatchById(match).refill();
        }
        //set turn to next player (while next is offline)
        if (lobbyController.getMatchById(match).getActive() == 1) {
            //caso in cui si è disconnesso il penultimo utente non devo passare il turno ma devo stare fermo? oppure comunicare qualcosa?
            m.setNext(null);
        } else {
            do {
                int num;
                if (m.getPlayers().indexOf(p) == m.getPlayers().size() - 1)
                    num = 0;
                else
                    num = m.getPlayers().indexOf(p) + 1;
                p = m.getPlayers().get(num);
            } while (lobbyController.checkOfflinePlayers(p.getNickName()));
            m.setNext(p);
        }
        //check if match is completed
        if (lobbyController.getMatchById(match).isEnding() && p.isStartedFirst())
            lobbyController.getMatchById(match).setPlayable(false);
    }

    /**
     * @param s player nickname (hashmap key)
     * @param o obsever player
     */
    public void addSockObs(String s, Observer o) {
        mapObservers.put(s, o);
    }

    /**
     * @param aRemoteObj Remote objects sent with RMI connection
     * @param nickname player nickname
     * @param matchid matchid
     * @throws RemoteException if remote communication fails
     */
    @Override
    public void addObserver(Remote aRemoteObj, String nickname, int matchid) throws RemoteException {
        PublicModelObserver publicobs = lobbyController.getMatchById(matchid).getObserver();
        Player p = lobbyController.getPlayer(nickname);

        PrivateModelObserver privateobs = p.getObserver();
        Observer o = new Observer() {
            /**
             * @param o   the observable object.
             * @param arg an argument passed to the {@code notifyObservers}
             *            method.
             */
            @Override
            public void update(Observable o, Object arg) {
                try {
                    ((updateRMI) aRemoteObj).update(arg);
                } catch (RemoteException e) {
                    //if the notify cannot arrive to the player, it means it loose connection, so it will be removed
                    try {
                        close(nickname);
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        };
        publicobs.addObserver(o);
        privateobs.addObserver(o);
        mapRemote.put(nickname, aRemoteObj);
        mapObservers.put(nickname, o);
    }

    /**
     * @param nickname player nickname
     * @return id match where the player with that nickname belongs
     */
    private int idMatchByPlayer(String nickname) {
        GameController m = lobbyController.matchByPlayer(nickname);
        if (m != null)
            return m.getId();
        return -1;
    }

    /**
     * @param nickname player nickname
     * @return match id in which the player that crashed will be reconnected , -1 if it's a new player
     * @throws RemoteException if remote communication fails
     */
    @Override
    public int reconnect(String nickname) throws RemoteException {
        //return id match if reconnected or -1 if newplayer
        ArrayList<GameController> ms = lobbyController.getActiveMatches();
        int mID = -1;
        boolean find = false;
        if (!ms.isEmpty()) {
            mID = idMatchByPlayer(nickname);
            if (mID != -1) {
                lobbyController.setPlayerOnline(nickname);
                GameController m = lobbyController.matchByPlayer(nickname);
                m.setActive(m.getActive() + 1);
                if (m.getNext() == null && m.getActive() > 1) {
                    m.setNext(m.getWasNext());
                }
            }
        }
        return mID;
    }

    /**
     * @param n number of players chosen in match
     * @return true if the match has successfully been created
     */
    public boolean newMatch(int n) {
        GameController gameController;
        if (n > 4 || n < 2) {
            System.out.println("Errore creazione Match: numero di giocatori richiesto: " + n);
            return false;
        }
        gameController = lobbyController.createNewMatch(n);
        if (gameController == null) return false;
        else {
            System.out.println("@PC: Creato Match per " + n);
            return true;
        }
    }

    /**
     * @return true if there is one match which is filling that is not started yet
     */
    public boolean hasQueueMatch() {
        return lobbyController.hasQueueMatch();
    }

    /**
     * @param name player nickname added to the match
     * @return match id where name is added
     */
    public int addToLastMatch(String name) {
        GameController gameController = lobbyController.addToLastMatch(lobbyController.getPlayer(name));
        System.out.println("@PC: Aggiunto il giocarore " + name + " al match in attesa");
        return gameController.getId();
    }

    /**
     * closes player connection and deletes it from observers
     * @param nickname player nickname
     * @throws RemoteException if remote communication fails
     */
    @Override
    public void close(String nickname) throws RemoteException {
        if (!lobbyController.checkOnlinePlayers(nickname))
            return;
        if (idMatchByPlayer(nickname) == -1)
            return;
        Observer o = mapObservers.get(nickname);
        mapObservers.remove(nickname);
        GameController match = lobbyController.getMatchById(idMatchByPlayer(nickname));
        lobbyController.getPlayer(nickname).getObserver().deleteObserver(o);
        match.getObserver().deleteObserver(o);
        lobbyController.setPlayerOffline(nickname);
        match.setActive(match.getActive() - 1);
        if (match.getNext() != null) {
            if (match.getNext().getNickName().equals(nickname)) {
                System.out.println("tocca a chi si è disconnesso");
                nextRound(nickname, match.getId());
            } else {
                System.out.println("tocca a chi non si è disconnesso");
                if (match.getActive() == 1) {
                    System.out.println("chi è online in questo match è da solo");
                    match.setNext(null);
                }
            }
        }
        System.out.println("disconnesso giocatore: " + nickname);
    }

    /**
     * @param view new view set
     * @throws RemoteException if remote communication fails
     */
    @Override
    public void setView(View view) throws RemoteException {
        //used only in client side
    }

}
