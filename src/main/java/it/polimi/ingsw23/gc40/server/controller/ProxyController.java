package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.updateRMI;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

public class ProxyController extends Observable implements ConnectionController, Observer {
    public LobbyController getLobbyController() {
        return lobbyController;
    }

    private LobbyController lobbyController;


    public ProxyController(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
    }

    public boolean addPlayer(String name) {
        if (lobbyController.checkExistingNickName(name)) {
            System.out.println("@PC: utente già esistente: " + name);
            return false;
        } else {
            System.out.println("@PC: creo utente: " + name);
            lobbyController.addPlayer(name);
            lobbyController.getPlayer(name).getObserver().addObserver(this);
        }
        return true;
    }


    public int startRound(String player, int matchId) {

        return lobbyController.getPlayer(player).getBookshelf().placeables();
    }

    @Override
    public ItemTile[] chooseTiles(int mid, int x, int y, int quantity, char direction) throws RemoteException {
        return lobbyController.getMatchById(mid).chooseTiles(x, y, quantity, direction);
    }

    @Override
    public int getAvailableSlots(String nickname, int column) throws RemoteException {
        return lobbyController.getPlayer(nickname).getBookshelf().getAvailableSlots(column);
    }

    @Override
    public void insertTile(String nickname, ItemTile handTile, int column) throws RemoteException {
        lobbyController.getPlayer(nickname).insertIT(handTile, column);
    }

    @Override
    public void nextRound(String nickname, int match) throws RemoteException {

        //check if there is space in the player bookshelf
        if (lobbyController.getPlayer(nickname).getBookshelf().isFull()) {
            lobbyController.getPlayer(nickname).setFirstScore(1);
            lobbyController.getMatchById(match).setEnding(true);
        }
        Player p = lobbyController.getPlayer(nickname);
        GameController m = lobbyController.getMatchById(match);

        //refresh scores
        m.checkScores(p);

        //refill map if needed
        if (lobbyController.getMatchById(match).getMap().needRefill()) {
            lobbyController.getMatchById(match).refill();
        }


        //set turn to next player
        int num;
        if (m.getPlayers().indexOf(p) == m.getPlayers().size() - 1)
            num = 0;
        else
            num = m.getPlayers().indexOf(p) + 1;

        //check if match is completed
        if(lobbyController.getMatchById(match).isEnding() && m.getPlayers().get(num).isStartedFirst())
            lobbyController.getMatchById(match).setPlayable(false);

        m.setNext(m.getPlayers().get(num));

    }

    @Override
    public void addObserver(Remote aRemoteObj) throws RemoteException {

        super.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                try {
                    ((updateRMI) aRemoteObj).update(arg);
                } catch (RemoteException e) {
                    System.err.println(e);
                    throw new RuntimeException(e);
                }
            }
        });


    }


    public boolean newMatch(int n) {
        GameController gameController;

        if (n > 4 || n< 2) {
            System.out.println("Errore creazione Match: numero di giocatori richiesto: " + n);
            return false;
        }

        gameController = lobbyController.createNewMatch(n);
        if (gameController == null) return false;
        else {
            System.out.println("@PC: Creato Match per " + n);
            gameController.getObserver().addObserver(this);
            return true;
        }

    }

    public boolean hasQueueMatch() {
        return lobbyController.hasQueueMatch();
    }

    public int addToLastMatch(String name) {
        GameController gameController = lobbyController.addToLastMatch(lobbyController.getPlayer(name));
        System.out.println("@PC: Aggiunto il giocarore " + name + " al match in attesa");

        return gameController.getId();
    }




    @Override
    public void close() throws RemoteException {
        //todo
    }

    @Override
    public void print(String s) throws RemoteException {
        System.out.println(s);
    }

    @Override
    public void setView(View view) throws RemoteException {
    }


    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
    }
}
