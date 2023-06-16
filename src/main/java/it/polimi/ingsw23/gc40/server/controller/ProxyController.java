package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.updateRMI;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelObserver;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelObserver;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.rmi.ConnectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public class ProxyController implements ConnectionController {
    public LobbyController getLobbyController() {
        return lobbyController;
    }

    private LobbyController lobbyController;
    private HashMap<String, Observer> mapObs = new HashMap<>();


    public ProxyController(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
    }

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
                //lobbyController.getPlayer(name).getObserver().addObserver(this);
            }
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
            Player p;
            if (lobbyController.getOfflinePlayer(nickname) == null) {
                p = lobbyController.getPlayer(nickname);
            } else {
                p = lobbyController.getOfflinePlayer(nickname);
            }
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
            do {
                int num;
                if (m.getPlayers().indexOf(p) == m.getPlayers().size() - 1)
                    num = 0;
                else
                    num = m.getPlayers().indexOf(p) + 1;

                p = m.getPlayers().get(num);
            } while (lobbyController.checkOfflinePlayers(p.getNickName()));


            m.setNext(p);

            //check if match is completed
            if (lobbyController.getMatchById(match).isEnding() && p.isStartedFirst())
                lobbyController.getMatchById(match).setPlayable(false);



    }

    public void addSockObs(String s, Observer o) {
        mapObs.put(s, o);
    }

    @Override
    public void addObserver(Remote aRemoteObj, String nickname, int matchid) throws RemoteException {
            PublicModelObserver publicobs = lobbyController.getMatchById(matchid).getObserver();
            PrivateModelObserver privateobs = lobbyController.getPlayer(nickname).getObserver();
            Observer o = new Observer() {
                @Override
                public void update(Observable o, Object arg) {
                    try {
                        ((updateRMI) aRemoteObj).update(arg);
                    } catch (RemoteException e) {
                        System.out.println("Entro in catch nel proxycontroller quando un client si è disconnesso in rmi e non riceve la notify... questo perchè invio tutte le notify a tutti, male. \n proxycontroller, metodo update dentro addobserver");
                        try {
                            close(nickname);
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }
                        /*System.err.println(e);
                        throw new RuntimeException(e);*/
                    }
                }
            };
            publicobs.addObserver(o);
            privateobs.addObserver(o);


            mapObs.put(nickname, o);


    }

    private int idMatchByPlayer(String nickname) {
        ArrayList<GameController> ms = lobbyController.getActiveMatches();

        for (int i = 0; i < ms.size(); i++) {
            ArrayList<Player> ps = ms.get(i).getPlayers();
            for (int j = 0; j < ps.size(); j++) {
                if (ps.get(j).getNickName().equals(nickname)) {
                    return ms.get(i).getId();


                }
            }

        }
        return -1;
    }

    @Override
    public int reconnect(String nickname) throws RemoteException {
        //return id match if reconnected or -1 if newplayer
        ArrayList<GameController> ms = lobbyController.getActiveMatches();
        int mID = -1;
        boolean trovato = false;
        if (!ms.isEmpty()) {
            mID = idMatchByPlayer(nickname);
            lobbyController.unkillPlayer(nickname);
        }

        return mID;
    }


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

    public boolean hasQueueMatch() {
        return lobbyController.hasQueueMatch();
    }

    public int addToLastMatch(String name) {
        GameController gameController = lobbyController.addToLastMatch(lobbyController.getPlayer(name));
        System.out.println("@PC: Aggiunto il giocarore " + name + " al match in attesa");

        return gameController.getId();
    }


    @Override
    public void close(String nickname) throws RemoteException {
        if (!lobbyController.checkOnlinePlayers(nickname))
            return;
        if (idMatchByPlayer(nickname) == -1)
            return;

        Observer o = mapObs.get(nickname);
        mapObs.remove(nickname);

        lobbyController.getPlayer(nickname).getObserver().deleteObserver(o);
        lobbyController.getMatchById(idMatchByPlayer(nickname)).getObserver().deleteObserver(o);
        lobbyController.killPlayer(nickname);
        if (lobbyController.getMatchById(idMatchByPlayer(nickname)).getNext().getNickName().equals(nickname)) {
            nextRound(nickname, idMatchByPlayer(nickname));
        }

        System.out.println("disconnesso giocatore: " + nickname);
    }

    @Override
    public void setView(View view) throws RemoteException {
    }


}
