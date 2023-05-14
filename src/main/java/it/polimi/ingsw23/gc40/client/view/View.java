package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelObservable;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelObservable;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

public abstract class View extends Thread {
    ConnectionController con;
    PrivateModelObservable privateModelObservable; //todo capire come aavere lo stesso pmo del player dell servermodel
    PublicModelObservable publicModelObservable; //todo capire come aavere lo stesso pmo del player dell servermodel
    public int matchId;
    public String player;
    private ItemTile[][] bookshelf = null;
    private int pgc;
    public ItemTile[][] lrb;
    public int[] cgs;
    public View(ConnectionController connection) {
        this.con = connection;
    }
    public void addPlayer(String name) throws RemoteException, Exception {
        con.addPlayer(name);
    }

    public abstract void init() throws RemoteException, Exception;

    public PrivateModelNetwork deserializePrivateModel() throws ClassNotFoundException, IOException {
       // System.out.println("entro");
        FileInputStream fileInPrivate;
        ObjectInputStream inPrivate;
        fileInPrivate = new FileInputStream("PrivateModelNetwork");
        inPrivate = new ObjectInputStream(fileInPrivate);
        Object obj = inPrivate.readObject();
      //  System.out.println("obj letto");

        if (obj instanceof ItemTile[][]) {
       //     System.out.println("abbiamo deserializzato la bookshelf");
        }
        return (PrivateModelNetwork) obj;
    }

    public PublicModelNetwork deserializePublicModel() throws ClassNotFoundException, IOException {
       // System.out.println("entro");
        FileInputStream fileInPublic;
        ObjectInputStream inPublic;
        fileInPublic = new FileInputStream("PublicModelNetwork");
        inPublic = new ObjectInputStream(fileInPublic);
        Object obj = inPublic.readObject();
       // System.out.println("obj letto");
        return (PublicModelNetwork) obj;
    }
    @Override
    public void run() {
        PrivateModelNetwork privateData  = new PrivateModelNetwork(null,-1,null);
        PublicModelNetwork publicData = new PublicModelNetwork(null, null, -1,null);

        while (true) {
            //thread (privateModelNetwork)
            //oldOggetto = privateModelNetwork
            //all'interno fa un while(oldOggetto = getOggetto)
            //esce e fa le cose che deve fare per la desearilazzazione
            try {
                if ( !(privateData.equals(deserializePrivateModel()))) {
                    privateData = deserializePrivateModel();
                    updatePrivateData(privateData);
                }
                if ( !(publicData.equals(deserializePublicModel()))) {
                        publicData = deserializePublicModel();
                        updatePublicData(publicData);
                }
                //aggiorna dati publici solo se il match è giusto
                //todo: printare aggiornamenti commonGoalCard

                if(publicData.ended)
                    break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void updatePrivateData(PrivateModelNetwork privateData){
        if (!this.player.equals(privateData.getNickname())) {
            return;
        }
        if (this.bookshelf != privateData.getBookshelf()) {
            this.bookshelf = privateData.getBookshelf();
            //todo da sostituire con funzione print da implementare
            // nelle due view in modi diversi per sfruttare ereditarietà
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.print(bookshelf[i][j].toString() + "|");
                }
                System.out.println();
            }
        }
        if (this.pgc != privateData.getPgc()) {
            this.pgc = privateData.getPgc();
        }
    }
    public void updatePublicData(PublicModelNetwork publicData) {
        if (this.matchId != publicData.getIdMatch())
            return;
        if (this.lrb != publicData.getLrb()) {
            this.lrb = publicData.getLrb();
            //todo da sostituire con funzione print da implementare
            // nelle due view in modi diversi per sfruttare ereditarietà
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(lrb[i][j].toString() + "|");
                }
                System.out.println();
            }

        }
        if (this.cgs != publicData.getCgs()) {
            this.cgs = publicData.getCgs();
        }
    }
}
