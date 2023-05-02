package it.polimi.ingsw23.gc40.server.connection;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketHandler implements Runnable {

    private Controller controller;
    private Socket socket;

    public SocketHandler(Socket socketClient, Controller controller) {
        this.socket=socketClient;
        this.controller=controller;
    }

    @Override
    public void run(){
        try{
            Scanner in = new Scanner(socket.getInputStream());
            while(true){
                String line = in.nextLine();
                if(line.equals("quit")) break;
                else{
                    System.out.println("#socketHandler class - run method - ricevo da client: "+line);
                    switch (line){
                        case "#addPlayer":
                            controller.addPlayer(in.nextLine());
                            break;
                        default:
                            System.out.println("#comando non riconosciuto");
                            break;
                    }
                }
            }
            in.close();
            socket.close();

        }catch(IOException e){
            System.out.println("#SocketHandler class - run method - catch error:");
            e.printStackTrace();
        }
    }
}
