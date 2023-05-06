package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.controller.GameController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PrivateModelNetwork implements Serializable {
    private static final long serialVersionUID = 7823495;
    private int riferPersonalCard;
    private ItemTile[][] bookshelf;
    private GameController gameController;
    public GameController getMatch() {
        return gameController;
    }



    public PrivateModelNetwork(int riferPersonalCard, ItemTile[][] bookshelf) {
        this.riferPersonalCard = riferPersonalCard;
        this.bookshelf = bookshelf;
    }

    public ItemTile[][] getBookshelf() {
        return bookshelf;
    }



    public int getRiferPersonalCard() {
        return riferPersonalCard;
    }
    public void Serialize(String fileName,Object obj) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(obj);
        out.close();
        fileOut.close();
        if(obj instanceof ItemTile[][]){
            System.out.println("abbiamo serializzato la bookshelf");
        }
    }


}
