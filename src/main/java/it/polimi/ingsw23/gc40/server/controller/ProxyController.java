package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.rmi.RemoteException;

public class ProxyController implements ConnectionController {
    public LobbyController getLobbyController() {
        return lobbyController;
    }

    private LobbyController lobbyController;


    public ProxyController(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
    }

    public boolean addPlayer(String name) {
        // System.out.println(name);
        // System.out.println("#classe controller del server - metodo addplayer");
        // to test
        if (lobbyController.checkExistingNickName(name)) {
            System.out.println("utente già esistente: " + name);
            return false;
        } else {
            System.out.println("creo utente: " + name);
            lobbyController.addPlayer(name);
        }
        //notify player todo
        return true;
    }


    public int startRound(String player, int matchId) {
        if (lobbyController.getMatchById(matchId).getMap().needRefill()) {
            lobbyController.getMatchById(matchId).refill();
        }
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
        if (lobbyController.getPlayer(nickname).getBookshelf().getFull()) {
            //p.getBookshelf().setFull(true);
            lobbyController.getPlayer(nickname).setFirstScore(1);
            lobbyController.getMatchById(match).setEnding(true);
        }
        Player p = lobbyController.getPlayer(nickname);
        GameController m = lobbyController.getMatchById(match);

        //refresh scores
        m.checkScores(p);

        //set turn to next player
        int num;
        if (m.getPlayers().indexOf(p) == m.getPlayers().size() - 1)
            num = 0;
        else
            num = m.getPlayers().indexOf(p) + 1;
        m.setNext(m.getPlayers().get(num));

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
            System.out.println("Creato Match per " + n);
            return true;
        }

    }

    public boolean hasQueueMatch() {
        return lobbyController.hasQueueMatch();
    }

    public int addToLastMatch(String name) {
        GameController gameController = lobbyController.addToLastMatch(lobbyController.getPlayer(name));
        System.out.println("Aggiunto il giocarore " + name + " al match in attesa");

        return gameController.getId();
    }


    public boolean playable(int n) throws RemoteException {
        return lobbyController.getMatchById(n).playable();

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
    public Object getObj(String str, int n) throws RemoteException {
        if (n == -1) {
            if (lobbyController.getPlayer(str).getPrivateModelObserver().getNewData()) {
                lobbyController.getPlayer(str).getPrivateModelObserver().setNewData(false);
                return lobbyController.getPlayer(str).getPrivateModelObserver().getData();
            } else return null;
        } else {

            if (lobbyController.getMatchById(n).getPublicModelObserver().getDownloaded().remove(str))
                return lobbyController.getMatchById(n).getPublicModelObserver().getData();
            else return null;

        }
    }

}
