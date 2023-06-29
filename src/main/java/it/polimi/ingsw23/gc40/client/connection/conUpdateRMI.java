package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.updateRMI;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class conUpdateRMI extends UnicastRemoteObject implements updateRMI {

    private final View view;


    /**
     * @param view client view
     * @throws RemoteException if remote communication fails
     */
    public conUpdateRMI(View view) throws RemoteException {
        super();
        this.view = view;
    }

    /**
     * ping to check if the player not crashed
     * @throws RemoteException if remote communication fails
     */
    public void ping() throws RemoteException{
    }


    /**
     * @param obj privatemodel or publicmodel that send update
     * @throws RemoteException if remote communication fails
     */
    @Override
    public void update(Object obj) throws RemoteException {
        //dividing public or private update
        if (obj instanceof PrivateModelNetwork)
            view.updatePrivateData((PrivateModelNetwork) obj);
        if (obj instanceof PublicModelNetwork)
            view.updatePublicData((PublicModelNetwork) obj);
    }
}
