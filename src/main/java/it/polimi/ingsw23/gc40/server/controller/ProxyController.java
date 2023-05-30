package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

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

    public boolean newMatch(int n) {
        GameController gameController;

        if (n > 4) {
            System.out.println("Errore creazione Match: numero di giocatori richiesto: " + n);
            return false;
        }

        gameController = lobbyController.createNewMatch(n);
        System.out.println("Creato Match per " + n);
        return true;

    }

    public boolean hasQueueMatch() {
        return lobbyController.hasQueueMatch();
    }

    public int addToLastMatch(String name) {
        //have to obtain from onlinePlayer in server and return it
        GameController gameController = lobbyController.addToLastMatch(lobbyController.getPlayer(name));
        System.out.println("Aggiunto il giocarore " + name + " al match in attesa");

        //partire nuovo thread socket public model in ascolto di connessioni su porta che restituisco al client che ha chiamato questo metodo


        //notify match todo
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
