package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Scanner;


public abstract class View extends Thread {
    ConnectionController con;
    public int matchId;
    public String player;
    private ItemTile[][] bookshelf = null;
    private int pgc;
    public ItemTile[][] lrb;
    public int[] cgs;
    public Socket privateModel_socket;
    public Socket publicModel_socket;



    public View(ConnectionController connection){
        this.con = connection;

    }

    public void addPlayer(String name) throws RemoteException, Exception {
        con.addPlayer(name);
    }

    public abstract void init() throws RemoteException, Exception;






    @Override
    public void run() {

        PrivateModelNetwork privateData = new PrivateModelNetwork(null,-1,null);
        PublicModelNetwork publicData = new PublicModelNetwork(false, null);
        boolean ended = false;

        ObjectInputStream ois = null;
        try {
            ois = con.getOIS();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


        while(true){
            Object public_obj = null;
            Object private_obj = null;

            try {
                if(ois.readLine().equals("#deserializePrivate")) {
                    try {
                        private_obj = ois.readObject();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if(ois.readLine().equals("#deserializePublic")) {
                    try {
                        public_obj = ois.readObject();
                    } catch (IOException e) {

                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(!(privateData.equals(private_obj))) {
                privateData = (PrivateModelNetwork) private_obj;
                updatePrivateData(privateData);
            }
            if(!(publicData.equals(public_obj))){
                publicData = (PublicModelNetwork) public_obj;
                updatePublicData(publicData);
                ended = publicData.ended;
            }


            //aggiorna dati publici solo se il match è giusto
            //todo: printare aggiornamenti commonGoalCard

            if(ended)  break;
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
