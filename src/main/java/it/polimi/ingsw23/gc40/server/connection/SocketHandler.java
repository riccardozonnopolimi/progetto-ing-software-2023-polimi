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


    public void sendObj(String st, Object obj) throws Exception{
        out.writeUTF(st);
        out.flush();
        out.writeObject(obj);
        out.flush();
    }

    @Override
    public void run() {
        try {
            in = new Scanner(socket.getInputStream());
            //we can try with a bufferReader
           // BufferedReader in2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //in printWriter constructor we can pass true to autoFlush, we couldn't use flush every time after sending message
           // out = new PrintWriter(socket.getOutputStream(), true);
            //out.println("connesso");
            out = new ObjectOutputStream(socket.getOutputStream());

            String target;
            String nickname="";
            int matchid;


            while (true) {
                target = in.nextLine();
                //target = in2.readLine();
                String line2;
                GameController gameController = null;
                Player player = null;
                if (target.equals("quit")) break;
                else {
                    //System.out.println("#socketHandler class - run method - ricevo da client: " + line);
                    switch (target) {
                        case "#addPlayer":
                            nickname = in.nextLine();
                            bool(proxyController.addPlayer(nickname));
                            proxyController.getLobbyController().getPlayer(nickname).getPrivateModelObservable().setSh(this);
                            break;
                        case "#hasQueueMatch":
                            bool(proxyController.hasQueueMatch());
                            break;
                        case "#newMatch":
                            int n = in.nextInt();
                            bool(proxyController.newMatch(n));
                            break;
                        case "#playable":
                            bool(proxyController.playable());
                            break;
                        case "#addToLastMatch":
                            line2 = in.nextLine();
                            matchid = proxyController.addToLastMatch(line2);
                            writeInt(matchid);
                            sendObj("#private_object",proxyController.getLobbyController().getPlayer(nickname).getPrivateModelObservable().getData());
                            sendObj("#public_object", proxyController.getLobbyController().getMatchById(matchid).getPublicModelObservable().getData());
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
