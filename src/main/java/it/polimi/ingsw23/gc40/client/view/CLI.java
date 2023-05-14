package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.rmi.RemoteException;
import java.util.Scanner;

public class CLI extends View implements Runnable {
    private Scanner sc;

    public CLI(ConnectionController con) {
        super(con);
        sc = new Scanner(System.in);
    }

    public void init() throws RemoteException, Exception {
        System.out.println("#metodo start della view CLI");
        //i metodi chiamati su con vengono implementati dalla classe conSocket
        // o conRMI a seconda di come viene istanziata la view
        //
        //creazione player
        this.player = inString("Inserisci nome giocatore: ");
        while (!con.addPlayer(player)) {
            System.out.println("Nome utente già in uso, riprova...");
            this.player = inString("Inserisci nome giocatore: ");
        }
        //ItemTile[][] prova = (ItemTile[][]) this.deserializePrivateModel();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
              //  System.out.print(prova[i][j].toString() + "|");
            }
            System.out.println();
        }
        /*
        while(playerserialzable.players.get(0).nickname.equals(name))
        {
        }         */
        //entro nel match / creo il match

        if (con.hasQueueMatch()) {
            this.matchId = con.addToLastMatch(this.player);
        } else {
            while (!con.newMatch(inString("Stai creando un nuovo match, di quanti giocatori vuoi che sia la partita? ")))
                System.out.println("Match non creato, riprova...  (Ricorda che le partite possono avere da 2 a 4 giocatori)");
            this.matchId = con.addToLastMatch(this.player);
        }
        this.start();

        //System.out.println("test");
        /*
        if (con.playable()) {
            System.out.println("playable");
        } else System.out.println("notplayable");
        System.out.println("test2");
        con.serverPrint("helpp");

        // todo riadattare a modello client-server
        while(match.playable()){
            System.out.println("E' il turno di "+match.getNext().getNickName());
            match.round(match.getNext());
        }
        //server.endMatch(match);
        for (int i=0; i< match.getNumPlayers(); i++){
            System.out.println("Punteggio di "+match.getPlayers().get(i).getNickName()+": "+match.getPlayers().get(i).totScore());
        }
        System.out.println("THE WINNER IS..... "+match.winner().getNickName());

         */
    }

    public String inString(String question) {
        System.out.println(question);
        String line = sc.nextLine();
        sc.reset();
        return line;
    }
}