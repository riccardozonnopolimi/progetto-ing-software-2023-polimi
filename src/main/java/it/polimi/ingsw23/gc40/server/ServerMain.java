package it.polimi.ingsw23.gc40.server;

import it.polimi.ingsw23.gc40.server.connection.ConnectionController;
import it.polimi.ingsw23.gc40.server.connection.ControllerCompleto;
import it.polimi.ingsw23.gc40.server.connection.FactorySingleton;

public class ServerMain {
    public static void main(String[] args) throws Exception{
        ControllerCompleto controller = new ControllerCompleto();
        FactorySingleton.init(controller);
    }
}
