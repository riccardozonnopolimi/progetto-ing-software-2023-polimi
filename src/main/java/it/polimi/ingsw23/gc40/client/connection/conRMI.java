package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.rmi.Remote;
import java.rmi.RemoteException;

public class conRMI implements ConnectionController {
    ConnectionController stub;
    private View view;

    /**
     * @param stub server in client side, using ProxyController methods by ConnectionController interface
     * @throws RemoteException if remote communication fails
     */
    public conRMI(ConnectionController stub) throws RemoteException {
        this.stub = stub;
    }

    //calling all method on server using stub

    /**
     * sets client view
     * @param view client view
     * @throws RemoteException if remote communication fails
     */
    @Override
    public void setView(View view) throws RemoteException {
        this.view = view;
    }

    /**
     * @param name player nickname who is going to start the round
     * @param m matchid at which the player belongs
     * @return the max number of item tiles which the player can take by checking empty slots in its bookshelf
     *
     * @throws RemoteException if remote communication fails
     */
    @Override
    public int startRound(String name, int m) throws RemoteException {
        return stub.startRound(name, m);
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

        ItemTile[] ht = stub.chooseTiles(nickname, mid, x, y, quantity, direction);
        return ht;
    }

    /**
     * @param nickname player nickname
     * @param column bookshelf column
     * @return the number of empty slots player bookshelf column has
     * @throws RemoteException if remote communication fails
     */
    @Override
    public int getAvailableSlots(String nickname, int column) throws RemoteException {
        return stub.getAvailableSlots(nickname, column);
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

        return  stub.insertTile(nickname, handTile, column);
    }

    /**
     * @param name nickname written by the client
     * @return true if there is no other player connected with that nickname
     * @throws RemoteException if remote communication fails
     */
    @Override
    public boolean connect(String name) throws RemoteException {
        return stub.connect(name);
    }

    /**
     * @param n number of players chosen in match
     * @return true if the match has successfully been created
     * @throws RemoteException if remote communication fails
     */
    public boolean newMatch(int n) throws RemoteException {
        return stub.newMatch(n);
    }

    /**
     @return true if there is one match which is filling that is not started yet
     * @throws RemoteException if remote communication fails
     */
    public boolean hasQueueMatch() throws RemoteException {
        return stub.hasQueueMatch();
    }


    /**
     * @param name player nickname added to the match
     * @return match id where name is added
     * @throws RemoteException if remote communication fails
     */
    @Override
    public int addToLastMatch(String name) throws RemoteException {
        int n = stub.addToLastMatch(name);
        return n;
    }


    /**
     * closes player connection and deletes it from observers
     * @param nickname player nickname
     * @throws RemoteException if remote communication fails
     */
    @Override
    public void close(String nickname) throws RemoteException {
        stub.close(nickname);
        this.stub = null;
    }


    /**
     * nextround updates player score, refills lrb if needed,checks if the match is completed,
     * sets the next player round
     * @param nickname player nickname
     * @param matchId id match
     * @throws RemoteException if remote communication fails
     */
    @Override
    public void nextRound(String nickname, int matchId) throws RemoteException {

        stub.nextRound(nickname, matchId);
    }

    /**
     * @param aRemoteObj Remote objects sent with RMI connection
     * @param s player nickname
     * @param n matchid
     * @throws RemoteException if remote communication fails
     */
    @Override
    public void addObserver(Remote aRemoteObj, String s, int n) throws RemoteException {
        stub.addObserver(new conUpdateRMI(view), s, n);
    }

    /**
     * @param nickname player nickname
     * @return match id in which the player that crashed will be reconnected, -1 if it's a new player
     * @throws RemoteException if remote communication fails
     * @throws InterruptedException when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity.
     */
    @Override
    public int reconnect(String nickname) throws RemoteException, InterruptedException {
        return stub.reconnect(nickname);
    }

}