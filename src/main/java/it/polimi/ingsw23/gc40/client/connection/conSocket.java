package it.polimi.ingsw23.gc40.client.connection;

import it.polimi.ingsw23.gc40.netModel.TileData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class conSocket implements Connection{
    private Socket socket;
    //private Scanner in;
    private PrintWriter out;

    public conSocket(Socket socket) {
        this.socket = socket;
        try {
             //in = new Scanner(socket.getInputStream());
             out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addPlayer(String name) throws Exception{
        out.println("#addPlayer");
        out.println(name);
        //invio in socket il comando e il nome utente poi vediamo come
       // throw ExecutionControl.NotImplementedException;
    }
    @Override
    public void close() throws Exception{

        //in.close();
        out.close();
        socket.close();
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

}
