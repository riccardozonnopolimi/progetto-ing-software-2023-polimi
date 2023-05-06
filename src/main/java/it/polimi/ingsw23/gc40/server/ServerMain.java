package it.polimi.ingsw23.gc40.server;

import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.server.connection.ConnectionOpener;
import it.polimi.ingsw23.gc40.server.controller.LobbyController;
import it.polimi.ingsw23.gc40.server.controller.ProxyController;
import it.polimi.ingsw23.gc40.server.model.Bookshelf;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        LobbyController lobbyController = new LobbyController();
        ProxyController proxyController = new ProxyController(lobbyController);
        ConnectionOpener a = new ConnectionOpener(proxyController);
        a.rmiInit();
        a.socketInit();






        Bookshelf bookshelf = new Bookshelf();

        ItemTile book0 = new ItemTile(Item.BOOK);
        ItemTile book1 = new ItemTile(Item.BOOK);
        ItemTile book2 = new ItemTile(Item.BOOK);
        ItemTile book3 = new ItemTile(Item.BOOK);
        ItemTile book4 = new ItemTile(Item.BOOK);

        ItemTile cat0 = new ItemTile(Item.CAT);
        ItemTile cat1 = new ItemTile(Item.CAT);
        ItemTile cat2 = new ItemTile(Item.CAT);
        ItemTile cat3 = new ItemTile(Item.CAT);
        ItemTile cat4 = new ItemTile(Item.CAT);

        ItemTile plant0 = new ItemTile(Item.PLANT);
        ItemTile plant1 = new ItemTile(Item.PLANT);
        ItemTile plant2 = new ItemTile(Item.PLANT);
        ItemTile plant3 = new ItemTile(Item.PLANT);
        ItemTile plant4 = new ItemTile(Item.PLANT);

        ItemTile frame0 = new ItemTile(Item.FRAME);
        ItemTile frame1 = new ItemTile(Item.FRAME);
        ItemTile frame2 = new ItemTile(Item.FRAME);
        ItemTile frame3 = new ItemTile(Item.FRAME);
        ItemTile frame4 = new ItemTile(Item.FRAME);

        ItemTile game0 = new ItemTile(Item.GAME);
        ItemTile game1 = new ItemTile(Item.GAME);
        ItemTile game2 = new ItemTile(Item.GAME);
        ItemTile game3 = new ItemTile(Item.GAME);
        ItemTile game4 = new ItemTile(Item.GAME);

        ItemTile trophy0 = new ItemTile(Item.TROPHY);
        ItemTile trophy1 = new ItemTile(Item.TROPHY);
        ItemTile trophy2 = new ItemTile(Item.TROPHY);
        ItemTile trophy3 = new ItemTile(Item.TROPHY);
        ItemTile trophy4 = new ItemTile(Item.TROPHY);

        //first check, put one different type of ItemTile for each line
        bookshelf.insertIT(trophy0, 0);
        bookshelf.insertIT(trophy1, 1);
        bookshelf.insertIT(trophy2, 2);
        bookshelf.insertIT(trophy3, 3);
        bookshelf.insertIT(trophy4, 4);

        bookshelf.insertIT(game0, 0);
        bookshelf.insertIT(game1, 1);
        bookshelf.insertIT(game2, 2);
        bookshelf.insertIT(game3, 3);
        bookshelf.insertIT(game4, 4);

        bookshelf.insertIT(plant0, 0);
        bookshelf.insertIT(plant1, 1);
        bookshelf.insertIT(plant2, 2);
        bookshelf.insertIT(plant3, 3);
        bookshelf.insertIT(plant4, 4);

        bookshelf.insertIT(frame0, 0);
        bookshelf.insertIT(frame1, 1);
        bookshelf.insertIT(frame2, 2);
        bookshelf.insertIT(frame3, 3);
        bookshelf.insertIT(frame4, 4);

        bookshelf.insertIT(cat0, 0);
        bookshelf.insertIT(cat1, 1);
        bookshelf.insertIT(cat2, 2);
        bookshelf.insertIT(cat3, 3);
        bookshelf.insertIT(cat4, 4);

        bookshelf.insertIT(book0, 0);
        bookshelf.insertIT(book1, 1);
        bookshelf.insertIT(book2, 2);
        bookshelf.insertIT(book3, 3);
        //bookshelf.insertIT(book4, 4);
        bookshelf.print();
        ItemTile[][] shelf = bookshelf.getShelf();
        PrivateModelNetwork pmn = new PrivateModelNetwork(2, shelf);
        pmn.Serialize("bookshelf", bookshelf.getShelf());
    }
}
