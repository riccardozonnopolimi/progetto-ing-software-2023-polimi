package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.TileData;
import it.polimi.ingsw23.gc40.server.model.Match;
import it.polimi.ingsw23.gc40.server.model.Player;
import it.polimi.ingsw23.gc40.server.model.Server;

import java.rmi.RemoteException;

public class Controller implements ConnectionController {
    private Server server;

    public Controller(Server server) {
        this.server = server;
    }

    public boolean addPlayer(String name){
        // System.out.println(name);
        // System.out.println("#classe controller del server - metodo addplayer");
        // to test
        if(server.checkExistingNickName(name)){
            System.out.println("utente già esistente: "+name);
            return false;
        }
        else{
            System.out.println("creo utente: "+name);
            server.addPlayer(name);
            Player player = server.getPlayer(name);
        }
        //notify player todo
        return true;
    }
    public boolean newMatch(String string){
        Match match;
        switch (string){
            case "2":
                match=server.createNewMatch(2);
                return true;
            //notify match todo
            case "3":
                match=server.createNewMatch(3);
                return true;
            case "4":
                match=server.createNewMatch(4);
                return true;
            default:
                return false;
        }
        //todo
    }
    public boolean hasQueueMatch(){
        return server.hasQueueMatch();
    }
    public boolean addToLastMatch(String name){
        //have to obtain from onlinePlayer in server and return it
        Match match = server.addToLastMatch(server.getPlayer(name));
        if(match!=null)
        {
            return true;
        }
        //notify match todo
        else
            return false;
    }

    public void extAddPlayer(){
        //todo
    }
    public TileData[] getTile(int x, int y, char direction, int qty){
        return null; //todo
    }
    public void startInserction(int column){
//todo
    }
    public void putTile(int id) {
//todo
    }
    public boolean playable() throws RemoteException {

        //todo test sul queue match
        return server.queueMatch.playable();

    }



}
