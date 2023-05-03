package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.TileData;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class conSocket implements Connection{
    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public conSocket(Socket socket) {
        this.socket = socket;
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addPlayer(String name) throws Exception{
        //out corrisponde al serversocket in ascolto
        out.println("#addPlayer");
        return check(name);
    }


    @Override
    public void close() {
        in.close();
        out.close();
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TileData[] getTile(int x, int y, char direction, int qty) {

        return null;
    }

    @Override
    public void startInserction(int column) {

    }

    @Override
    public void putTile(int id) {

    }

    @Override
    public boolean newMatch(String string){
        out.println("#newMatch");
        return check(string);
    }

    @Override
    public boolean addToLastMatch(String name) throws RemoteException {
        out.println("#addToLastMatch");

        return check(name);
    }

    @Override
    public boolean hasQueueMatch()  {
        return check("#hasQueueMatch");
    }

    @Override
    public boolean playable() throws RemoteException {
        return check("#playable");
    }


    //send string to socket server; return true or false depending on server answer
    private boolean check(String string){
        out.println(string);
        out.flush();
        try{
            String line = in.nextLine();
            if(line.equals("#false")) return false;
            if(line.equals("#true")) return true;
        }catch(NoSuchElementException e){
            System.out.println("#catch del while-true socket lato client");
            e.printStackTrace();
        }
        System.out.println("##errore addPlayer conSocket");
        return false;
    }
}
