package it.polimi.ingsw23.gc40.server.connection;

import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelObserver;
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

    public SocketHandler(Socket socketClient, ProxyController proxyController) {
        this.socket = socketClient;
        this.proxyController = proxyController;
        this.proxyController.addObserver(this);
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
            String nickname = "";
            int matchid = -1, n;


            while (true) {
                line = in.readUTF();

                GameController gameController = null;
                Player player = null;


                //System.out.println("@SH: RICEVO da" + nickname + ":  " + line + "");
                if (line.equals("quit")) break;
                else {
                    switch (line) {
                        case "#addPlayer":
                            nickname = in.readUTF();
                            bool(proxyController.addPlayer(nickname));
//                            proxyController.getLobbyController().getPlayer(nickname).getPrivateModelObserver().setSh(this);
//                            sendObj("#private_object", proxyController.getLobbyController().getPlayer(nickname).getPrivateModelObserver().getData());

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
                        /*case "#playable":
                            n = in.readInt();
                            bool(proxyController.playable(n));
                            break;*/
                        case "#getAvailableSlots":
                            writeInt(proxyController.getAvailableSlots(in.readUTF(), in.readInt()));
                            break;
                        case "#addToLastMatch":
                            line2 = in.readUTF();
                            matchid = proxyController.addToLastMatch(line2);
                            writeInt(matchid);
                            break;
                        case "#serverPrint":
                            line2 = in.readUTF();
                            proxyController.print(line2);
                            break;
                        case "#startRound":
                            writeInt(proxyController.startRound(in.readUTF(), in.readInt()));
                            break;
                        case "#insertTile":
                            proxyController.insertTile(in.readUTF(),(ItemTile) in.readObject(),in.readInt());
                            break;
                        case "#nextRound":
                            proxyController.nextRound(in.readUTF(), in.readInt());
                            break;
                        /*case "#setObservers":
                            proxyController.getLobbyController().getPlayer(nickname).getObserver().newObserver(this);
                            proxyController.getLobbyController().getMatchById(matchid).getObserver().newObserver(this);
                            System.out.println("mi aggiungo come observer SH");

                            break;
                        case "#getPrivateObserver":
                            line2 = in.readUTF();
                            sendObj(proxyController.getPrivateObserver(line2));
                        case "#getPublicObserver":
                            matchid = in.readInt();
                            sendObj(proxyController.getPublicObserver(matchid));
                            */
                        default:
                            System.out.println("###### Ho ricevuto: ");
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
            //System.out.println("@SH: Ho inviato: true");

        } else {
            out.writeUTF("#false");
            out.flush();
            //System.out.println("@SH: Ho inviato: false");
        }
    }

    private void writeUTF(String st) throws IOException {
        out.writeUTF(st);
        out.flush();
        //System.out.println("@SH: Ho inviato: " + st);


    }

    private void writeInt(int intero) throws IOException {
        out.writeUTF("#int");
        out.flush();
        out.writeInt(intero);
        out.flush();
        //System.out.println("@SH: Ho inviato: " + intero);


    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            sendObj("#update",arg);
        } catch (Exception e) {
            System.out.println("Errore SG-Update: "+e);
            throw new RuntimeException(e);
        }
    }
}
