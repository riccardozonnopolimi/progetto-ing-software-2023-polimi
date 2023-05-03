package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.server.model.Match;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketHandler implements Runnable {

    private Controller controller;
    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public SocketHandler(Socket socketClient, Controller controller) {
        this.socket = socketClient;
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            while (true) {
                String line = in.nextLine();
                String line2;
                Match match = null;
                Player player = null;
                if (line.equals("quit")) break;
                else {
                    //System.out.println("#socketHandler class - run method - ricevo da client: " + line);
                    switch (line) {
                        case "#addPlayer":
                            line2 = in.nextLine();
                            bool(controller.addPlayer(line2));
                            break;
                        case "#hasQueueMatch":
                            bool(controller.hasQueueMatch());
                            break;
                        case "#newMatch":
                            line2 = in.nextLine();
                            bool(controller.newMatch(line2));
                            break;
                        case "#playable":
                            bool(controller.playable());
                            break;
                        case "#addToLastMatch":
                            line2 = in.nextLine();
                            bool(controller.addToLastMatch(line2));
                            break;


                        default:
                            System.out.println("#comando non riconosciuto");
                            break;
                    }
                }
            }
            in.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("#SocketHandler class - run method - catch error:");
            e.printStackTrace();
        }
    }
    private void bool(boolean bool){
        if(bool){
            out.println("#true");
            out.flush();
        } else {
            out.println("#false");
            out.flush();
        }
    }
}
