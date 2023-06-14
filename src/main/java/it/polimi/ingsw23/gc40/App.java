package it.polimi.ingsw23.gc40;

import it.polimi.ingsw23.gc40.client.Client;
import it.polimi.ingsw23.gc40.client.connection.conRMI;
import it.polimi.ingsw23.gc40.client.connection.conSocket;
import it.polimi.ingsw23.gc40.client.view.CLI;
import it.polimi.ingsw23.gc40.server.ServerMain;

public class App {
    public static void main(String[] args) throws Exception {

        if (CLI.getChoice(new String[]{"Client", "Server"}, "Cosa devo essere?") == 0) {
            Client.main(new String[]{});
        } else {
            ServerMain.main(new String[]{});
        }

    }
}
