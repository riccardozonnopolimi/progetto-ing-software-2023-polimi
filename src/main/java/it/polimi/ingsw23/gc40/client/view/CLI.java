package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.client.connection.Connection;

public class CLI extends View{
    public CLI(Connection con){
        super(con);
    }

    @Override
    public void start() {
        System.out.println("ciao sono partito");

        con.addPlayer("Filiberto");

    }
}
