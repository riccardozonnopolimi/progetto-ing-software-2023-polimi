package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.client.view.View;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;

import java.rmi.RemoteException;

public class conRMI extends Thread implements ConnectionController {
    ConnectionController stub;
    private View view;

    @Override
    public void setView(View view) throws RemoteException {
        this.view = view;
        start();
    }

    public conRMI(ConnectionController stub) throws RemoteException {
        this.stub = stub;
        print("#nuova connessione RMI");
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

    @Override
    public boolean playable() throws RemoteException {
        return stub.playable();
    }

    @Override
    public int addToLastMatch(String name) throws RemoteException {
        return stub.addToLastMatch(name);
    }


    /* /@Override
    public void serverPrint(String s) throws RemoteException {
        stub.print(s);
    }
    */
    @Override
    public void close() throws RemoteException {
        this.stub = null;
    }

    @Override
    public void print(String s) throws RemoteException {
        stub.print(s);
    }


    @Override
    public void run() {

        System.out.println("parte thread conrmi");
        while (true) {
            try {
                    //System.out.println("##conrmi run view diverso da null nn: "+view.nickname+" mid: "+view.matchId);
                    Object obj;
                    while (!view.isOk()) {
                        System.out.print("");
                    }
                    if (view.isOk()) {


                        System.out.println("nickname diverso da null");


                        obj = getObj(view.nickname, -1);


                        System.out.println("nick client: "+view.nickname);
                        if(obj==null)
                            System.out.println("obj null");
                        else
                            System.out.println("nick server: "+((PrivateModelNetwork) obj).getNickname());


                        view.updatePrivateData((PrivateModelNetwork) obj);
                        System.out.println("### conRmi run - ricevo privatedata nickname: "+((PrivateModelNetwork) obj).getNickname());
                    }

                    if (view.isOk()) {
                        System.out.println("matchid diverso da menouno");
                        obj = getObj("", view.matchId);
                        view.updatePublicData((PublicModelNetwork) obj);
                        System.out.println("### conRmi run - ricevo publicdata match id: "+((PublicModelNetwork) obj).idMatch);
                    }
                    


            } catch (RemoteException e) {
                System.out.println("catch conrmi run errore: "+e);
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Object getObj(String s, int n) throws RemoteException {
        return stub.getObj(s, n);
    }


}
