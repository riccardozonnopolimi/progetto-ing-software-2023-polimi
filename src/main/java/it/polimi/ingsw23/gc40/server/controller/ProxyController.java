package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ProxyController implements ConnectionController {
    public LobbyController getLobbyController() {
        return lobbyController;
    }

    private LobbyController lobbyController;


    public ProxyController(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
    }

    public boolean addPlayer(String name){
        // System.out.println(name);
        // System.out.println("#classe controller del server - metodo addplayer");
        // to test
        if(lobbyController.checkExistingNickName(name)){
            System.out.println("utente già esistente: "+name);
            return false;
        }
        else{
            System.out.println("creo utente: "+name);
            lobbyController.addPlayer(name);
        }
        //notify player todo
        return true;
    }
    public boolean newMatch(String string){
        GameController gameController;
        switch (string) {
            case "2" -> {
                gameController = lobbyController.createNewMatch(2);
                System.out.println("Creato Match per 2");
                return true;
            }
            //notify match todo
            case "3" -> {
                gameController = lobbyController.createNewMatch(3);
                System.out.println("Creato Match per 3");
                return true;
            }
            case "4" -> {
                gameController = lobbyController.createNewMatch(4);
                System.out.println("Creato Match per 4");
                return true;
            }
            default -> {
                System.out.println("Errore creazione Match: numero di giocatori richiesto: " + string);
                return false;
            }
        }
        //todo
    }
    public boolean hasQueueMatch(){
        return lobbyController.hasQueueMatch();
    }
    public int addToLastMatch(String name){
        //have to obtain from onlinePlayer in server and return it
        GameController gameController = lobbyController.addToLastMatch(lobbyController.getPlayer(name));
        System.out.println("Aggiunto il giocarore "+name+" al match in attesa");

        //partire nuovo thread socket public model in ascolto di connessioni su porta che restituisco al client che ha chiamato questo metodo


        //notify match todo
        return gameController.getId();
    }


    public boolean playable() throws RemoteException {
        //todo - per ora test su un solo match
        return lobbyController.getActiveMatches().get(0).playable();

    }

    @Override
    public void close() throws RemoteException {
        //todo
    }

    @Override
    public void print(String s) throws RemoteException{
        System.out.println(s);
    }

    @Override
    public void setView(View view) throws RemoteException {
    }


    @Override
    public Object getObj(String str, int n) throws RemoteException {
        if (n == -1) {
            return lobbyController.getPlayer(str).getPrivateModelObservable().getData();
        } else {
            return lobbyController.getActiveMatches().get(n).getPublicModelObservable().getData();
        }
    }

}
