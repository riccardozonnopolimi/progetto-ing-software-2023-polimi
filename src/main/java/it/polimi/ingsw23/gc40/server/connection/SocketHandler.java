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
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketHandler(Socket socketClient, ProxyController proxyController) {
        this.socket = socketClient;
        this.proxyController = proxyController;
    }


    public void sendObj(String st, Object obj) throws Exception{
        out.writeUTF(st);
        out.flush();
        out.writeObject(obj);
        out.flush();
    }

    @Override
    public void run() {
        try {

            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();

            in = new ObjectInputStream(socket.getInputStream());


            String line;
            String line2;
            String nickname="";
            int matchid, n;


            while (true) {
                line = in.readUTF();

                GameController gameController = null;
                Player player = null;


                if (line.equals("quit")) break;
                else {
                    //System.out.println("#socketHandler class - run method - ricevo da client: " + line);
                    switch (line) {
                        case "#addPlayer":
                            nickname = in.readUTF();
                            bool(proxyController.addPlayer(nickname));
                            proxyController.getLobbyController().getPlayer(nickname).getPrivateModelObservable().setSh(this);
                            sendObj("#private_object",proxyController.getLobbyController().getPlayer(nickname).getPrivateModelObservable().getData());
                            break;
                        case "#hasQueueMatch":
                            bool(proxyController.hasQueueMatch());
                            break;
                        case "#newMatch":
                            n = in.readInt();
                            bool(proxyController.newMatch(n));
                            break;
                        case "#playable":
                            n = in.readInt();
                            bool(proxyController.playable(n));
                            break;
                        case "#addToLastMatch":
                            line2 = in.readUTF();
                            matchid = proxyController.addToLastMatch(line2);
                            writeInt(matchid);
                            sendObj("#public_object", proxyController.getLobbyController().getMatchById(matchid).getPublicModelObservable().getData());
                            break;
                        case "#serverPrint":
                            line2 = in.readUTF();
                            proxyController.print(line2);
                            break;
                        default:
                            System.out.println("##################### BROOOO \n ho ricevuto: ");
                            System.out.println("");
                            System.out.println(line);
                            System.out.println("");
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void bool(boolean bool) throws IOException {
        if (bool) {
            out.writeUTF("#true");
            out.flush();
            System.out.println("@SH: Ho inviato: true");

        } else {
            out.writeUTF("#false");
            out.flush();
            System.out.println("@SH: Ho inviato: false");
        }
    }

    private void writeUTF(String st) throws IOException {
        out.writeUTF(st);
        out.flush();
        System.out.println("@SH: Ho inviato: "+st);


    }
    private void writeInt(int intero) throws IOException {
        out.writeUTF("#int");
        out.writeInt(intero);
        out.flush();
        System.out.println("@SH: Ho inviato: "+intero);


    }

}
