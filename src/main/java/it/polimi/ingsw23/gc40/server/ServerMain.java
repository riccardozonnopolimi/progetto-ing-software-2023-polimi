package it.polimi.ingsw23.gc40.server;

import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelObservable;
import it.polimi.ingsw23.gc40.server.connection.ConnectionOpener;
import it.polimi.ingsw23.gc40.server.controller.LobbyController;
import it.polimi.ingsw23.gc40.server.controller.ProxyController;
import it.polimi.ingsw23.gc40.server.model.Bookshelf;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        LobbyController lobbyController = new LobbyController();
        ProxyController proxyController = new ProxyController(lobbyController);
        ConnectionOpener a = new ConnectionOpener(proxyController);
        a.rmiInit();
        a.socketInit();

    }
}
