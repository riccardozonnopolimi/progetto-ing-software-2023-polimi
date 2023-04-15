package it.polimi.ingsw23.gc40.server;

import it.polimi.ingsw23.gc40.server.model.Match;
import it.polimi.ingsw23.gc40.server.model.Player;
import it.polimi.ingsw23.gc40.server.model.Score;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerMain implements Server {
    private ArrayList<Player> onlinePlayers;
    private ArrayList<Match> activeMatches;
    private Match queueMatch;
    private ArrayList<Score> SavedScores;
    static int port;
    protected ServerMain() throws RemoteException {
    }

    public static void main(String args) throws RemoteException {
         Server stub = null;
        ServerMain obj = new ServerMain();
        Registry registry = null;
        try{
                stub = (Server) UnicastRemoteObject.exportObject(obj, port);

        }catch(RemoteException e){ e.printStackTrace();}
         try{
             registry = LocateRegistry.createRegistry(port);
         }catch(RemoteException e){e.printStackTrace();}
         try{
             registry.bind("Server", stub);
         }catch(RemoteException e){e.printStackTrace();} catch (AlreadyBoundException e) {
             throw new RuntimeException(e);
         }

    }

    @Override
    public ArrayList<Player> getOnlinePlayers() {
        return onlinePlayers;
    }

    @Override
    public Match getQueueMatch() {
        return queueMatch;
    }
    public ArrayList<Match> getActiveMatches(){
        return activeMatches;
    }

}

