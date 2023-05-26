package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.server.controller.ProxyController;
import it.polimi.ingsw23.gc40.server.controller.GameController;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketHandler implements Runnable {

    private ProxyController proxyController;
    private Socket socket;
    private Scanner in;
    private ObjectOutputStream out;

    public SocketHandler(Socket socketClient, ProxyController proxyController) {
        this.socket = socketClient;
        this.proxyController = proxyController;
    }


    @Override
    public void run() {
        try {
            //in = new Scanner(socket.getInputStream());
            //we can try with a bufferReader
            BufferedReader in2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //in printWriter constructor we can pass true to autoFlush, we couldn't use flush every time after sending message
            //out = new PrintWriter(socket.getOutputStream(), true);
            //out.println("connesso");
            out = new ObjectOutputStream(socket.getOutputStream());

            String target;

            while (true) {
                //String line = in.nextLine();
                target = in2.readLine();
                String line2;
                GameController gameController = null;
                Player player = null;
                if (target.equals("quit")) break;
                else {
                    //System.out.println("#socketHandler class - run method - ricevo da client: " + line);
                    switch (target) {
                        case "#addPlayer":
                            line2 = in.nextLine();
                            String name = line2;
                            bool(proxyController.addPlayer(name));
                            proxyController.getLobbyController().getPlayer(name).setSocket(socket);
                            break;
                        case "#hasQueueMatch":
                            bool(proxyController.hasQueueMatch());
                            break;
                        case "#newMatch":
                            line2 = in.nextLine();
                            bool(proxyController.newMatch(line2));
                            break;
                        case "#playable":
                            bool(proxyController.playable());
                            break;
                        case "#addToLastMatch":
                            line2 = in.nextLine();
                            proxyController.getLobbyController().getQueueMatch().setSocket(socket);
                            restituisci(proxyController.addToLastMatch(line2) + "");
                            //todo better but think works

                            break;
                        case "#serverPrint":
                            line2 = in.nextLine();
                            proxyController.print(line2);
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

    private void bool(boolean bool) throws IOException {
        if (bool) {
            out.writeChars("#true");

        } else {
            out.writeChars("#false");
            out.flush();
        }
    }

    private void restituisci(String st) throws IOException {
        out.writeChars(st);
        out.flush();


    }

}
