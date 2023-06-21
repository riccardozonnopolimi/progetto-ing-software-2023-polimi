package it.polimi.ingsw23.gc40;

import it.polimi.ingsw23.gc40.client.Client;
import it.polimi.ingsw23.gc40.client.connection.conRMI;
import it.polimi.ingsw23.gc40.client.connection.conSocket;
import it.polimi.ingsw23.gc40.client.view.CLI;
import it.polimi.ingsw23.gc40.server.ServerMain;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println(CLI.green+"Welcome in myShelfie!"+CLI.resetStdColor);
        System.out.println();

        System.out.println("You can Play by selecting \"client\" or host a game by selecting \"server\"");
        System.out.println();

        if (CLI.getChoice(new String[]{"Client (to start playing)", "Server (to host a game)"}, "What process do you want to start?") == 0) {
            Client.main(new String[]{});
        } else {
            ServerMain.main(new String[]{});
        }

    }
}
