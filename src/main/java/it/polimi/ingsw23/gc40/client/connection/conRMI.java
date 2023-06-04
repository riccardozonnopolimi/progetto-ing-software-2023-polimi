package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelObserver;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelObserver;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.rmi.RemoteException;
import java.util.Observable;

public class conRMI extends Observable implements ConnectionController {
    ConnectionController stub;
    private View view;

    public conRMI(ConnectionController stub) throws RemoteException {
        this.stub = stub;
        print("#nuova connessione RMI");
    }

    @Override
    public void setView(View view) throws RemoteException {
        this.view = view;
        //start();
    }

    @Override
    public int startRound(String name, int m) throws RemoteException {
        return stub.startRound(name, m);
    }

    @Override
    public ItemTile[] chooseTiles(int mid, int x, int y, int quantity, char direction) throws RemoteException {

        ItemTile[] ht = stub.chooseTiles(mid, x, y, quantity, direction);
        return ht;
    }

    @Override
    public int getAvailableSlots(String nickname, int column) throws RemoteException {
        return stub.getAvailableSlots(nickname, column);
    }

    @Override
    public void insertTile(String nickname, ItemTile handTile, int column) throws RemoteException {
        stub.insertTile(nickname,handTile,column);
    }

    @Override
    public boolean addPlayer(String name) throws RemoteException {
        return stub.addPlayer(name);
    }

    public boolean newMatch(int n) throws RemoteException {
        return stub.newMatch(n);
    }

    public boolean hasQueueMatch() throws RemoteException {
        return stub.hasQueueMatch();
    }

/*    @Override
    public boolean playable(int n) throws RemoteException {
        return stub.playable(n);
    }*/

    @Override
    public int addToLastMatch(String name) throws RemoteException {
        return stub.addToLastMatch(name);
    }


    @Override
    public void close() throws RemoteException {
        this.stub = null;
    }

    @Override
    public void print(String s) throws RemoteException {
        stub.print(s);
    }

   /* @Override
    public void run() {
        //System.out.println("parte thread conrmi");
        while (true) {
            try {
                //System.out.println("##conrmi run view diverso da null nn: "+view.nickname+" mid: "+view.matchId);
                Object obj;
                while (view.matchId == -1) {
                    System.out.print("");
                }

                obj = getObj(view.nickname, -1);
                if (obj != null) {
                    //System.out.println("deserializzo oggetto player: " + ((PrivateModelNetwork) obj).getNickname());
                    view.updatePrivateData((PrivateModelNetwork) obj);
                }
                obj = getObj(view.nickname, view.matchId);
                if (obj != null) {
                    //System.out.println("deserializzo obj match id: " + ((PublicModelNetwork) obj).getIdMatch());
                    view.updatePublicData((PublicModelNetwork) obj);
                }

            } catch (RemoteException e) {
                System.out.println("catch conrmi run errore: " + e);
                throw new RuntimeException(e);
            }
        }
    }
*/
  /*  @Override
    public Object getObj(String s, int n) throws RemoteException {
        return stub.getObj(s, n);
    }*/
    @Override
    public void nextRound(String nickname,int matchId) throws RemoteException{
        stub.nextRound(nickname, matchId);
    }

    @Override
    public void setObservers(String nickname, int matchId) throws RemoteException {

    }

   /* @Override
    public PrivateModelObserver getPrivateObserver(String player) throws RemoteException {
        return stub.getPrivateObserver(player);
    }

    @Override
    public PublicModelObserver getPublicObserver(int matchId) throws RemoteException {
        return stub.getPublicObserver(matchId);
    }
*/
}