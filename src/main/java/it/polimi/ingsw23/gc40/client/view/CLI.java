package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.client.connection.Connection;

import java.rmi.RemoteException;

public class CLI extends View{
    public CLI(Connection con){
        super(con);
    }

    @Override
    public void start() throws RemoteException, Exception {
        System.out.println("#metodo start della view CLI");

        con.addPlayer("Filiberto");
        con.close();
    }
}
