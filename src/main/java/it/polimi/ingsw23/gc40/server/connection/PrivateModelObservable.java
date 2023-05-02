package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.netModel.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.server.Bookshelf;
import it.polimi.ingsw23.gc40.server.ItemTile;
import it.polimi.ingsw23.gc40.server.Match;
import it.polimi.ingsw23.gc40.server.PersonalGoalCard;

import java.util.Observable;
import java.util.Observer;

public class PrivateModelObservable extends Observable implements Observer {
    private PrivateModelNetwork data;

    public PrivateModelObservable(Bookshelf bookshelf, PersonalGoalCard pgc, Match m){
        /*
        bookshelf.addObserver((o,  _arg) -> {
            ItemTile[][] arg = (ItemTile[][])_arg;
            data.setBookshelf(arg);
            this.notifyObservers(this.data);
        });

        pgc.addObserver((o,  _arg) -> {
            PersonalGoalCard arg = (PersonalGoalCard) _arg;
            data.setRiferPersonalCard(-1);
            this.notifyObservers(this.data);
        }); */
        data = new PrivateModelNetwork();
        m.addObserver(this);
        m.notifyObservers();
        //m.

        // TODO implement removelistener on destructor
    }

    @Override
    public void update(Observable o, Object arg) {
       //this.data.setBookshelf((Bookshelf) arg);
    }

    public PrivateModelNetwork getData(){
        return this.data;
    }
}
