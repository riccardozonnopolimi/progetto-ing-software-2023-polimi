package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class dati {
    public static void main(String[] args) throws Exception{
      //  PrivateModelObservable privateModel = new PrivateModelObservable();





        PublicModelNetwork a = new PublicModelNetwork(new ItemTile[]{new ItemTile(Item.FRAME ),new ItemTile(Item.CAT)}, new int[]{1,2});
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        out = new ObjectOutputStream(bos);
        out.writeObject(a);


       // PrivateModelNetwork b = new PrivateModelNetwork(5,new ItemTile[]{new ItemTile(Item.FRAME ),new ItemTile(Item.CAT)},false,false);
        //out.writeObject(b);
    }
}
