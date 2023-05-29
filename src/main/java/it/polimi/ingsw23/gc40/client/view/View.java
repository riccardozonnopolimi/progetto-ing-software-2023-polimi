package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;

import java.rmi.RemoteException;

public abstract class View {
    ConnectionController con;
    public int matchId;
    public String nickname;
    public PrivateModelNetwork player_data;
    public PublicModelNetwork match_data;

    public boolean isOk() {
        return ok;
    }

    public boolean ok;

    public View(ConnectionController connection) {
        this.con = connection;
        this.match_data = null;
        this.player_data = null;
        this.matchId = -1;
        this.nickname = "";
    }

    public void addPlayer(String name) throws RemoteException, Exception {
        con.addPlayer(name);
    }

    public abstract void init() throws RemoteException, Exception;

    public void updatePrivateData(PrivateModelNetwork privateData) {
        this.player_data = privateData;
    }

    public void updatePublicData(PublicModelNetwork publicData) {
        this.match_data = publicData;
    }
}
