package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.client.connection.Connection;

public abstract class View {
    Connection con;
    public View(Connection connection) {
        this.con = connection;
    }

    public void addPlayer(String name){
        con.addPlayer(name);
    }

    public abstract void start();
}
