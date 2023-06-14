package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;
import it.polimi.ingsw23.gc40.server.controller.ProxyController;
import it.polimi.ingsw23.gc40.server.controller.GameController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class SocketHandler implements Runnable, Observer {

    private ProxyController proxyController;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private int matchid = -1;
    private String nickname = "###notdefined###";

    public SocketHandler(Socket socketClient, ProxyController proxyController) {
        this.socket = socketClient;
        this.proxyController = proxyController;

    }


    public void sendObj(String st, Object obj) throws Exception {
        out.writeUTF(st);
        out.flush();
        out.writeObject(obj);
        out.flush();
        out.reset();
    }


    @Override
    public void run() {
        try {

            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();


            in = new ObjectInputStream(socket.getInputStream());


            String line;
            String line2;

            int n;


            boolean online = true;
            while (online) {
                line = in.readUTF();

                GameController gameController = null;
                Player player = null;


                switch (line) {
                    case "#connect":
                        nickname = in.readUTF();
                        bool(proxyController.connect(nickname));

                        break;
                    case "#chooseTiles":
                        sendObj("#handTiles", proxyController.chooseTiles(matchid, in.readInt(), in.readInt(), in.readInt(), in.readChar()));
                        break;
                    case "#hasQueueMatch":
                        bool(proxyController.hasQueueMatch());
                        break;
                    case "#newMatch":
                        n = in.readInt();
                        bool(proxyController.newMatch(n));
                        break;

                    case "#getAvailableSlots":
                        writeInt(proxyController.getAvailableSlots(in.readUTF(), in.readInt()));
                        break;
                    case "#addToLastMatch":
                        line2 = in.readUTF();
                        matchid = proxyController.addToLastMatch(line2);
                        writeInt(matchid);
                        //sendObj("#update", proxyController.getLobbyController().getMatchById(matchid).getObserver().getData());
                        break;
                    case "#serverPrint":
                        line2 = in.readUTF();
                        proxyController.print(line2);
                        break;
                    case "#startRound":
                        writeInt(proxyController.startRound(in.readUTF(), in.readInt()));
                        break;
                    case "#insertTile":
                        proxyController.insertTile(in.readUTF(), (ItemTile) in.readObject(), in.readInt());
                        break;
                    case "#nextRound":
                        proxyController.nextRound(in.readUTF(), in.readInt());
                        break;
                    case "#addObserver":
                        proxyController.addSockObs(nickname, this);
                        proxyController.getLobbyController().getMatchById(matchid).getObserver().addObserver(this);
                        proxyController.getLobbyController().getPlayer(nickname).getObserver().addObserver(this);
                        break;
                    case "#reconnect":
                        writeInt(proxyController.reconnect(in.readUTF()));
                        break;
                    case "#close":
                        writeUTF("#quit");
                        proxyController.close(in.readUTF());
                        online=false;
                        //devo distruggere questo thread
                        break;

                    default:
                        System.out.println("###### Ho ricevuto: ");
                        System.out.println("");
                        System.out.println(line);
                        System.out.println("");
                        System.out.println("#comando non riconosciuto");
                        break;
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

        } else {
            out.writeUTF("#false");
            out.flush();
        }
    }

    private void writeUTF(String st) throws IOException {
        out.writeUTF(st);
        out.flush();


    }

    private void writeInt(int intero) throws IOException {
        out.writeUTF("#int");
        out.flush();
        out.writeInt(intero);
        out.flush();


    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof PublicModelNetwork) {
            if (((PublicModelNetwork) arg).getIdMatch() == matchid) {
                try {
                    sendObj("#update", arg);

                } catch (Exception e) {
                    System.out.println("Errore SG-Update: " + e);
                    throw new RuntimeException(e);
                }
            }
        }
        if (arg instanceof PrivateModelNetwork) {
            if (((PrivateModelNetwork) arg).getNickname().equals(nickname)) {
                try {
                    sendObj("#update", arg);
                } catch (Exception e) {
                    System.out.println("Errore SG-Update: " + e);
                    throw new RuntimeException(e);
                }
            }
        }


    }
}
