package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.netModel.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.server.Bookshelf;
import it.polimi.ingsw23.gc40.server.ItemTile;
import it.polimi.ingsw23.gc40.server.Match;

import java.util.Observable;

public class PrivateModelObservable extends Observable {
    private PrivateModelNetwork data;

    public PrivateModelObservable(Bookshelf bookshelf, Match match){
        bookshelf.addObserver((o,  _arg) -> {
            ItemTile[][] arg = (ItemTile[][])_arg;
            data.setBookshelf(arg);
            this.notifyObservers(this.data);
        });

        match.addObserver((o,  _arg) -> {
            Match arg = (Match) _arg;
            data.setRiferPersonalCard(-1);
            this.notifyObservers(this.data);
        });

        // TODO implement removelistener on destructor
    }

    public PrivateModelNetwork getData(){
        return this.data;
    }
}
