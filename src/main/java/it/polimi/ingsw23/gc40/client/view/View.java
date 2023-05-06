package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;

public abstract class View {
    ConnectionController con;
    public View(ConnectionController connection) {
        this.con = connection;
    }

    public void addPlayer(String name) throws RemoteException, Exception{
        con.addPlayer(name);
    }

    public abstract void start() throws RemoteException, Exception;
    public Object deserializza(String fileName) throws ClassNotFoundException, IOException {
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Object obj = in.readObject();
        in.close();
        fileIn.close();
        if(obj instanceof ItemTile[][]){
            System.out.println("abbiamo deserializzato la bookshelf");
        }
        return obj;
    }
}
