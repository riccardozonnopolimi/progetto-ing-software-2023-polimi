package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Scanner;


public abstract class View  {
    ConnectionController con;
    public int matchId = -1;
    public String player = "temp";
    public ItemTile[][] bookshelf = null;
    public int pgc;
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





    public void updatePrivateData(PrivateModelNetwork privateData) {
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
