package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.server.Bookshelf;
import it.polimi.ingsw23.gc40.server.Match;

import java.util.Observable;

public abstract class Connection {


    //Observable publicModel;     // Modello Pubblico
    PrivateModelObservable privateModel;    // Modello Privato

    ConnectionController controller;

    public Connection(ConnectionController controller, Bookshelf bookshelf, Match match) {
        this.controller=controller;
        this.privateModel = new PrivateModelObservable(bookshelf, match);
    }
}
